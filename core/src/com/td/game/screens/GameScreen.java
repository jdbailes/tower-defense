package com.td.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.td.game.TowerDefenseGame;
import com.td.game.model.Wave;
import java.util.Random;

public class GameScreen implements Screen {

  private OrthographicCamera camera;
  private SpriteBatch batch;

  // Creates the wave of enemies
  private Wave wave = new Wave(10);

  private final TowerDefenseGame game;

  GameScreen(final TowerDefenseGame game) {
    // Passing the game into the game screen
    this.game = game;

    // Setup the camera
    camera = new OrthographicCamera();
    camera.setToOrtho(false, 1920, 1080);

    // Initialise a new SpriteBatch for this game
    batch = new SpriteBatch();
  }

  @Override
  public void show() {

  }

  @Override
  public void render(float delta) {
    // Renders the background
    Gdx.gl.glClearColor(0, 0, 0.2f, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    // Determine a spawn probability
    Random rand = new Random();
    float randomFloat = rand.nextFloat();

    // Spawns enemies onto the map
    if (randomFloat < 0.01) { // Spawn probability could be constant/parameterised
      // Adds a new enemy to the wave
      wave.addEnemy();
    }

    // Removes enemies that have reached the end of the map
    wave.cleanUp();

    camera.update();
    batch.setProjectionMatrix(camera.combined);
    batch.begin();

    // Updates the positions of all enemies and draws them
    wave.updatePositions(100 * Gdx.graphics.getDeltaTime());
    wave.batchDraw(batch);

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
