package com.td.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;
import com.td.game.Config;
import com.td.game.TowerDefenseGame;

public class MainMenuScreen implements Screen {

  private static final int TITLE_Y = 800;
  private static final int RESUME_BUTTON_Y = 500;
  private static final int NEW_GAME_BUTTON_Y = 300;
  private static final int EXIT_BUTTON_Y = 100;

  private final int titleX;
  private final int resumeButtonX;
  private final int newGameButtonX;
  private final int exitButtonX;

  private TowerDefenseGame game;
  private OrthographicCamera camera;
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
    this.game = game;

    // Load images to texture
    this.title = new Texture("core/assets/fonts/game_title.png");
    this.resumeButtonActive = new Texture("core/assets/fonts/resume_button_active.png");
    this.resumeButtonInactive = new Texture("core/assets/fonts/resume_button_inactive.png");
    this.newGameButtonActive = new Texture("core/assets/fonts/newgame_button_active.png");
    this.newGameButtonInactive = new Texture("core/assets/fonts/newgame_button_inactive.png");
    this.exitButtonActive = new Texture("core/assets/fonts/exit_button_active.png");
    this.exitButtonInactive = new Texture("core/assets/fonts/exit_button_inactive.png");

    // Calculate x-coordinates for screen items
    this.titleX = getCentrePoint(title.getWidth());
    this.resumeButtonX = getCentrePoint(resumeButtonInactive.getWidth());
    this.newGameButtonX = getCentrePoint(newGameButtonInactive.getWidth());
    this.exitButtonX = getCentrePoint(exitButtonInactive.getWidth());

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
    // Render the tilemap
    this.camera.update();

    // Clear the screen
    Gdx.gl.glClearColor(0, 0, 0.2f, 1);
    Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    game.batch.begin();

    drawTitle();

    if (isTouchingExitButton(exitButtonX) && Gdx.input.isTouched()) {
      safeExit();
    } else if (isTouchingExitButton(exitButtonX)) {
      drawExitButton(exitButtonX, EXIT_BUTTON_Y, true);
    } else {
      drawExitButton(exitButtonX, EXIT_BUTTON_Y, false);
    }

    if (isTouchingResumeButton(resumeButtonX) && Gdx.input.isTouched()) {
      switchScreen(new GameScreen(game));
    } else if (isTouchingResumeButton(resumeButtonX)) {
      drawResumeButton(resumeButtonX, RESUME_BUTTON_Y, true);
    } else {
      drawResumeButton(resumeButtonX, RESUME_BUTTON_Y, false);
    }

    if ((isTouchingNewGameButton(newGameButtonX)) && Gdx.input.isTouched()) {
      switchScreen(new GameScreen(game));
    } else if (isTouchingNewGameButton(newGameButtonX)) {
      drawNewGameButton(newGameButtonX, NEW_GAME_BUTTON_Y, true);
    } else {
      drawNewGameButton(newGameButtonX, NEW_GAME_BUTTON_Y, false);
    }

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

  private void switchScreen(Screen screen) {
    game.setScreen(screen);
    dispose();
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

  private void safeExit() {
    Gdx.app.exit();
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

  private int getCentrePoint(int width) {
    return (Config.SCREEN_WIDTH / 2) - (width / 2);
  }
}
