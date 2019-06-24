package com.td.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.td.game.Config;
import com.td.game.TowerDefenseGame;
import com.td.game.offScreen.Level;
import java.util.ArrayList;
import java.util.List;

/**
 * Serves the screen the game is played within.
 */
public class GameScreen extends AbstractScreen {

  private static final String NAVIGATION_KEY = "NAVIGATION_LAYER";

  private OrthographicCamera camera;
  private TiledMap tiledMap;
  private TiledMapRenderer tiledMapRenderer;
  private ShapeRenderer shapeRenderer;
  private SpriteBatch batch;
  private Level level;

  GameScreen(final TowerDefenseGame game, int level) {
    super(game);
    setupCamera();

    this.tiledMap = this.game.getAssetManager().get(Config.getLevelFilepath(level));

    MapObjects mapObjects = this.tiledMap.getLayers().get(NAVIGATION_KEY).getObjects();
    List<Vector2> breadCrumbs = getBreadCrumbs(mapObjects);

    this.level = new Level(breadCrumbs);

    this.tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
    this.shapeRenderer = new ShapeRenderer();
    this.batch = new SpriteBatch();
  }

  @Override
  public void show() {

  }

  @Override
  public void render(float delta) {
    super.render(delta);

    // Render the tilemap
    this.camera.update();
    this.tiledMapRenderer.setView(camera);
    this.tiledMapRenderer.render();

    // TODO Move to private method
    if (Gdx.input.isKeyJustPressed(Keys.NUM_1)) {
      // Get the coordinates of the current mouse position
      int x = Gdx.input.getX();
      int y = Gdx.input.getY();
      Vector3 worldCoords = camera.unproject(new Vector3(x, y, 0));

      // Create a new tower in the fleet with these towers
      level.addShip((int) worldCoords.x, (int) worldCoords.y);
    }

    // Render the game
    this.batch.setProjectionMatrix(camera.combined);
    this.batch.begin();

    boolean gameOver = this.level.update();

    if (gameOver) {
      game.setScreen(new GameOverScreen(game));
      dispose();
    }

    this.level.draw(batch);

    this.shapeRenderer.end();
    this.batch.end();
  }

  private void setupCamera() {
    this.camera = new OrthographicCamera();
    this.camera.setToOrtho(false, Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);
    this.camera.update();
  }

  private List<Vector2> getBreadCrumbs(MapObjects mapObjects) {

    List<Vector2> breadCrumbs = new ArrayList<>();

    for (int i = 0; i < mapObjects.getCount(); i++) {
      MapObject mapObject = mapObjects.get(i);

      Vector2 vector2 = new Vector2(
          (float) mapObject.getProperties().get("x"),
          (float) mapObject.getProperties().get("y")
      );

      breadCrumbs.add(Integer.valueOf(mapObject.getName()), vector2);
    }

    return breadCrumbs;
  }
}
