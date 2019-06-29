package com.td.game.onScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.td.game.offScreen.Wave;

/**
 * Stores the state of a base component.
 *
 * @author josephbailey
 * @author tautvydasponelis
 */
public class Base {

  private static final Texture TEXTURE = new Texture(Gdx.files.internal("game/space-base.png"));

  private final Sprite sprite;
  private final Rectangle collisionRectangle;

  private float health = 1900;

  private final HealthBar healthbar;

  /**
   * Default constructor.
   *
   * @param xPosition the x-position of the Base
   * @param yPosition the y-position of the Base
   */
  public Base(float xPosition, float yPosition) {
    // Create and configure a new sprite
    sprite = new Sprite(TEXTURE);
    sprite.setX(xPosition);
    sprite.setY(yPosition);
    sprite.setSize(128, 128);

    // Create and configure a new collision rectangle
    collisionRectangle = new Rectangle();
    collisionRectangle.setX(xPosition);
    collisionRectangle.setY(yPosition);
    collisionRectangle.setHeight(128);
    collisionRectangle.setWidth(128);

    healthbar = new HealthBar(1875, 0);
    healthbar.getSprite().setSize(25, 1900);
  }

  /**
   * Runs the base for a single frame.
   */
  public void run(Wave wave) {
    wave.getEnemies().forEach(enemy -> {
      boolean collision = Intersector.overlaps(enemy.getCollisionCircle(), this.collisionRectangle);
      if (collision) {
        enemy.setAttackingBase(true);
        this.health--;
        updateHealthBar();

      } else {
        enemy.setAttackingBase(false);
      }
    });
  }

  /**
   * Simple getter on base health.
   */
  public double getHealth() {
    return this.health;
  }


  private void updateHealthBar() {
    this.healthbar.getSprite().setSize(25, this.health);

  }

  /**
   * Renders the Sprite on the SpriteBatch.
   *
   * @param batch SpriteBatch to be rendered onto
   */
  public void draw(SpriteBatch batch) {
    this.sprite.draw(batch);
    this.healthbar.draw(batch);
  }

  /**
   * Draws the base for a single frame.
   */
  public void draw(SpriteBatch batch, ShapeRenderer renderer) {
    renderer
        .rect(this.collisionRectangle.x, this.collisionRectangle.y, this.collisionRectangle.width,
            this.collisionRectangle.height);
    this.sprite.draw(batch);

  }
}
