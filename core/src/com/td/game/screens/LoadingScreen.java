package com.td.game.screens;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.td.game.Config;
import com.td.game.TowerDefenseGame;

public class LoadingScreen extends AbstractScreen {

  private Viewport viewport;
  private OrthographicCamera camera;

  private Texture title;
  private final int titleX;
  private final int titleY;

  public LoadingScreen(TowerDefenseGame game) {
    super(game);

    // Load images to texture
    this.title = new Texture("fonts/loading_title.png");

    // Setup the camera
    this.camera = new OrthographicCamera();
    this.camera.setToOrtho(false, Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);
    this.camera.update();

    // Calculate x-coordinates for screen items
    this.titleX = getCentrePointX(this.title.getWidth());
    this.titleY = getCentrePointY(this.title.getHeight());
  }

  @Override
  public void resize(int width, int height) {
    viewport.update(width, height);
  }

  @Override
  public void show() {
    game.getAssetManager().load("Level_1.tmx", TiledMap.class);

    camera = new OrthographicCamera();
    camera.position.set(Config.SCREEN_WIDTH / 2, Config.SCREEN_HEIGHT / 2, 0);
    camera.update();
    viewport = new FitViewport(Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT, camera);
  }

  @Override
  public void render(float delta) {
    super.render(delta);

    this.camera.update();
    this.game.batch.begin();

    update();
    drawTitle();

    this.game.batch.end();
  }

  private void drawTitle() {
    game.batch.draw(title, titleX, titleY);
  }

  private void update() {
    if (game.getAssetManager().update()) {
      game.setScreen(new GameScreen(game));
    }
  }
}
