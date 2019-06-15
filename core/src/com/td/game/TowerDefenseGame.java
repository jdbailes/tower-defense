package com.td.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.td.game.screens.MainMenuScreen;

/**
 * Game abstract class provides an implementation of ApplicationListener for you to use, along with
 * some helper methods to set and handle Screen rendering.
 */
public class TowerDefenseGame extends Game {

  // SpriteBatch used to render objects on the screen
  public SpriteBatch batch;

  @Override
  public void create() {
    this.batch = new SpriteBatch();

    this.setScreen(new MainMenuScreen(this));
  }

  @Override
  public void render() {
    super.render(); // Call ensures Screen we set in create() is rendered
  }

  @Override
  public void dispose() {
    // Kills batch and font
    this.batch.dispose();
  }
}
