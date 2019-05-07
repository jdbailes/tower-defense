package com.td.game.onScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;

/**
 * Stores the state of a Missile for easy tracking of Missile projections & stats.
 */
public class Missile {

  private static final Texture TEXTURE = new Texture(Gdx.files.internal("laser-red.png"));

  private Sprite sprite;              // Missile configuration
  private Circle collisionCircle;       // Missile collision collisionCircle
  private float speed = 5.0f;

  /**
   * Constructor for a Missile.
   *
   * @param xPos the starting x position for the missile
   * @param yPos the starting y position for the missile
   */
  public Missile(float xPos, float yPos) {
    this.sprite = new Sprite(TEXTURE);
    this.sprite.setPosition(xPos + 64, yPos);

    collisionCircle = new Circle(sprite.getX(), sprite.getY(), 32);
  }

  /**
   * Updates the position of the Missile in relation to the target it's heading towards.
   *
   * @param direction the direction the missile needs to travel in
   */
  public void updatePosition(Vector2 direction, Float rotation) {

    this.sprite.setRotation(rotation);

    float newXPos = sprite.getX() + this.speed * Gdx.graphics.getDeltaTime() * direction.x;
    float newYPos = sprite.getY() + this.speed * Gdx.graphics.getDeltaTime() * direction.y;

    this.sprite.setPosition(newXPos, newYPos);
    this.collisionCircle.setPosition(newXPos, newYPos);
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
   * Simple accessor for the collisionCircle.
   *
   * @return the collisionCircle for the Missile
   */
  public Circle getCollisionCircle() {
    return this.collisionCircle;
  }
}
