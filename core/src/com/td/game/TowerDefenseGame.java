package com.td.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.td.game.screens.MenuScreen;

/**
 * Main class for application and serves as entry point to the game.
 */
public class TowerDefenseGame extends Game {

  // SpriteBatch used to render objects on the screen
  public SpriteBatch batch;
  // Bitmap font used alongside SpriteBatch to render text on the screen
  public BitmapFont font;

  @Override
  public void create() {
    batch = new SpriteBatch();
    font = new BitmapFont();

    this.setScreen(new MenuScreen(this));
  }

  @Override
  public void render() {
    super.render(); // Call ensures Screen we set in create() is rendered
  }

  @Override
  public void dispose() {
    // Kills batch and font
    batch.dispose();
    font.dispose();
  }
}
