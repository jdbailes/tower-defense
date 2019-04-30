package com.td.game.model;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Manages the wave of enemies in the game.
 */
class Wave {

  private List<Enemy> enemies = new ArrayList<>();  // A list of enemies in a wave
  private Integer waveSize;                         // Size of the wave

  /**
   * Creates a new wave containing a single enemy.
   *
   * @param waveSize the maximum size the wave can be.
   */
  Wave(int waveSize, float spawnX, float spawnY) {
    this.waveSize = waveSize;
    this.enemies.add(new Enemy(spawnX, spawnY));
  }

  /**
   * Simple accessor method for the list of enemies.
   *
   * @return the list of enemies (i.e. the wave).
   */
  public List<Enemy> getEnemies() {
    return enemies;
  }

  /**
   * Checks that the max wave size will not be exceeded by the addition of a new enemy. Creates a
   * new enemy and adds it to the wave.
   */
  void addEnemy() {
    if (enemies.size() < this.waveSize) {
      if (enemies.get(enemies.size() - 1).getXPos()
          > 64) { // Ensures enemies don't spawn on top of each other
        // Pre-defined spawn positions for all enemies on the map
        // 0 - 64
        int spawnX = -64;
        // 540 - 64
        int spawnY = 476;
        Enemy enemy = new Enemy(spawnX, spawnY);
        enemies.add(enemy);
      }
    }
  }

  /**
   * Checks for enemies that have reached the end of the map and removes them from the wave.
   */
  void cleanUp() {
    // Filters out enemies that have left the screen
    List<Enemy> cleanedEnemies = this.enemies.stream().filter(enemy -> enemy.getXPos() < 1920 - 64)
        .collect(Collectors.toList());

    // Prevent infinite spawning
    if (this.enemies.size() != cleanedEnemies.size()) {
      this.enemies = cleanedEnemies;
      waveSize = cleanedEnemies.size();
    }
  }

  /**
   * Updates the position of all enemies in the wave.
   *
   * @param delta a given delta to be added to the x-positions.
   */
  void updatePositions(float delta) {
    enemies.forEach(e -> e.updateXPos(delta));
  }

  /**
   * Renders the Sprite on the SpriteBatch.
   *
   * @param batch SpriteBatch to be rendered onto
   */
  void batchDraw(SpriteBatch batch) {
    enemies.forEach(enemy -> enemy.batchDraw(batch));
  }
}
