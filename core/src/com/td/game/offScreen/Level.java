package com.td.game.offScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 * Level class stores the state of a level. By having this in a Level class rather than the
 * GameManager, it means that different levels could be configured in the future.
 */
public class Level {

  private static final float SPAWN_PROBABILITY = 0.1f;   // The change of an enemy spawning

  private Wave wave;
  private Fleet fleet;

  /**
   * Constructor for a Level.
   */
  public Level() {
    this.wave = new Wave();
    this.fleet = new Fleet();
  }

  /**
   * Method invoked by GameManager with the rendering of each frame.
   */
  public void update() {
    this.wave.cleanUp();
    this.wave.spawnEnemy(SPAWN_PROBABILITY);
    this.wave.updatePositions(100 * Gdx.graphics.getDeltaTime());

    this.fleet.run(this.wave);
  }

  public void addShip(int x, int y) {
    this.fleet.addShip(x, y);
  }

  /**
   * Method invoked by GameManager and calls rendering methods on wave and ship.
   *
   * @param batch SpriteBatch passed from GameManager ensures all components are drawn on the same
   * batch
   */
  public void draw(SpriteBatch batch) {
    this.wave.draw(batch);
    this.fleet.draw(batch);
  }

  public void draw(SpriteBatch batch, ShapeRenderer renderer) {
    this.wave.draw(batch, renderer);
    this.fleet.draw(batch, renderer);
  }
}
