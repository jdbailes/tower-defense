package com.td.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.td.game.Config;
import com.td.game.TowerDefenseGame;

/**
 * An abstraction of Screen implementations to allow for shared functionality across all game
 * screens.
 *
 * @author josephbailey
 */
public abstract class AbstractScreen implements Screen {

  final TowerDefenseGame game;
  final OrthographicCamera camera;

  AbstractScreen(final TowerDefenseGame game) {
    this.game = game;

    // Setup the camera
    camera = new OrthographicCamera();
    camera.setToOrtho(false, Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);
    camera.update();
  }

  @Override
  public void show() {

  }

  @Override
  public void render(float delta) {
    // Sets background colour to black
    Gdx.gl.glClearColor(0, 0, 0, 1);
    Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
  }

  @Override
  public void resize(int width, int height) {
    // Do nothing...
  }

  @Override
  public void pause() {
    // Do nothing...
  }

  @Override
  public void resume() {
    // Do nothing...
  }

  @Override
  public void hide() {
    // Do nothing...
  }

  @Override
  public void dispose() {
    // Do nothing...
  }

  /**
   * Safely switches screen.
   */
  void switchScreen(Screen screen) {
    game.setScreen(screen);
    dispose();
  }

  /**
   * Gets the centre point of the screen relative to the width of an object.
   */
  int getCentrePointX(int width) {
    return (Config.SCREEN_WIDTH / 2) - (width / 2);
  }

  /**
   * Gets the centre points of the screen relative to the height of an object.
   */
  int getCentrePointY(int height) {
    return (Config.SCREEN_HEIGHT / 2) - (height / 2);
  }

  /**
   * Gets the on-screen x-coordinate of the current mouse input.
   */
  float getInputX() {
    // Get the coordinates of the current mouse position
    Vector3 screenCoords = camera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
    return screenCoords.x;
  }

  /**
   * Gets the on-screen y-coordinate of the current mouse input.
   */
  float getInputY() {
    // Get the coordinates of the current mouse position
    Vector3 screenCoords = camera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
    return screenCoords.y;
  }

  /**
   * Safely exits the application.
   */
  void safeExit() {
    Gdx.app.exit();
  }

  /**
   * Returns true when input is just touched.
   */
  boolean inputIsTouched() {
    return Gdx.input.justTouched();
  }
}
