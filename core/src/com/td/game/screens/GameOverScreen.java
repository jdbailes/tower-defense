package com.td.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Logger;
import com.td.game.TowerDefenseGame;

/**
 * Serves the Main Menu screen in the game.
 */
public class GameOverScreen extends AbstractScreen {

  private final static Logger logger = new Logger("GameOverScreen", Logger.INFO);

  private static final int GAME_OVER_Y = 620;
  private static final int MENU_BUTTON_Y = 100;

  private final int gameOverX;
  private final int menuButtonX;

  private Texture gameOver;
  private Texture menuButtonActive;
  private Texture menuButtonInactive;

  public GameOverScreen(final TowerDefenseGame game) {
    super(game);

    // Load images to texture
    this.gameOver = new Texture("fonts/game_over_title.png");
    this.menuButtonActive = new Texture("fonts/menu_button_active.png");
    this.menuButtonInactive = new Texture("fonts/menu_button_inactive.png");

    // Calculate x-coordinates for screen items
    this.gameOverX = getCentrePointX(gameOver.getWidth());
    this.menuButtonX = getCentrePointX(menuButtonInactive.getWidth());
  }

  @Override
  public void show() {

  }

  @Override
  public void render(float delta) {
    super.render(delta);

    this.camera.update();
    this.game.batch.begin();

    drawGameOverTitle();

    // Render exit button
    if (isTouchingMenuButton(menuButtonX) && Gdx.input.justTouched()) {
      logger.info("Switching to main menu screen");
      switchScreen(new MainMenuScreen(game));
    } else if (isTouchingMenuButton(menuButtonX)) {
      drawMenuButton(menuButtonX, MENU_BUTTON_Y, true);
    } else {
      drawMenuButton(menuButtonX, MENU_BUTTON_Y, false);
    }

    this.game.batch.end();
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

  private void drawGameOverTitle() {
    game.batch.draw(gameOver, gameOverX, GAME_OVER_Y);
  }

  @Override
  public String toString() {
    return "Game over screen";
  }

}
