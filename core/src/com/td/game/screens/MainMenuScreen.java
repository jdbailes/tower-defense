package com.td.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Logger;
import com.td.game.TowerDefenseGame;

public class MainMenuScreen extends AbstractScreen {

  private final static Logger logger = new Logger("MainMenuScreen", Logger.INFO);

  private static final int TITLE_Y = 620;
  private static final int RESUME_BUTTON_Y = 300;
  private static final int NEW_GAME_BUTTON_Y = 200;
  private static final int EXIT_BUTTON_Y = 100;

  private final int titleX;
  private final int resumeButtonX;
  private final int newGameButtonX;
  private final int exitButtonX;

  private Texture title;
  private Texture resumeButtonActive;
  private Texture resumeButtonInactive;
  private Texture newGameButtonActive;
  private Texture newGameButtonInactive;
  private Texture exitButtonActive;
  private Texture exitButtonInactive;

  /**
   * Default constructor for MainMenuScreen.
   */
  public MainMenuScreen(TowerDefenseGame game) {
    super(game);

    // Load images to texture
    this.title = new Texture("ui/game_title.png");
    this.resumeButtonActive = new Texture("ui/resume_button_active.png");
    this.resumeButtonInactive = new Texture("ui/resume_button_inactive.png");
    this.newGameButtonActive = new Texture("ui/newgame_button_active.png");
    this.newGameButtonInactive = new Texture("ui/newgame_button_inactive.png");
    this.exitButtonActive = new Texture("ui/exit_button_active.png");
    this.exitButtonInactive = new Texture("ui/exit_button_inactive.png");

    // Calculate x-coordinates for screen items
    this.titleX = getCentrePointX(title.getWidth());
    this.resumeButtonX = getCentrePointX(resumeButtonInactive.getWidth());
    this.newGameButtonX = getCentrePointX(newGameButtonInactive.getWidth());
    this.exitButtonX = getCentrePointX(exitButtonInactive.getWidth());
  }

  @Override
  public void render(float delta) {
    super.render(delta);

    this.camera.update();
    this.game.batch.begin();

    drawTitle();

    // Render exit button
    if (isTouchingExitButton(exitButtonX) && Gdx.input.justTouched()) {
      logger.info("Exiting main menu screen");
      safeExit();
    } else if (isTouchingExitButton(exitButtonX)) {
      drawExitButton(exitButtonX, EXIT_BUTTON_Y, true);
    } else {
      drawExitButton(exitButtonX, EXIT_BUTTON_Y, false);
    }

    // Render resume button
    if (isTouchingResumeButton(resumeButtonX) && Gdx.input.justTouched()) {
      logger.info("Switching to level menu screen");
      switchScreen(new LevelMenuScreen(game));
    } else if (isTouchingResumeButton(resumeButtonX)) {
      drawResumeButton(resumeButtonX, RESUME_BUTTON_Y, true);
    } else {
      drawResumeButton(resumeButtonX, RESUME_BUTTON_Y, false);
    }

    // Render new game button
    if ((isTouchingNewGameButton(newGameButtonX)) && Gdx.input.justTouched()) {
      logger.info("Switching to level menu screen");
      switchScreen(new LevelMenuScreen(game));
    } else if (isTouchingNewGameButton(newGameButtonX)) {
      drawNewGameButton(newGameButtonX, NEW_GAME_BUTTON_Y, true);
    } else {
      drawNewGameButton(newGameButtonX, NEW_GAME_BUTTON_Y, false);
    }

    this.game.batch.end();
  }

  private void drawTitle() {
    game.batch.draw(title, titleX, TITLE_Y);
  }

  private void drawExitButton(float x, float y, boolean active) {
    Texture texture = active ? this.exitButtonActive : this.exitButtonInactive;
    game.batch.draw(texture, x, y);
  }

  private void drawResumeButton(float x, float y, boolean active) {
    Texture texture = active ? this.resumeButtonActive : this.resumeButtonInactive;
    game.batch.draw(texture, x, y);
  }

  private void drawNewGameButton(float x, float y, boolean active) {
    Texture texture = active ? this.newGameButtonActive : this.newGameButtonInactive;
    game.batch.draw(texture, x, y);
  }

  private boolean isTouchingNewGameButton(int x) {
    return getInputX() < x + newGameButtonInactive.getWidth()
        && getInputX() > x
        && getInputY() < NEW_GAME_BUTTON_Y + newGameButtonInactive.getHeight()
        && getInputY() > NEW_GAME_BUTTON_Y;
  }

  private boolean isTouchingResumeButton(int x) {
    return getInputX() < x + resumeButtonInactive.getWidth()
        && getInputX() > x
        && getInputY() < RESUME_BUTTON_Y + resumeButtonInactive.getHeight()
        && getInputY() > RESUME_BUTTON_Y;
  }

  private boolean isTouchingExitButton(int x) {
    return getInputX() < x + exitButtonInactive.getWidth()
        && getInputX() > x
        && getInputY() < EXIT_BUTTON_Y + exitButtonInactive.getHeight()
        && getInputY() > EXIT_BUTTON_Y;
  }

  @Override
  public String toString() {
    return "Main menu screen";
  }
}
