package com.td.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.awt.Rectangle;

public class TowerDefenseGame extends ApplicationAdapter {

  private OrthographicCamera camera;
  private SpriteBatch batch;

  private Rectangle alien;
  private Texture alienImage;

  private static final String ALIEN_IMG = "alien.png";

  @Override
  public void create() {
    // Load the images for the alien
    alienImage = new Texture(Gdx.files.internal(ALIEN_IMG));

    camera = new OrthographicCamera();
    camera.setToOrtho(false, 1600, 960);

    batch = new SpriteBatch();

    alien = new Rectangle();
    alien.x = 20;
    alien.y = 800;
    alien.width = 64;
    alien.height = 64;
  }

  @Override
  public void render() {
    Gdx.gl.glClearColor(0, 0, 0.2f, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    camera.update();

    alien.x += 75 * Gdx.graphics.getDeltaTime();

    if (alien.x < 0) {
      alien.x = 0;
    }
    if (alien.x > 1600 - 64) {
      alien.x = 1600 - 64;
    }

    batch.setProjectionMatrix(camera.combined);
    batch.begin();
    batch.draw(alienImage, alien.x, alien.y);
    batch.end();
  }

  @Override
  public void dispose() {
    batch.dispose();
  }
}
