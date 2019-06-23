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
  LevelMenuScreen(TowerDefenseGame game) {
    super(game);

    // TODO Move to constants.

    // Load images to texture
    this.title = new Texture("ui/level_select_title.png");
    this.levelOneSelectionInactive = new Texture("ui/level1_selection_inactive.png");
    this.levelOneSelectionActive = new Texture("ui/level1_selection_active.png");
    this.levelTwoSelectionInactive = new Texture("ui/level2_selection_inactive.png");
    this.levelTwoSelectionActive = new Texture("ui/level2_selection_active.png");
    this.levelThreeSelectionInactive = new Texture("ui/level3_selection_inactive.png");
    this.levelThreeSelectionActive = new Texture("ui/level3_selection_active.png");

    // Calculate x-coordinates for screen items
    this.titleX = getCentrePointX(this.title.getWidth());
    this.levelOneSelectionX = getLevelOneCentrePoint(this.levelOneSelectionInactive.getWidth());
    this.levelTwoSelectionX = getLevelTwoCentrePoint(this.levelTwoSelectionInactive.getWidth());
    this.levelThreeSelectionX = getLevelThreeCentrePoint(
        this.levelThreeSelectionInactive.getWidth());
  }

  @Override
  public void render(float delta) {
    super.render(delta);

    this.camera.update();
    this.game.batch.begin();

    drawLevelSelectTitle();

    if (isTouchingLevelOne(levelOneSelectionX) && Gdx.input.justTouched()) {
      logger.info("Switching to game screen [level 1]");
      switchScreen(new LoadingScreen(game, 1));
    } else if (isTouchingLevelOne(levelOneSelectionX)) {
      drawLevelOne(levelOneSelectionX, true);
    } else {
      drawLevelOne(levelOneSelectionX, false);
    }

    if (isTouchingLevelTwo(levelTwoSelectionX) && Gdx.input.justTouched()) {
      logger.info("Switching to game screen [level 2]");
      switchScreen(new LoadingScreen(game, 2));
    } else if (isTouchingLevelTwo(levelTwoSelectionX)) {
      drawLevelTwo(levelTwoSelectionX, true);
    } else {
      drawLevelTwo(levelTwoSelectionX, false);
    }

    if (isTouchingLevelThree(levelThreeSelectionX) && Gdx.input.justTouched()) {
      logger.info("Switching to game screen [level 3]");
      switchScreen(new LoadingScreen(game, 3));
    } else if (isTouchingLevelThree(levelThreeSelectionX)) {
      drawLevelThree(levelThreeSelectionX, true);
    } else {
      drawLevelThree(levelThreeSelectionX, false);
    }

    this.game.batch.end();
  }

  private boolean isTouchingLevelOne(int x) {
    return getInputX() < x + levelOneSelectionInactive.getWidth()
        && getInputX() > x
        && getInputY() < LEVEL_ONE_SELECTION_Y + levelOneSelectionInactive.getHeight()
        && getInputY() > LEVEL_ONE_SELECTION_Y;
  }

  private void drawLevelOne(float x, boolean active) {
    Texture texture = active ? this.levelOneSelectionActive : this.levelOneSelectionInactive;
    game.batch.draw(texture, x, (float) LevelMenuScreen.LEVEL_ONE_SELECTION_Y);
  }

  private boolean isTouchingLevelTwo(int x) {
    return getInputX() < x + levelTwoSelectionInactive.getWidth()
        && getInputX() > x
        && getInputY() < LEVEL_TWO_SELECTION_Y + levelTwoSelectionInactive.getHeight()
        && getInputY() > LEVEL_TWO_SELECTION_Y;
  }

  private void drawLevelTwo(float x, boolean active) {
    Texture texture = active ? this.levelTwoSelectionActive : this.levelTwoSelectionInactive;
    game.batch.draw(texture, x, (float) LevelMenuScreen.LEVEL_TWO_SELECTION_Y);
  }

  private boolean isTouchingLevelThree(int x) {
    return getInputX() < x + levelThreeSelectionInactive.getWidth()
        && getInputX() > x
        && getInputY() < LEVEL_THREE_SELECTION_Y + levelThreeSelectionInactive.getHeight()
        && getInputY() > LEVEL_THREE_SELECTION_Y;
  }

  private void drawLevelThree(float x, boolean active) {
    Texture texture = active ? this.levelThreeSelectionActive : this.levelThreeSelectionInactive;
    game.batch.draw(texture, x, (float) LevelMenuScreen.LEVEL_THREE_SELECTION_Y);
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
