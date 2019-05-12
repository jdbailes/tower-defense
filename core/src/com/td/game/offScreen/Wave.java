package com.td.game.offScreen;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.td.game.onScreen.Enemy;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Manages the wave of enemies in the game.
 */
public class Wave {

  private static final float SPAWN_X = -64;
  private static final float SPAWN_Y = 476;
  private static final Random RANDOM = new Random();

  private List<Enemy> enemies = new ArrayList<>();
  private int waveSize = 100;

  /**
   * Creates a new wave containing a single enemy.
   */
  public Wave() {
    this.enemies.add(new Enemy(SPAWN_X, SPAWN_Y));
  }

  /**
   * Simple accessor method for the list of enemies.
   *
   * @return the list of enemies (i.e. the wave).
   */
  public List<Enemy> getEnemies() {
    return enemies;
  }

  public void spawnEnemy(float spawnProbability) {
    float randomFloat = RANDOM.nextFloat();    // Determine a spawn probability

    // Spawns enemies onto the map
    if (randomFloat < spawnProbability) {
      addEnemy();
    }
  }

  /**
   * Checks that the max wave size will not be exceeded by the addition of a new enemy. Creates a
   * new enemy and adds it to the wave.
   */
  private void addEnemy() {
    if (waveSize > 0) {
      if (enemies.size() < this.waveSize) {
        // Ensures enemies don't spawn on top of each other
        if (enemies.size() > 0 && (enemies.get(enemies.size() - 1).getX() > 64)) {
          // Pre-defined spawn positions for all enemies on the map
          int spawnX = -64; // 0 - 64
          int spawnY = 476; // 540 - 64

          Enemy enemy = new Enemy(spawnX, spawnY);
          enemies.add(enemy);
          waveSize--;
        }
      }
    }
  }

  /**
   * Checks for enemies that have reached the end of the map and removes them from the wave.
   */
  void cleanUp() {
    // Filters out enemies that have left the screen
    List<Enemy> cleanedEnemies = this.enemies.stream()
        .filter(enemy -> !enemy.isDead() && !enemy.isLost())
        .collect(Collectors.toList());

    this.enemies = cleanedEnemies;
  }

  /**
   * Updates the position of all enemies in the wave.
   *
   * @param delta a given delta to be added to the x-positions.
   */
  public void updatePositions(float delta) {
    enemies.forEach(e -> e.updateX(delta));
  }

  /**
   * Renders the Sprite on the SpriteBatch.
   *
   * @param batch SpriteBatch to be rendered onto
   */
  public void draw(SpriteBatch batch) {
    enemies.forEach(enemy -> enemy.draw(batch));
  }

  public void draw(SpriteBatch batch, ShapeRenderer renderer) {
    enemies.forEach(enemy -> enemy.draw(batch, renderer));
  }
}
