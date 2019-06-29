package com.td.game.onScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

/**
 * Stores the state of a normal ship.
 *
 * @author josephbailey
 * @author tautvydasponelis
 */
public class NormalShip extends Ship {

  private static final Texture TEXTURE = new Texture(Gdx.files.internal("game/space-ship.png"));
  private static final float RADIUS = 320;

  /**
   * Simple constructor for a Ship object.
   *
   * @param x the x-position of the collision box for this tower.
   * @param y the y-position of the collision box for this tower.
   */
  public NormalShip(int x, int y) {
    super(x, y, TEXTURE, RADIUS);
  }
}
