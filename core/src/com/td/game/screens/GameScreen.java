package com.td.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.td.game.Config;
import com.td.game.TowerDefenseGame;
import com.td.game.offScreen.Level;

/**
 * Serves the screen the game is played within.
 */
public class GameScreen implements Screen {

  private OrthographicCamera camera;

  private TiledMap tiledMap;
  private TiledMapRenderer tiledMapRenderer;

  private final TowerDefenseGame game; // Game is passed into the GameScreen constructor

  private SpriteBatch batch;
  private Level level;

  GameScreen(final TowerDefenseGame game) {
    this.game = game;

    // Setup the camera
    this.camera = new OrthographicCamera();
    this.camera.setToOrtho(false, Config.getScreenWidth(), Config.getScreenHeight());
    this.camera.update();

    this.tiledMap = new TmxMapLoader().load("tiled.tmx");
    this.tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);

    // Initialise a new SpriteBatch for this game
    this.batch = new SpriteBatch();
    this.level = new Level();
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

    // TODO Functionality to place a new tower on the screen
    if(Gdx.input.isKeyJustPressed(Keys.T)) {
      // Get the coordinates of the current mouse position
      int x = Gdx.input.getX();
      int y = Gdx.input.getY();

      // Create a new tower in the fleet with these towers
      level.addShip(x, Config.getScreenHeight() - y);
      System.out.println("I spawned a tower");
    }

    // Render the game
    this.batch.setProjectionMatrix(camera.combined);
    this.batch.begin();

    this.level.update();    // Firstly, level is updated
    this.level.draw(batch); // Level is then drawn after its been updated

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
