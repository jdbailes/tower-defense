package com.td.game.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import java.awt.Rectangle;

public class Enemy {

  private static final int WIDTH = 32;
  private static final int HEIGHT = 32;
  private static final Texture TEXTURE = new Texture(Gdx.files.internal("alien.png"));

  private Rectangle collisionBox;

  /**
   * Simple constructor for an Enemy object.
   *
   * @param xPos the x-position of the collision box for this enemy.
   * @param yPos the y-position of the collision box for this enemy.
   */
  Enemy(int xPos, int yPos) {
    collisionBox = new Rectangle();
    collisionBox.setBounds(xPos, yPos, WIDTH, HEIGHT);
  }

  /**
   * Simple accessor method on the collision box's x-position.
   *
   * @return the x-position of the collision box.
   */
  int getXPos() {
    return this.collisionBox.x;
  }

  /**
   * Simple accessor method on the collision box's y-position.
   *
   * @return the y-position of the collision box.
   */
  int getYPos() {
    return this.collisionBox.y;
  }

  /**
   * Updates the x-position of the enemy's collision box.
   *
   * @param delta a delta to add to the x-position.
   */
  void updateXPos(float delta) {
    this.collisionBox.x += delta;
  }

  /**
   * Updates the y-position of the enemy's collision box.
   *
   * @param delta a delta to add to the y-position.
   */
  public void updateYPos(float delta) {
    this.collisionBox.y += delta;
  }

  /**
   * Simple accessor method on the enemy texture.
   *
   * @return the texture of the enemy.
   */
  Texture getTexture() {
    return TEXTURE;
  }
}
