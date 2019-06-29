package com.td.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Logger;
import com.td.game.TowerDefenseGame;

/**
 * The first screen that's displayed upon starting the game application. Gives the user options of
 * resuming a game, starting a new game, or exiting the game application.
 *
 * @author joebailey
 */
public class MainMenuScreen extends AbstractScreen {

  private static final Logger logger = new Logger("MainMenuScreen", Logger.DEBUG);
  private static final String PREF = "profile";

  // All file paths for the textures of all screen components
  private static final String RESUME_ACTIVE_FILEPATH = "ui/resume_button_active.png";
  private static final String RESUME_INACTIVE_FILEPATH = "ui/resume_button_inactive.png";
  private static final String NEW_GAME_ACTIVE_FILEPATH = "ui/newgame_button_active.png";
  private static final String NEW_GAME_INACTIVE_FILEPATH = "ui/newgame_button_inactive.png";
  private static final String EXIT_ACTIVE_FILEPATH = "ui/exit_button_active.png";
  private static final String EXIT_INACTIVE_FILEPATH = "ui/exit_button_inactive.png";

  // y-positions of all screen components
  private static final String TITLE_PATH = "ui/game_title.png";
  private static final int TITLE_Y_POS = 620;
  private static final int RESUME_Y_POS = 300;
  private static final int NEW_GAME_Y_POS = 200;
  private static final int EXIT_Y_POS = 100;

  // x-positions of all screen components
  private final int titleXPos;
  private final int resumeXPos;
  private final int newGameXPos;
  private final int exitXPos;

  // All textures for screen components
  private final Texture title;
  private final Texture resumeButtonActive;
  private final Texture resumeButtonInactive;
  private final Texture newGameButtonActive;
  private final Texture newGameButtonInactive;
  private final Texture exitButtonActive;
  private final Texture exitButtonInactive;

  /**
   * Default constructor for MainMenuScreen.
   */
  public MainMenuScreen(TowerDefenseGame game) {
    super(game);

    // Load images to texture
    title = new Texture(TITLE_PATH);
    resumeButtonActive = new Texture(RESUME_ACTIVE_FILEPATH);
    resumeButtonInactive = new Texture(RESUME_INACTIVE_FILEPATH);
    newGameButtonActive = new Texture(NEW_GAME_ACTIVE_FILEPATH);
    newGameButtonInactive = new Texture(NEW_GAME_INACTIVE_FILEPATH);
    exitButtonActive = new Texture(EXIT_ACTIVE_FILEPATH);
    exitButtonInactive = new Texture(EXIT_INACTIVE_FILEPATH);

    // Calculate x-coordinates for screen items
    titleXPos = getCentrePointX(title.getWidth());
    resumeXPos = getCentrePointX(resumeButtonInactive.getWidth());
    newGameXPos = getCentrePointX(newGameButtonInactive.getWidth());
    exitXPos = getCentrePointX(exitButtonInactive.getWidth());
  }

  @Override
  public void render(float delta) {
    super.render(delta);

    camera.update();
    game.batch.begin();

    drawTitle();
    renderExitButton();

    Preferences preferences = Gdx.app.getPreferences(PREF);
    if (preferences.getBoolean("newGame")) {
      renderNewGameButton();

    } else {
      renderNewGameButton();
      renderResumeButton();

    }

    this.game.batch.end();
  }

  private void renderExitButton() {
    if (isTouchingExitButton(exitXPos) && inputIsTouched()) {
      logger.debug("Exiting main menu screen");
      safeExit();

    } else if (isTouchingExitButton(exitXPos)) {
      drawExitButton(exitXPos, exitButtonActive);

    } else {
      drawExitButton(exitXPos, exitButtonInactive);

    }
  }

  private void renderNewGameButton() {
    if ((isTouchingNewGameButton(newGameXPos)) && inputIsTouched()) {
      logger.debug("Switching to level menu screen");
      switchScreen(new LevelMenuScreen(game));

    } else if (isTouchingNewGameButton(newGameXPos)) {
      drawNewGameButton(newGameXPos, newGameButtonActive);

    } else {
      drawNewGameButton(newGameXPos, newGameButtonInactive);

    }
  }

  private void renderResumeButton() {
    if (isTouchingResumeButton(resumeXPos) && inputIsTouched()) {
      logger.debug("Switching to level menu screen");
      switchScreen(new LevelMenuScreen(game));

    } else if (isTouchingResumeButton(resumeXPos)) {
      drawResumeButton(resumeXPos, resumeButtonActive);

    } else {
      drawResumeButton(resumeXPos, resumeButtonInactive);

    }
  }

  private void drawTitle() {
    game.batch.draw(title, titleXPos, TITLE_Y_POS);
  }

  private void drawExitButton(float x, Texture texture) {
    game.batch.draw(texture, x, (float) EXIT_Y_POS);
  }

  private void drawResumeButton(float x, Texture texture) {
    game.batch.draw(texture, x, (float) RESUME_Y_POS);
  }

  private void drawNewGameButton(float x, Texture texture) {
    game.batch.draw(texture, x, (float) NEW_GAME_Y_POS);
  }

  private boolean isTouchingNewGameButton(int x) {
    return getInputX() < x + newGameButtonInactive.getWidth()
        && getInputX() > x
        && getInputY() < NEW_GAME_Y_POS + newGameButtonInactive.getHeight()
        && getInputY() > NEW_GAME_Y_POS;
  }

  private boolean isTouchingResumeButton(int x) {
    return getInputX() < x + resumeButtonInactive.getWidth()
        && getInputX() > x
        && getInputY() < RESUME_Y_POS + resumeButtonInactive.getHeight()
        && getInputY() > RESUME_Y_POS;
  }

  private boolean isTouchingExitButton(int x) {
    return getInputX() < x + exitButtonInactive.getWidth()
        && getInputX() > x
        && getInputY() < EXIT_Y_POS + exitButtonInactive.getHeight()
        && getInputY() > EXIT_Y_POS;
  }

  @Override
  public String toString() {
    return "Main menu screen";
  }
}
