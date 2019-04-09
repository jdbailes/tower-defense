package com.td.game.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;

public class Tower {

  private Sprite sprite;  // Tower configuration
  private Circle zone;    // Encircles the tower and acts as a detection zone

  private Enemy target;

  private static Texture towerTexture = new Texture(Gdx.files.internal("ship.png"));

  /**
   * Simple constructor for a Tower object.
   *
   * @param xPos the x-position of the collision box for this tower.
   * @param yPos the y-position of the collision box for this tower.
   */
  public Tower(int xPos, int yPos) {
    sprite = new Sprite(towerTexture);
    sprite.setPosition(xPos, yPos);

    zone = new Circle(sprite.getX(), sprite.getY(), 300);
  }

  /**
   * Simple accessor method on for the zone.
   *
   * @return a Circle zone for this tower.
   */
  public Circle getZone() {
    return this.zone;
  }

  public void setTarget(Enemy target) {
    this.target = target;
  }

  public void batchDraw(SpriteBatch batch) {
    sprite.draw(batch);
  }
}
