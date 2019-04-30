package com.td.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.td.game.TowerDefenseGame;
import com.td.game.managers.GameManager;

/**
 * Serves the screen the game is played within.
 */
public class GameScreen implements Screen {

  private OrthographicCamera camera;
  private SpriteBatch batch;
  private GameManager manager;

  private final TowerDefenseGame game; // Game is passed into the GameScreen constructor

  GameScreen(final TowerDefenseGame game) {
    this.game = game;

    // Setup the camera
    this.camera = new OrthographicCamera();
    this.camera.setToOrtho(false, 1920, 1080);

    // Initialise a new SpriteBatch for this game
    this.batch = new SpriteBatch();

    // Creates a game manager
    this.manager = new GameManager(batch);
  }

  @Override
  public void show() {

  }

  @Override
  public void render(float delta) {
    // Renders the background
    Gdx.gl.glClearColor(0, 0, 0.2f, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    camera.update();
    batch.setProjectionMatrix(camera.combined);
    batch.begin();

    manager.render();

    batch.end();
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
