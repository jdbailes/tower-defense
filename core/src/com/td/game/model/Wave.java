package com.td.game.model;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Manages the wave of enemies in the game.
 */
public class Wave {

  private List<Enemy> enemies = new ArrayList<>();
  private int maxSize;

  // Pre-defined spawn positions for all enemies on the map
  private final static int SPAWN_X_POS = -64; // 0 - 64
  private final static int SPAWN_Y_POS = 476; // 540 - 64

  /**
   * Creates a new wave containing a single enemy.
   *
   * @param maxSize the maximum size the wave can be.
   */
  public Wave(int maxSize) {
    this.maxSize = maxSize;
    this.enemies.add(new Enemy(SPAWN_X_POS, SPAWN_Y_POS));
  }

  /**
   * Checks that the max wave size will not be exceeded by the addition of a new enemy. Creates a
   * new enemy and adds it to the wave.
   */
  public void addEnemy() {
    int waveSize = enemies.size();

    if (waveSize < this.maxSize) {
      if (enemies.get(enemies.size() - 1).getXPos()
          > 64) { // Ensures enemies don't spawn on top of each other
        Enemy enemy = new Enemy(SPAWN_X_POS, SPAWN_Y_POS);
        enemies.add(enemy);
      }
    }
  }

  /**
   * Checks for enemies that have reached the end of the map and removes them from the wave.
   */
  public void cleanUp() {

    List<Enemy> cleanedEnemies = this.enemies.stream().filter(enemy -> enemy.getXPos() < 1920 - 64)
        .collect(Collectors.toList());

    if(this.enemies.size() != cleanedEnemies.size()) { // Prevent infinite spawning
      this.enemies = cleanedEnemies;
      maxSize = cleanedEnemies.size();
    }
  }

  /**
   * Updates the position of all enemies in the wave.
   *
   * @param delta a given delta to be added to the x-positions.
   */
  public void updatePositions(float delta) {
    enemies.forEach(e -> e.updateXPos(delta));
  }

  /**
   * Renders each enemy on the maps.
   *
   * @param batch a SpriteBatch passed from the calling class.
   */
  public void batchDraw(SpriteBatch batch) {
    enemies.forEach(enemy -> batch.draw(enemy.getTexture(), enemy.getXPos(), enemy.getYPos()));
  }
}
