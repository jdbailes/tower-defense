package com.td.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Preferences;
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
 *
 * @author josephbailey
 * @author tautvydasponelis
 */
public class GameScreen extends AbstractScreen {

  private static final String NAVIGATION_KEY = "NAVIGATION_LAYER";

  private final TiledMapRenderer tiledMapRenderer;
  private final ShapeRenderer shapeRenderer;
  private final SpriteBatch batch;
  private final Level level;
  private final int levelNumber;

  private OrthographicCamera camera;

  GameScreen(final TowerDefenseGame game, int level) {
    super(game);
    setupCamera();

    levelNumber = level;
    TiledMap tiledMap = this.game.getAssetManager().get(Config.getTiledMapFilepath(level));

    MapObjects mapObjects = tiledMap.getLayers().get(NAVIGATION_KEY).getObjects();
    List<Vector2> breadCrumbs = getBreadCrumbs(mapObjects);

    this.level = new Level(breadCrumbs, level);

    tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
    shapeRenderer = new ShapeRenderer();
    batch = new SpriteBatch();
  }

  @Override
  public void show() {

  }

  @Override
  public void render(float delta) {
    super.render(delta);

    // Render the tilemap
    camera.update();
    tiledMapRenderer.setView(camera);
    tiledMapRenderer.render();

    if (Gdx.input.isKeyJustPressed(Keys.NUM_1)) {
      // Get the coordinates of the current mouse position
      int x = Gdx.input.getX();
      int y = Gdx.input.getY();
      Vector3 worldCoords = camera.unproject(new Vector3(x, y, 0));

      // Create a new tower in the fleet with these towers
      level.addNormalShip((int) worldCoords.x, (int) worldCoords.y);

    }

    if (Gdx.input.isKeyJustPressed(Keys.NUM_2)) {
      // Get the coordinates of the current mouse position
      int x = Gdx.input.getX();
      int y = Gdx.input.getY();
      Vector3 worldCoords = camera.unproject(new Vector3(x, y, 0));

      // Create a new tower in the fleet with these towers
      level.addBigShip((int) worldCoords.x, (int) worldCoords.y);

    }

    // Render the game
    batch.setProjectionMatrix(camera.combined);
    batch.begin();

    boolean gameOver = this.level.update();

    if (gameOver) {
      switchScreen(new GameOverScreen(game));

    }

    if (level.levelComplete()) {
      Preferences preferences = Gdx.app.getPreferences("profile");
      switch (levelNumber) {
        case 1:
          preferences.putBoolean("levelTwoUnlocked", true);
          preferences.flush();
          break;
        case 2:
          preferences.putBoolean("levelThreeUnlocked", true);
          preferences.flush();
          break;
        default:
          // Do nothing...
      }
      switchScreen(new LevelCompleteScreen(game));

    }

    level.draw(batch);

    shapeRenderer.end();
    batch.end();
  }

  private void setupCamera() {
    camera = new OrthographicCamera();
    camera.setToOrtho(false, Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);
    camera.update();
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
