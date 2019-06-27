package com.td.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Logger;
import com.td.game.TowerDefenseGame;

/**
 * Serves the Main Menu screen in the game.
 */
public class LevelCompleteScreen extends AbstractScreen {

  private final static Logger logger = new Logger("LevelCompleteScreen", Logger.INFO);

  private static final int LEVEL_COMPLETE_Y = 620;
  private static final int MENU_BUTTON_Y = 100;

  private final int levelCompleteX;
  private final int menuButtonX;

  private Texture levelComplete;
  private Texture menuButtonActive;
  private Texture menuButtonInactive;

  public LevelCompleteScreen(final TowerDefenseGame game, UserConfig userConfig) {
    super(game, userConfig);

    // Load images to texture
    this.levelComplete = new Texture("ui/level_complete_title.png");
    this.menuButtonActive = new Texture("ui/menu_button_active.png");
    this.menuButtonInactive = new Texture("ui/menu_button_inactive.png");

    // Calculate x-coordinates for screen items
    this.levelCompleteX = getCentrePointX(levelComplete.getWidth());
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

    drawLevelCompleteTitle();

    // Render exit button
    if (isTouchingMenuButton(menuButtonX) && Gdx.input.justTouched()) {
      logger.info("Switching to main menu screen");
      switchScreen(new LevelMenuScreen(game,userConfig));
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

  private void drawLevelCompleteTitle() {
    game.batch.draw(levelComplete, levelCompleteX, LEVEL_COMPLETE_Y);
  }

  @Override
  public String toString() {
    return "Game over screen";
  }

}
