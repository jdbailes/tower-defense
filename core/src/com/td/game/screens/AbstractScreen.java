package com.td.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.td.game.Config;
import com.td.game.TowerDefenseGame;

public class AbstractScreen implements Screen {

  TowerDefenseGame game;
  OrthographicCamera camera;

  AbstractScreen(final TowerDefenseGame game) {
    this.game = game;

    // Setup the camera
    this.camera = new OrthographicCamera();
    this.camera.setToOrtho(false, Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);
    this.camera.update();
  }

  @Override
  public void show() {

  }

  @Override
  public void render(float delta) {
    Gdx.gl.glClearColor(0, 0, 0.2f, 1);
    Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
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

  void switchScreen(Screen screen) {
    game.setScreen(screen);
    dispose();
  }

  int getCentrePointX(int width) {
    return (Config.SCREEN_WIDTH / 2) - (width / 2);
  }
  
  int getCentrePointY(int height) {
    return (Config.SCREEN_HEIGHT / 2) - (height / 2);
  }

  float getInputX() {
    // Get the coordinates of the current mouse position
    Vector3 screenCoords = camera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
    return screenCoords.x;
  }

  float getInputY() {
    // Get the coordinates of the current mouse position
    Vector3 screenCoords = camera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
    return screenCoords.y;
  }

  void safeExit() {
    Gdx.app.exit();
  }
}
