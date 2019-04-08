package com.td.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.td.game.TowerDefenseGame;

/**
 * Serves the Main Menu screen in the game.
 */
public class MenuScreen implements Screen {

  private final TowerDefenseGame game;
  private OrthographicCamera camera;

  public MenuScreen(final TowerDefenseGame game) {
    this.game = game;

    camera = new OrthographicCamera();
    camera.setToOrtho(false, 1920, 1080);
  }

  @Override
  public void show() {

  }

  @Override
  public void render(float delta) {
    Gdx.gl.glClearColor(0, 0, 0.2f, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    camera.update();

    // Need to call the game's SpriteBatch and BitmapFont, not create our own
    game.batch.setProjectionMatrix(camera.combined);

    game.batch.begin();
    game.font.draw(game.batch, "TOWER DEFENSE ", 900, 540);
    game.font.draw(game.batch, "Tap anywhere to begin!", 900, 500);
    game.batch.end();

    // Check to see if the screen has been touched and switches to game screen if so
    if (Gdx.input.isTouched()) {
      game.setScreen(new GameScreen(game));
      dispose();
    }

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
