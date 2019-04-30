package com.td.game.managers;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.td.game.model.Level;

/**
 * GameManage class will allow us to orchestrate levels & scoring within the game.
 */
public class GameManager {

  private final SpriteBatch batch;    // SpriteBatch to be used within the game
  private final Level level;          // A single level (at the moment)

  /**
   * Constructor for the GameManager.
   *
   * @param batch receives a SpriteBatch to be used in the game
   */
  public GameManager(SpriteBatch batch) {
    this.batch = batch;

    // Configurable number of enemy for a level - will allow us to configure certain aspects of a level
    this.level = new Level(10);
  }

  /**
   * Method called with every frame of the game.
   */
  public void render() {
    this.level.update();    // Firstly, level is updated
    this.level.draw(batch); // Level is then drawn after its been updated
  }
}
