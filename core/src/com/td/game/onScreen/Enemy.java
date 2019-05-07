package com.td.game.onScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector2;

/**
 * Enemy class stores the state of an enemy character.
 */
public class Enemy {

  private static Texture enemyTexture = new Texture(Gdx.files.internal("alien.png"));

  private Sprite sprite;              // Enemy configuration
  private Circle collisionCircle;     // Encircles the enemy and acts as a detection collisionCircle

  private boolean isDead = false;
  private int health = 20;

  /**
   * Simple constructor for an Enemy object.
   *
   * @param xPos the x-position of the collision box for this enemy.
   * @param yPos the y-position of the collision box for this enemy.
   */
  public Enemy(float xPos, float yPos) {
    sprite = new Sprite(enemyTexture);
    sprite.setX(xPos);
    sprite.setY(yPos);

    collisionCircle = new Circle(sprite.getX(), sprite.getY(), 32);
  }

  /**
   * Simple accessor method on the collision box's x-position.
   *
   * @return the x-position of the collision box.
   */
  public float getXPos() {
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
  public void updateXPos(float delta) {
    sprite.setX(sprite.getX() + delta);
    this.collisionCircle.x += delta;
  }

  /**
   * Simple accessor method on for the collisionCircle.
   *
   * @return a Circle collisionCircle for this enemy.
   */
  public Circle getCollisionCircle() {
    return this.collisionCircle;
  }

  /**
   * Renders the Sprite on the SpriteBatch.
   *
   * @param batch SpriteBatch to be rendered onto
   */
  public void batchDraw(SpriteBatch batch) {
    sprite.draw(batch);
  }

  /**
   * Creates and returns the vector based on the x and y co-ordinates.
   *
   * @return the vector for this enemy
   */
  public Vector2 getVector() {
    return new Vector2(getXPos(), getYPos());
  }

  public boolean isDead() {
    return this.isDead;
  }

  public void decreaseHealth() {
    if(this.health < 0) {
      this.health--;
    } else {
      setDead(true);
    }
  }

  public void setDead(boolean dead) {
    isDead = dead;
  }

  public boolean isMissileColliding(Missile missile) {
    Circle missileCollisionCircle = missile.getCollisionCircle();
    return Intersector.overlaps(missileCollisionCircle, this.collisionCircle);
  }
}
