package com.td.game.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;

public class Tower {

  private Sprite sprite;  // Tower configuration
  private Circle zone;    // Encircles the tower and acts as a detection zone

  private static final String IMAGE = "ship.png";

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

  private Texture getTexture() {
    return new  Texture(Gdx.files.internal(IMAGE));
  }

  /**
   * Renders the tower on the map.
   *
   * @param batch a SpriteBatch passed from the calling class.
   */
  public void batchDraw(SpriteBatch batch) {
    batch.draw(getTexture(), sprite.getX(), sprite.getY());
  }

  /**
   * Simple accessor method on for the zone.
   * @return a Circle zone for this tower.
   */
  public Circle getZone() {
    return this.zone;
  }

  /**
   * Determines whether an enemy has collided with the tower.
   * @param enemy Enemy which may have collided with tower.
   * @return true if collision has occurred, otherwise false.
   */
  public boolean collides(Enemy enemy) {
    return this.zone.overlaps(enemy.getZone());
  }
}
