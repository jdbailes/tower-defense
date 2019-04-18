package com.td.game.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;

/**
 * Enemy class stores the state of an enemy character.
 */
class Enemy {

  private static Texture enemyTexture = new Texture(Gdx.files.internal("alien.png"));

  private Sprite sprite;  // Enemy configuration
  private Circle zone;    // Encircles the enemy and acts as a detection zone

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

    zone = new Circle(sprite.getX(), sprite.getY(), 32);
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
  private float getYPos() {
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
  Circle getZone() {
    return this.zone;
  }

  /**
   * Renders the Sprite on the SpriteBatch.
   *
   * @param batch SpriteBatch to be rendered onto
   */
  void batchDraw(SpriteBatch batch) {
    sprite.draw(batch);
  }

  /**
   * Creates and returns the vector based on the x and y co-ordinates.
   *
   * @return the vector for this enemy
   */
  Vector2 getVector() {
    return new Vector2(getXPos(), getYPos());
  }
}
