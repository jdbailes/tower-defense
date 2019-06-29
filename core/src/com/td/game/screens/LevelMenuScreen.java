package com.td.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Logger;
import com.td.game.Config;
import com.td.game.TowerDefenseGame;

/**
 * Serves the Level Menu screen in the game.
 *
 * @author josephbailey
 */
public class LevelMenuScreen extends AbstractScreen {

  private static final Logger logger = new Logger("LevelMenuScreen", Logger.INFO);
  private static final String PREF = "profile";

  // All file paths for the textures of all screen components
  private static final String TITLE_FILEPATH = "ui/level_select_title.png";
  private static final String LEVEL_ONE_INACTIVE_FILEPATH = "ui/level1_selection_inactive.png";
  private static final String LEVEL_ONE_ACTIVE_FILEPATH = "ui/level1_selection_active.png";
  private static final String LEVEL_ONE_LOCKED_FILEPATH = "ui/level1_selection_locked.png";
  private static final String LEVEL_TWO_INACTIVE_FILEPATH = "ui/level2_selection_inactive.png";
  private static final String LEVEL_TWO_ACTIVE_FILEPATH = "ui/level2_selection_active.png";
  private static final String LEVEL_TWO_LOCKED_FILEPATH = "ui/level2_selection_locked.png";
  private static final String LEVEL_THREE_INACTIVE_FILEPATH = "ui/level3_selection_inactive.png";
  private static final String LEVEL_THREE_ACTIVE_FILEPATH = "ui/level3_selection_active.png";
  private static final String LEVEL_THREE_LOCKED_FILEPATH = "ui/level3_selection_locked.png";

  // y-positions of all screen components
  private static final int TITLE_Y = 600;
  private static final int LEVEL_ONE_SELECTION_Y = 250;
  private static final int LEVEL_TWO_SELECTION_Y = 250;
  private static final int LEVEL_THREE_SELECTION_Y = 250;

  // x-positions of all screen components
  private final int titleX;
  private final int levelOneSelectionX;
  private final int levelTwoSelectionX;
  private final int levelThreeSelectionX;

  // All textures for screen components
  private final Texture title;
  private final Texture levelOneSelectionInactive;
  private final Texture levelOneSelectionActive;
  private final Texture levelOneSelectionLocked;
  private final Texture levelTwoSelectionInactive;
  private final Texture levelTwoSelectionActive;
  private final Texture levelTwoSelectionLocked;
  private final Texture levelThreeSelectionInactive;
  private final Texture levelThreeSelectionActive;
  private final Texture levelThreeSelectionLocked;

  /**
   * Default constructor for MainMenuScreen.
   */
  LevelMenuScreen(TowerDefenseGame game) {
    super(game);

    // Load images to texture
    title = new Texture(TITLE_FILEPATH);

    levelOneSelectionInactive = new Texture(LEVEL_ONE_INACTIVE_FILEPATH);
    levelOneSelectionActive = new Texture(LEVEL_ONE_ACTIVE_FILEPATH);
    levelOneSelectionLocked = new Texture(LEVEL_ONE_LOCKED_FILEPATH);

    levelTwoSelectionInactive = new Texture(LEVEL_TWO_INACTIVE_FILEPATH);
    levelTwoSelectionActive = new Texture(LEVEL_TWO_ACTIVE_FILEPATH);
    levelTwoSelectionLocked = new Texture(LEVEL_TWO_LOCKED_FILEPATH);

    levelThreeSelectionInactive = new Texture(LEVEL_THREE_INACTIVE_FILEPATH);
    levelThreeSelectionActive = new Texture(LEVEL_THREE_ACTIVE_FILEPATH);
    levelThreeSelectionLocked = new Texture(LEVEL_THREE_LOCKED_FILEPATH);

    // Calculate x-coordinates for screen items
    titleX = getCentrePointX(this.title.getWidth());
    levelOneSelectionX = getLevelOneCentrePoint(this.levelOneSelectionInactive.getWidth());
    levelTwoSelectionX = getLevelTwoCentrePoint(this.levelTwoSelectionInactive.getWidth());
    levelThreeSelectionX = getLevelThreeCentrePoint(
        this.levelThreeSelectionInactive.getWidth());
  }

  @Override
  public void render(float delta) {
    super.render(delta);

    camera.update();
    game.batch.begin();

    drawLevelSelectTitle();

    Preferences preferences = Gdx.app.getPreferences(PREF);
    preferences.putBoolean("newGame", false);
    preferences.flush();

    if (preferences.getBoolean("levelOneUnlocked")) {
      if (isTouchingLevelOne(levelOneSelectionX) && Gdx.input.justTouched()) {
        logger.info("Switching to game screen [level 1]");
        switchScreen(new LoadingScreen(game, 1));

      } else if (isTouchingLevelOne(levelOneSelectionX)) {
        drawLevelOne(levelOneSelectionX, levelOneSelectionActive);

      } else {
        drawLevelOne(levelOneSelectionX, levelOneSelectionInactive);

      }
    } else {
      drawLevelOne(levelOneSelectionX, levelOneSelectionLocked);

    }

    if (preferences.getBoolean("levelTwoUnlocked")) {
      if (isTouchingLevelTwo(levelTwoSelectionX) && Gdx.input.justTouched()) {
        logger.info("Switching to game screen [level 2]");
        switchScreen(new LoadingScreen(game, 2));

      } else if (isTouchingLevelTwo(levelTwoSelectionX)) {
        drawLevelTwo(levelTwoSelectionX, levelTwoSelectionActive);

      } else {
        drawLevelTwo(levelTwoSelectionX, levelTwoSelectionInactive);

      }
    } else {
      drawLevelTwo(levelTwoSelectionX, levelTwoSelectionLocked);

    }

    if (preferences.getBoolean("levelThreeUnlocked")) {
      if (isTouchingLevelThree(levelThreeSelectionX) && Gdx.input.justTouched()) {
        logger.info("Switching to game screen [level 3]");
        switchScreen(new LoadingScreen(game, 3));

      } else if (isTouchingLevelThree(levelThreeSelectionX)) {
        drawLevelThree(levelThreeSelectionX, levelThreeSelectionActive);

      } else {
        drawLevelThree(levelThreeSelectionX, levelThreeSelectionInactive);

      }
    } else {
      drawLevelThree(levelThreeSelectionX, levelThreeSelectionLocked);

    }

    game.batch.end();
  }

  private boolean isTouchingLevelOne(int x) {
    return getInputX() < x + levelOneSelectionInactive.getWidth()
        && getInputX() > x
        && getInputY() < LEVEL_ONE_SELECTION_Y + levelOneSelectionInactive.getHeight()
        && getInputY() > LEVEL_ONE_SELECTION_Y;
  }

  private void drawLevelOne(float x, Texture texture) {
    game.batch.draw(texture, x, (float) LevelMenuScreen.LEVEL_ONE_SELECTION_Y);
  }

  private boolean isTouchingLevelTwo(int x) {
    return getInputX() < x + levelTwoSelectionInactive.getWidth()
        && getInputX() > x
        && getInputY() < LEVEL_TWO_SELECTION_Y + levelTwoSelectionInactive.getHeight()
        && getInputY() > LEVEL_TWO_SELECTION_Y;
  }

  private void drawLevelTwo(float x, Texture texture) {
    game.batch.draw(texture, x, (float) LevelMenuScreen.LEVEL_TWO_SELECTION_Y);
  }

  private boolean isTouchingLevelThree(int x) {
    return getInputX() < x + levelThreeSelectionInactive.getWidth()
        && getInputX() > x
        && getInputY() < LEVEL_THREE_SELECTION_Y + levelThreeSelectionInactive.getHeight()
        && getInputY() > LEVEL_THREE_SELECTION_Y;
  }

  private void drawLevelThree(float x, Texture texture) {
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
