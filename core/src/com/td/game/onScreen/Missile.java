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

  private static final Texture TEXTURE = new Texture(Gdx.files.internal("laser.png"));

  private Sprite sprite;              // Missile configuration
  private Circle collisionZone;       // Missile collision collisionZone

  /**
   * Constructor for a Missile.
   *
   * @param xPos the starting x position for the missile
   * @param yPos the starting y position for the missile
   */
  public Missile(float xPos, float yPos) {
    this.sprite = new Sprite(TEXTURE); // Sets the texture
    this.sprite.setSize(16, 16); // Sets the size
    this.sprite.setPosition(xPos + 64, yPos); // Set the x and y position

    collisionZone = new Circle(sprite.getX(), sprite.getY(), 32); // Creates the collision zone
  }

  /**
   * Updates the position of the Missile in relation to the target it's heading towards.
   *
   * @param direction the direction the missile needs to travel in
   */
  public void updatePosition(Vector2 direction) {
    float missileSpeed = 5.0f;
    float newXPos = sprite.getX() + missileSpeed * Gdx.graphics.getDeltaTime() * direction.x;
    float newYPos = sprite.getY() + missileSpeed * Gdx.graphics.getDeltaTime() * direction.y;

    sprite.setPosition(newXPos, newYPos);
    collisionZone.setPosition(newXPos, newYPos);
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
   * Simple accessor for the collisionZone.
   *
   * @return the collisionZone for the Missile
   */
  public Circle getCollisionZone() {
    return this.collisionZone;
  }
}
