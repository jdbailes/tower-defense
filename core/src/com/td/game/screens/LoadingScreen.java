package com.td.game.screens;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.td.game.Config;
import com.td.game.TowerDefenseGame;

/**
 * Serves the Main Menu screen in the game.
 *
 * @author josephbailey
 */
public class LoadingScreen extends AbstractScreen {

  private static final String TITLE_PATH = "ui/loading_title.png";

  private OrthographicCamera camera;

  private final Texture title;
  private final int titleX;
  private final int titleY;

  private final int level;

  LoadingScreen(TowerDefenseGame game, int level) {
    super(game);

    this.level = level;

    // Load images to texture
    title = new Texture(TITLE_PATH);

    // Setup the camera
    camera = new OrthographicCamera();
    camera.setToOrtho(false, Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);
    camera.update();

    // Calculate x-coordinates for screen items
    titleX = getCentrePointX(this.title.getWidth());
    titleY = getCentrePointY(this.title.getHeight());
  }

  @Override
  public void show() {
    game.getAssetManager().load(Config.getTiledMapFilepath(this.level), TiledMap.class);

    camera = new OrthographicCamera();
    camera.position.set(Config.SCREEN_WIDTH / 2, Config.SCREEN_HEIGHT / 2, 0);
    camera.update();
  }

  @Override
  public void render(float delta) {
    super.render(delta);

    camera.update();
    game.batch.begin();

    if (game.getAssetManager().update()) {
      switchScreen(new GameScreen(this.game, this.level));
    }

    drawTitle();

    game.batch.end();
  }

  private void drawTitle() {
    game.batch.draw(title, titleX, titleY);
  }
}
