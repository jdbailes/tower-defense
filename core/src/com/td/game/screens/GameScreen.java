package com.td.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.td.game.TowerDefenseGame;
import com.td.game.managers.GameManager;

/**
 * Serves the screen the game is played within.
 */
public class GameScreen implements Screen {

  private OrthographicCamera camera;
  private SpriteBatch batch;
  private GameManager manager;

  private TiledMap tiledMap;
  private TiledMapRenderer tiledMapRenderer;

  private final TowerDefenseGame game; // Game is passed into the GameScreen constructor

  GameScreen(final TowerDefenseGame game) {
    this.game = game;

    // Setup the camera
    this.camera = new OrthographicCamera();
    this.camera.setToOrtho(false, 1920, 1080);
    this.camera.update();

    this.tiledMap = new TmxMapLoader().load("tiled.tmx");
    this.tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);

    // Initialise a new SpriteBatch for this game
    this.batch = new SpriteBatch();

    // Creates a game manager
    this.manager = new GameManager(batch);
  }

  @Override
  public void show() {

  }

  @Override
  public void render(float delta) {
    Gdx.gl.glClearColor(1, 0, 0, 1);
    Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    camera.update();
    tiledMapRenderer.setView(camera);
    tiledMapRenderer.render();

    batch.setProjectionMatrix(camera.combined);
    batch.begin();

    manager.render();

    batch.end();
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
}
