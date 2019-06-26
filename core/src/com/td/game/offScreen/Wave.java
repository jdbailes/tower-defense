package com.td.game.offScreen;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.td.game.onScreen.Enemy;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Manages the wave of enemies in the game.
 */
public class Wave {

  private final float spawnX;
  private final float spawnY;
  private static final Random RANDOM = new Random();

  private List<Vector2> breadCrumbs;
  private List<Enemy> enemies;
  private final int waveSize;
  private int spawnCount;
  private int enemyHealth;

  private int killCounter;

  /**
   * Creates a new wave containing a single enemy.
   */
  public Wave(List<Vector2> breadCrumbs, int waveSize, int enemyHealth) {
    this.waveSize = waveSize;
    this.enemyHealth = enemyHealth;

    this.breadCrumbs = breadCrumbs;

    this.enemies = new ArrayList<>();

    this.spawnX = breadCrumbs.get(0).x;
    this.spawnY = breadCrumbs.get(0).y;

    addEnemy(enemyHealth);
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
      addEnemy(enemyHealth);
    }

  }

  /**
   * Checks that the max wave size will not be exceeded by the addition of a new enemy. Creates a
   * new enemy and adds it to the wave.
   */
  private void addEnemy(int enemyHealth) {
    if (enemies.size() < this.waveSize) {
      // Ensures enemies don't spawn on top of each other
      if (spawnCount <= waveSize) {
        Enemy enemy = new Enemy(spawnX, spawnY, breadCrumbs.get(1), enemyHealth);
        enemies.add(enemy);
        spawnCount ++;
      } else if (enemies.get(enemies.size() - 1).getX() > 64) {
        // Pre-defined spawn positions for all enemies on the map
        Enemy enemy = new Enemy(spawnX, spawnY, breadCrumbs.get(1), enemyHealth);
        enemies.add(enemy);
        spawnCount ++;
      }
    }
  }

  /**
   * Checks for enemies that have reached the end of the map and removes them from the wave.
   */
  void cleanUp() {
    // Filters out enemies that have left the screen

    enemies.forEach(e -> {
      if (e.isDead()) {
        killCounter++;
      }
    });

    enemies = enemies.stream()
        .filter(enemy -> !enemy.isDead() && !enemy.isLost())
        .collect(Collectors.toList());
  }

  /**
   * Updates the position of all enemies in the wave.
   *
   * @param delta a given delta to be added to the x-positions.
   */
  public void updatePositions(float delta) {
    enemies.forEach(e -> {
      if (!e.isAttackingBase()) {

        // Set new breadcrumb if current breadcrumb has been reached
        if (e.hasReachedDestination()) {
          int currentIndex = breadCrumbs.indexOf(e.getDestination());
          int nextIndex = currentIndex + 1;

          if (nextIndex < breadCrumbs.size()) {
            e.setDestination(breadCrumbs.get(nextIndex));
          }
        }

        e.updatePosition(delta);
      }
    });
  }

  public boolean waveKilled() {
    return killCounter >= waveSize;
  }

  int getKillCounter() {
    return killCounter;
  }

  void updateHealthBars() {
    this.getEnemies().forEach(Enemy::updateHealthBar);
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
