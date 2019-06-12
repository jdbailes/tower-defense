package com.td.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.td.game.Config;
import com.td.game.TowerDefenseGame;

public class MainMenuScreen implements Screen {

  private static final int BUTTON_WIDTH = 300;
  private static final int BUTTON_HEIGHT = 150;

  private TowerDefenseGame game;

  private Texture playButtonActive;
  private Texture playButtonInactive;
  private Texture exitButtonActive;
  private Texture exitButtonInactive;

  public MainMenuScreen(TowerDefenseGame game) {
    this.game = game;
    this.playButtonActive = new Texture("core/assets/fonts/playButtonActive.png");
    this.playButtonInactive = new Texture("core/assets/fonts/playButtonInactive.png");
    this.exitButtonActive = new Texture("core/assets/fonts/exitButtonActive.png");
    this.exitButtonInactive = new Texture("core/assets/fonts/exitButtonInactive.png");
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

    game.batch.begin();

    float buttonCentrePosition = Config.getScreenWidth() / 2 - BUTTON_WIDTH / 2;

    game.batch.draw(exitButtonActive, buttonCentrePosition, 100, BUTTON_WIDTH, BUTTON_HEIGHT);
    game.batch.end();
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
