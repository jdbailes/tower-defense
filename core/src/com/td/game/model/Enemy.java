package com.td.game.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;

public class Enemy {

  private Sprite sprite;  // Enemy configuration
  private Circle zone;    // Encircles the enemy and acts as a detection zone

  private static Texture enemyTexture = new Texture(Gdx.files.internal("alien.png"));

  /**
   * Simple constructor for an Enemy object.
   *
   * @param xPos the x-position of the collision box for this enemy.
   * @param yPos the y-position of the collision box for this enemy.
   */
  Enemy(float xPos, float yPos) {
    sprite = new Sprite(enemyTexture);
    sprite.setX(xPos);
    sprite.setY(yPos);

    zone = new Circle(sprite.getX(), sprite.getY(), 64);
  }

  /**
   * Simple accessor method on the collision box's x-position.
   *
   * @return the x-position of the collision box.
   */
  float getXPos() {
    return this.sprite.getX();
  }

  /**
   * Simple accessor method on the collision box's y-position.
   *
   * @return the y-position of the collision box.
   */
  float getYPos() {
    return this.sprite.getY();
  }

  /**
   * Updates the x-position of the enemy's collision box.
   *
   * @param delta a delta to add to the x-position.
   */
  void updateXPos(float delta) {
    sprite.setX(sprite.getX() + delta);
    this.zone.x += delta;
  }

  /**
   * Simple accessor method on for the zone.
   *
   * @return a Circle zone for this enemy.
   */
  public Circle getZone() {
    return this.zone;
  }

  public void batchDraw(SpriteBatch batch) {
    sprite.draw(batch);
  }

  public Vector2 getVector() {
    return new Vector2(getXPos(), getYPos());
  }
}
