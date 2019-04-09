package com.td.game.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.td.game.model.Enemy;
import com.td.game.model.Tower;
import com.td.game.model.Wave;
import java.util.Random;

public class GameManager {

  private Wave wave;
  private Tower tower;

  private Random random = new Random();

  private final SpriteBatch batch;

  public GameManager(SpriteBatch batch) {
    this.batch = batch;

    this.wave = new Wave(1);
    this.tower = new Tower(896, 746);
  }

  public void render() {
    cleanUp();
    updateEnemyPositions();
    draw();
    spawnEnemy();
  }

  private void scan() {
    for (Enemy enemy : this.wave.getEnemies()) {
      if (this.tower.getZone().overlaps(enemy.getZone())) {
        this.tower.setTarget(enemy);
        break;
      }
    }
  }

  private void draw() {
    // Drawns both the tower and the wave after their values have been updated
    this.wave.batchDraw(batch);
    this.tower.batchDraw(batch);
  }

  private void updateEnemyPositions() {
    // Updates the position of all enemies with a constant value
    wave.updatePositions(100 * Gdx.graphics.getDeltaTime());
  }

  private void cleanUp() {
    // Cleans up enemies that have left the map
    wave.cleanUp();
  }

  private void spawnEnemy() {
    // Determine a spawn probability
    float randomFloat = this.random.nextFloat();

    // Spawns enemies onto the map
    if (randomFloat < 0.01) { // Spawn probability could be constant/parameterised
      // Adds a new enemy to the wave
      wave.addEnemy();
    }
  }
}
