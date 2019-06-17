package com.td.game.screens;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.td.game.Config;
import com.td.game.TowerDefenseGame;

public class LoadingScreen extends AbstractScreen {

  private ShapeRenderer shapeRenderer;
  private Viewport viewport;
  private OrthographicCamera camera;
  private final TowerDefenseGame game;

  public LoadingScreen(TowerDefenseGame game) {
    super(game);
    this.game = game;
  }

  @Override
  public void resize(int width, int height) {
    viewport.update(width, height);
  }

  @Override
  public void show() {
    game.getAssetManager().load("tester-tilemap.tmx", TiledMap.class);

    camera = new OrthographicCamera();
    camera.position.set(Config.SCREEN_WIDTH / 2, Config.SCREEN_HEIGHT / 2, 0);
    camera.update();
    viewport = new FitViewport(Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT, camera);
    shapeRenderer = new ShapeRenderer();
  }

  @Override
  public void render(float delta) {
    super.render(delta);

    this.camera.update();
    this.game.batch.begin();

    update();
    draw();

    this.game.batch.end();
  }

  @Override
  public void dispose() {
    shapeRenderer.dispose();
  }

  private void update() {
    if (game.getAssetManager().update()) {
      game.setScreen(new GameScreen(game));
    }
  }

  private void draw() {
    shapeRenderer.setProjectionMatrix(camera.projection);
    shapeRenderer.setTransformMatrix(camera.view);
    shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
    shapeRenderer.setColor(Color.WHITE);
    shapeRenderer.end();
  }
}
