package com.td.game.model;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Manages the wave of enemies in the game.
 */
public class Wave {

  private List<Enemy> enemies = new ArrayList<>();  // A list of enemies in a wave
  private Integer waveSize;                         // Size of the wave

  // Pre-defined spawn positions for all enemies on the map
  private final static int SPAWN_X_POS = -64; // 0 - 64
  private final static int SPAWN_Y_POS = 476; // 540 - 64

  /**
   * Creates a new wave containing a single enemy.
   *
   * @param waveSize the maximum size the wave can be.
   */
  public Wave(int waveSize) {
    this.waveSize = waveSize;
    this.enemies.add(new Enemy(SPAWN_X_POS, SPAWN_Y_POS));
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
  public void addEnemy() {
    if (enemies.size() < this.waveSize) {
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
  public void updatePositions(float delta) {
    enemies.forEach(e -> e.updateXPos(delta));
  }

  public void batchDraw(SpriteBatch batch) {
    enemies.forEach(enemy -> enemy.batchDraw(batch));
  }
}
