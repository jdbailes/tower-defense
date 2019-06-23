package com.td.game.onScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class HealthBar {

  private static final Texture TEXTURE = new Texture(Gdx.files.internal("game/green-laser.png"));

  private final Sprite sprite;

  public HealthBar(float xPosition, float yPosition) {
    this.sprite = new Sprite(TEXTURE);
    this.sprite.setX(xPosition);
    this.sprite.setY(yPosition);
    this.sprite.setSize(5, 64);
    this.sprite.setRotation(90);
  }

  public Sprite getSprite() {
    return this.sprite;
  }

  public void updateX(float delta) {
    this.sprite.setX(this.sprite.getX() + delta);
  }

  /**
   * Renders the Sprite on the SpriteBatch.
   *
   * @param batch SpriteBatch to be rendered onto
   */
  public void draw(SpriteBatch batch) {
    this.sprite.draw(batch);
  }

}
