package com.td.game.offScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.td.game.onScreen.Base;

/**
 * Level class stores the state of a level. By having this in a Level class rather than the
 * GameManager, it means that different levels could be configured in the future.
 */
public class Level {

  private static final float SPAWN_PROBABILITY = 0.01f;  // The the chance of an enemy spawning in a frame
  private static final float BASE_X_POSITION = 1750;    // The default x-position of the base
  private static final float BASE_Y_POSITION = 525;     // The default y-position of the base

  // The core on-screen components of the level
  private final Wave wave;
  private final Fleet fleet;
  private final Base base;

  // Flag is flipped once the base is destroyed
  private boolean baseDestroyed = false;

  /**
   * Constructor for a Level.
   */
  public Level() {
    this.wave = new Wave();
    this.fleet = new Fleet();
    this.base = new Base(BASE_X_POSITION, BASE_Y_POSITION);
  }

  /**
   * Method invoked by GameManager with the rendering of each frame.
   */
  public boolean update() {
    this.wave.cleanUp();
    this.wave.spawnEnemy(SPAWN_PROBABILITY);
    this.wave.updatePositions(100 * Gdx.graphics.getDeltaTime());
    this.wave.updateHealthBars();

    this.fleet.run(this.wave);

    if (!this.baseDestroyed) {
      this.base.run(this.wave);

      // If the base health drops to 0 or below, the base is flagged as destroyed
      if (this.base.getHealth() <= 0) {
        this.baseDestroyed = true;
        this.wave.getEnemies().forEach(enemy -> enemy.setAttackingBase(false));
      }
    }

    return this.baseDestroyed;
  }

  /**
   * Encapsulates addShip() method in Fleet.
   *
   * @param x x-position of the ship to be added
   * @param y y-position of the ship to be added
   */
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
    if (!baseDestroyed) {
      this.base.draw(batch);
    }
  }

  /**
   * Debug method invoked by GameManager and calls rendering methods on wave and ship as well as the
   * collision zones which will also be displayed on the screen
   *
   * @param batch SpriteBatch passed from GameManager ensures all components are drawn on the same
   * batch
   * @param renderer ShapeRenderer renders the collision zones on the screen for debugging
   */
  public void draw(SpriteBatch batch, ShapeRenderer renderer) {
    this.wave.draw(batch, renderer);
    this.fleet.draw(batch, renderer);
    if (!baseDestroyed) {
      this.base.draw(batch, renderer);
    }
  }
}
