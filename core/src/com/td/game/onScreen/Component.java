package com.td.game.onScreen;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;

public abstract class Component {

  float x;
  float y;

  final Sprite sprite;
  final Circle collisionCircle;

  public Component(float x, float y, float width, float height, Texture texture, float radius) {
    this.x = x;
    this.y = y;

    this.sprite = new Sprite(texture);
    this.sprite.setX(x);
    this.sprite.setY(y);
    this.sprite.setSize(width, height);

    this.collisionCircle = new Circle(x, y, radius);
  }

  public Sprite getSprite() {
    return sprite;
  }

  public Circle getCollisionCircle() {
    return collisionCircle;
  }

  public void setX(float x) {
    this.x = x;
    this.sprite.setX(x);
    this.collisionCircle.setY(y);
  }

  public void updateX(float delta) {
    this.x += delta;
    this.sprite.setX(this.sprite.getX() + delta);
    this.collisionCircle.x += delta;
  }

  public void setY(float y) {
    this.y = y;
    this.sprite.setY(y);
    this.collisionCircle.setY(y);
  }

  public float getX() {
    return x;
  }

  public float getY() {
    return y;
  }

  public float getCentreX() {
    return x + (sprite.getWidth() / 2);
  }

  public float getCentreY() {
    return y + (sprite.getHeight() / 2);
  }

  /**
   * Creates and returns the vector based on the x and y co-ordinates.
   *
   * @return the vector for this enemy
   */
  public Vector2 getVector() {
    return new Vector2(this.x, this.y);
  }

  /**
   * Renders the Sprite on the SpriteBatch.
   *
   * @param batch SpriteBatch to be rendered onto
   */
  public void batchDraw(SpriteBatch batch) {
    this.sprite.draw(batch);
  }
}
