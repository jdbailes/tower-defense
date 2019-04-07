package com.td.game.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;

public class Tower {

  // Tower configuration
  private Sprite sprite;
  private static final int WIDTH = 128;
  private static final int HEIGHT = 128;
  private static final Texture TEXTURE = new Texture(Gdx.files.internal("ship.png"));

  // Encircles the tower and acts as a detection zone
  private Circle zone;

  /**
   * Simple constructor for a Tower object.
   *
   * @param xPos the x-position of the collision box for this tower.
   * @param yPos the y-position of the collision box for this tower.
   */
  public Tower(int xPos, int yPos) {
    sprite = new Sprite();
    sprite.setX(xPos);
    sprite.setY(yPos);

    zone = new Circle(sprite.getX(), sprite.getY(), 300);
  }

  /**
   * Renders the tower on the map.
   *
   * @param batch a SpriteBatch passed from the calling class.
   */
  public void batchDraw(SpriteBatch batch) {
    batch.draw(TEXTURE, sprite.getX(), sprite.getY());
  }

  /**
   * Simple accessor method on for the zone.
   * @return a Circle zone for this tower.
   */
  public Circle getZone() {
    return this.zone;
  }
}
