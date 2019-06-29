package com.td.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Logger;
import com.td.game.TowerDefenseGame;

/**
 * Serves the Main Menu screen in the game.
 *
 * @author josephbailey
 */
public class GameOverScreen extends AbstractScreen {

  private static final Logger logger = new Logger("GameOverScreen", Logger.INFO);

  // All file paths for the textures of all screen components
  private static final String GAME_OVER_FILEPATH = "ui/game_over_title.png";
  private static final String MENU_ACTIVE_FILEPATH = "ui/menu_button_active.png";
  private static final String MENU_INACTIVE_FILEPATH = "ui/menu_button_inactive.png";

  // y-positions of all screen components
  private static final int GAME_OVER_Y = 620;
  private static final int MENU_BUTTON_Y = 100;

  // x-positions of all screen components
  private final int gameOverX;
  private final int menuButtonX;

  // All textures for screen components
  private final Texture gameOver;
  private final Texture menuButtonActive;
  private final Texture menuButtonInactive;

  GameOverScreen(final TowerDefenseGame game) {
    super(game);

    // Load images to texture
    gameOver = new Texture(GAME_OVER_FILEPATH);
    menuButtonActive = new Texture(MENU_ACTIVE_FILEPATH);
    menuButtonInactive = new Texture(MENU_INACTIVE_FILEPATH);

    // Calculate x-coordinates for screen items
    gameOverX = getCentrePointX(gameOver.getWidth());
    menuButtonX = getCentrePointX(menuButtonInactive.getWidth());
  }

  @Override
  public void render(float delta) {
    super.render(delta);

    camera.update();
    game.batch.begin();

    drawGameOverTitle();
    renderExitButton();

    game.batch.end();
  }

  private void renderExitButton() {
    if (isTouchingMenuButton(menuButtonX) && inputIsTouched()) {
      logger.info("Switching to main menu screen");
      switchScreen(new LevelMenuScreen(game));

    } else if (isTouchingMenuButton(menuButtonX)) {
      drawMenuButton(menuButtonX, true);

    } else {
      drawMenuButton(menuButtonX, false);

    }
  }

  private void drawMenuButton(float x, boolean active) {
    Texture texture = active ? this.menuButtonActive : this.menuButtonInactive;
    game.batch.draw(texture, x, (float) GameOverScreen.MENU_BUTTON_Y);
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
