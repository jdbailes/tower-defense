package com.td.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector3;
import com.td.game.Config;
import com.td.game.TowerDefenseGame;
import com.td.game.offScreen.Level;

/**
 * Serves the screen the game is played within.
 */
public class GameScreen implements Screen {

  private final TowerDefenseGame game;

  private OrthographicCamera camera;
  private TiledMap tiledMap;
  private TiledMapRenderer tiledMapRenderer;
  private ShapeRenderer shapeRenderer;
  private SpriteBatch batch;
  private Level level;

  private boolean isDebug = false;

  GameScreen(final TowerDefenseGame game) {
    this.game = game;

    // Setup the camera
    this.camera = new OrthographicCamera();
    this.camera.setToOrtho(false, Config.getScreenWidth(), Config.getScreenHeight());
    this.camera.update();

    this.tiledMap = new TmxMapLoader().load("Level_1.tmx");
    this.tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);

    // Initialise a new SpriteBatch for this game
    this.batch = new SpriteBatch();
    this.level = new Level();

    this.shapeRenderer = new ShapeRenderer();
  }

  @Override
  public void show() {

  }

  @Override
  public void render(float delta) {
    // Clear the screen
    Gdx.gl.glClearColor(1, 0, 0, 1);
    Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    // Render the tilemap
    this.camera.update();
    this.tiledMapRenderer.setView(camera);
    this.tiledMapRenderer.render();

    // TODO Move to private method
    // Will switch to/from debug mode if tab is pressed
    if (Gdx.input.isKeyJustPressed(Keys.TAB)) {
      this.isDebug = !this.isDebug;
    }

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

    boolean gameOver =this.level.update();

    if(gameOver) {
      game.setScreen(new GameOverScreen(game));
      dispose();
    }

    if (this.isDebug) {
      // Configure the shape renderer for debug mode
      this.shapeRenderer.setProjectionMatrix(camera.projection);
      this.shapeRenderer.setTransformMatrix(camera.view);
      this.shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
      this.level.draw(batch, shapeRenderer);
    } else {
      this.level.draw(batch);
    }

    this.shapeRenderer.end();
    this.batch.end();
  }

  @Override
  public void resize(int width, int height) {

  }

  @Override
  public void pause() {

  }

  @Override
  public void resume() {

  }

  @Override
  public void hide() {

  }

  @Override
  public void dispose() {

  }
}
