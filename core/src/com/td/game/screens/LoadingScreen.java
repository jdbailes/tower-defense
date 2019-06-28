package com.td.game.screens;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.td.game.Config;
import com.td.game.TowerDefenseGame;

public class LoadingScreen extends AbstractScreen {

  private static final String TITLE_PATH = "ui/loading_title.png";

  private OrthographicCamera camera;

  private Texture title;
  private final int titleX;
  private final int titleY;

  private int level;

  LoadingScreen(TowerDefenseGame game, int level) {
    super(game);

    this.level = level;

    // Load images to texture
    this.title = new Texture(TITLE_PATH);

    // Setup the camera
    this.camera = new OrthographicCamera();
    this.camera.setToOrtho(false, Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);
    this.camera.update();

    // Calculate x-coordinates for screen items
    this.titleX = getCentrePointX(this.title.getWidth());
    this.titleY = getCentrePointY(this.title.getHeight());
  }

  @Override
  public void show() {
    this.game.getAssetManager().load(Config.getLevelFilepath(this.level), TiledMap.class);

    this.camera = new OrthographicCamera();
    this.camera.position.set(Config.SCREEN_WIDTH / 2, Config.SCREEN_HEIGHT / 2, 0);
    this.camera.update();
  }

  @Override
  public void render(float delta) {
    super.render(delta);

    this.camera.update();
    this.game.batch.begin();

    if (this.game.getAssetManager().update()) {
      this.game.setScreen(new GameScreen(this.game, this.level));
    }

    drawTitle();

    this.game.batch.end();
  }

  private void drawTitle() {
    game.batch.draw(title, titleX, titleY);
  }
}
