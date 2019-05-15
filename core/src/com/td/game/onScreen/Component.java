package com.td.game.onScreen;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;

public abstract class Component {

  private final Sprite sprite;
  private final Circle collisionCircle;

  public Component(float x, float y, float height, float width, Texture texture,
      float collisionCircleRadius) {
    this.sprite = new Sprite(texture);
    this.sprite.setX(x);
    this.sprite.setY(y);

    this.collisionCircle = new Circle();
    this.collisionCircle.setX(x + (width / 2));
    this.collisionCircle.setY(y + (height / 2));
    this.collisionCircle.setRadius(collisionCircleRadius);
  }

  public Sprite getSprite() {
    return sprite;
  }

  public Circle getCollisionCircle() {
    return collisionCircle;
  }

  public void setX(float x) {
    this.sprite.setX(x);
    this.collisionCircle.setX(x);
  }

  public void setY(float y) {
    this.sprite.setY(y);
    this.collisionCircle.setY(y);
  }

  public void updateX(float delta) {
    this.sprite.setX(this.sprite.getX() + delta);
    this.collisionCircle.setX(this.collisionCircle.x + delta);
  }

  public void setRotation(float rotation) {
    this.sprite.setRotation(rotation);
  }

  public float getX() {
    return sprite.getX();
  }

  public float getY() {
    return sprite.getY();
  }

  /**
   * Creates and returns the vector based on the x and y co-ordinates.
   *
   * @return the vector for this enemy
   */
  public Vector2 getVector() {
    return new Vector2(collisionCircle.x , collisionCircle.y);
  }

  /**
   * Renders the Sprite on the SpriteBatch.
   *
   * @param batch SpriteBatch to be rendered onto
   */
  public void draw(SpriteBatch batch) {
    this.sprite.draw(batch);
  }

  public void draw(SpriteBatch batch, ShapeRenderer renderer) {
    renderer.circle(collisionCircle.x, collisionCircle.y, collisionCircle.radius);
    this.sprite.draw(batch);
  }
}
