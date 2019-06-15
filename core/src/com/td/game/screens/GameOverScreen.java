package com.td.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;
import com.td.game.Config;
import com.td.game.TowerDefenseGame;

/**
 * Serves the Main Menu screen in the game.
 */
public class GameOverScreen implements Screen {

  private static final int GAME_OVER_Y = 800;
  private static final int MENU_BUTTON_Y = 300;

  private final int gameOverX;
  private final int menuButtonX;

  private TowerDefenseGame game;
  private OrthographicCamera camera;
  private Texture gameOver;
  private Texture menuButtonActive;
  private Texture menuButtonInactive;

  public GameOverScreen(final TowerDefenseGame game) {
    this.game = game;

    // Load images to texture
    this.gameOver = new Texture("core/assets/fonts/game_over_title.png");
    this.menuButtonActive = new Texture("core/assets/fonts/menu_button_active.png");
    this.menuButtonInactive = new Texture("core/assets/fonts/menu_button_inactive.png");

    // Calculate x-coordinates for screen items
    this.gameOverX = getCentrePoint(gameOver.getWidth());
    this.menuButtonX = getCentrePoint(menuButtonInactive.getWidth());

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
    clearScreen();

    this.camera.update();
    this.game.batch.begin();

    drawGameOverTitle();

    // Render exit button
    if (isTouchingMenuButton(menuButtonX) && Gdx.input.isTouched()) {
      switchScreen(new MainMenuScreen(game));
    } else if (isTouchingMenuButton(menuButtonX)) {
      drawMenuButton(menuButtonX, MENU_BUTTON_Y, true);
    } else {
      drawMenuButton(menuButtonX, MENU_BUTTON_Y, false);
    }

    this.game.batch.end();
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

  private void drawMenuButton(float x, float y, boolean active) {
    Texture texture = active ? this.menuButtonActive : this.menuButtonInactive;
    game.batch.draw(texture, x, y);
  }

  private boolean isTouchingMenuButton(int x) {
    return getInputX() < x + menuButtonInactive.getWidth()
        && getInputX() > x
        && getInputY() < MENU_BUTTON_Y + menuButtonInactive.getHeight()
        && getInputY() > MENU_BUTTON_Y;
  }

  private float getInputX() {
    // Get the coordinates of the current mouse position
    Vector3 screenCoords = camera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
    return screenCoords.x;
  }

  private float getInputY() {
    // Get the coordinates of the current mouse position
    Vector3 screenCoords = camera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
    return screenCoords.y;
  }

  private void switchScreen(Screen screen) {
    game.setScreen(screen);
    dispose();
  }

  private void drawGameOverTitle() {
    game.batch.draw(gameOver, gameOverX, GAME_OVER_Y);
  }

  private int getCentrePoint(int width) {
    return (Config.SCREEN_WIDTH / 2) - (width / 2);
  }


  private void clearScreen() {
    Gdx.gl.glClearColor(0, 0, 0.2f, 1);
    Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
  }

}
