package com.td.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Logger;
import com.td.game.Config;
import com.td.game.TowerDefenseGame;

public class LevelMenuScreen extends AbstractScreen {

  private final static Logger logger = new Logger("LevelMenuScreen", Logger.INFO);

  private static final int TITLE_Y = 600;
  private static final int LEVEL_ONE_SELECTION_Y = 250;
  private static final int LEVEL_TWO_SELECTION_Y = 250;
  private static final int LEVEL_THREE_SELECTION_Y = 250;

  private final int titleX;
  private final int levelOneSelectionX;
  private final int levelTwoSelectionX;
  private final int levelThreeSelectionX;

  private Texture title;
  private Texture levelOneSelectionInactive;
  private Texture levelOneSelectionActive;
  private Texture levelTwoSelectionInactive;
  private Texture levelTwoSelectionActive;
  private Texture levelThreeSelectionInactive;
  private Texture levelThreeSelectionActive;

  /**
   * Default constructor for MainMenuScreen.
   */
  public LevelMenuScreen(TowerDefenseGame game) {
    super(game);

    // Load images to texture
    this.title = new Texture("core/assets/fonts/level_select_title.png");
    this.levelOneSelectionInactive = new Texture("core/assets/fonts/level1_selection_inactive.png");
    this.levelOneSelectionActive = new Texture("core/assets/fonts/level1_selection_active.png");
    this.levelTwoSelectionInactive = new Texture("core/assets/fonts/level2_selection_inactive.png");
    this.levelTwoSelectionActive = new Texture("core/assets/fonts/level2_selection_active.png");
    this.levelThreeSelectionInactive = new Texture(
        "core/assets/fonts/level3_selection_inactive.png");
    this.levelThreeSelectionActive = new Texture("core/assets/fonts/level3_selection_active.png");

    // Calculate x-coordinates for screen items
    this.titleX = getCentrePoint(this.title.getWidth());
    this.levelOneSelectionX = getLevelOneCentrePoint(this.levelOneSelectionInactive.getWidth());
    this.levelTwoSelectionX = getLevelTwoCentrePoint(this.levelTwoSelectionInactive.getWidth());
    this.levelThreeSelectionX = getLevelThreeCentrePoint(
        this.levelThreeSelectionInactive.getWidth());
  }

  @Override
  public void render(float delta) {
    super.render(delta);

    drawLevelSelectTitle();

    if (isTouchingLevelOne(levelOneSelectionX) && Gdx.input.justTouched()) {
      logger.info("Exiting level menu screen");
      safeExit();
    } else if (isTouchingLevelOne(levelOneSelectionX)) {
      drawLevelOne(levelOneSelectionX, LEVEL_ONE_SELECTION_Y, true);
    } else {
      drawLevelOne(levelOneSelectionX, LEVEL_ONE_SELECTION_Y, false);
    }

    if (isTouchingLevelTwo(levelTwoSelectionX) && Gdx.input.justTouched()) {
      logger.info("Exiting level menu screen");
      safeExit();
    } else if (isTouchingLevelTwo(levelTwoSelectionX)) {
      drawLevelTwo(levelTwoSelectionX, LEVEL_TWO_SELECTION_Y, true);
    } else {
      drawLevelTwo(levelTwoSelectionX, LEVEL_TWO_SELECTION_Y, false);
    }

    if (isTouchingLevelThree(levelThreeSelectionX) && Gdx.input.justTouched()) {
      logger.info("Exiting level menu screen");
      safeExit();
    } else if (isTouchingLevelThree(levelThreeSelectionX)) {
      drawLevelThree(levelThreeSelectionX, LEVEL_THREE_SELECTION_Y, true);
    } else {
      drawLevelThree(levelThreeSelectionX, LEVEL_THREE_SELECTION_Y, false);
    }

    this.game.batch.end();
  }

  private boolean isTouchingLevelOne(int x) {
    return getInputX() < x + levelOneSelectionInactive.getWidth()
        && getInputX() > x
        && getInputY() < LEVEL_ONE_SELECTION_Y + levelOneSelectionInactive.getHeight()
        && getInputY() > LEVEL_ONE_SELECTION_Y;
  }

  private void drawLevelOne(float x, float y, boolean active) {
    Texture texture = active ? this.levelOneSelectionActive : this.levelOneSelectionInactive;
    game.batch.draw(texture, x, y);
  }

  private boolean isTouchingLevelTwo(int x) {
    return getInputX() < x + levelTwoSelectionInactive.getWidth()
        && getInputX() > x
        && getInputY() < LEVEL_TWO_SELECTION_Y + levelTwoSelectionInactive.getHeight()
        && getInputY() > LEVEL_TWO_SELECTION_Y;
  }

  private void drawLevelTwo(float x, float y, boolean active) {
    Texture texture = active ? this.levelTwoSelectionActive : this.levelTwoSelectionInactive;
    game.batch.draw(texture, x, y);
  }

  private boolean isTouchingLevelThree(int x) {
    return getInputX() < x + levelThreeSelectionInactive.getWidth()
        && getInputX() > x
        && getInputY() < LEVEL_THREE_SELECTION_Y + levelThreeSelectionInactive.getHeight()
        && getInputY() > LEVEL_THREE_SELECTION_Y;
  }

  private void drawLevelThree(float x, float y, boolean active) {
    Texture texture = active ? this.levelThreeSelectionActive : this.levelThreeSelectionInactive;
    game.batch.draw(texture, x, y);
  }

  private void drawLevelSelectTitle() {
    game.batch.draw(title, titleX, TITLE_Y);
  }

  private int getLevelOneCentrePoint(int width) {
    return (Config.SCREEN_WIDTH / 4) - (width / 2);
  }

  private int getLevelTwoCentrePoint(int width) {
    return (Config.SCREEN_WIDTH / 2) - (width / 2);
  }

  private int getLevelThreeCentrePoint(int width) {
    return ((Config.SCREEN_WIDTH / 4) * 3) - (width / 2);
  }

  @Override
  public String toString() {
    return "Level menu screen";
  }
}
