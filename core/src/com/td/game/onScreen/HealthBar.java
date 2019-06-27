package com.td.game.onScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class HealthBar extends Component {

  private static final Texture TEXTURE = new Texture(Gdx.files.internal("game/green-laser.png"));

  public HealthBar(float xPosition, float yPosition) {
    super(xPosition, yPosition, 64, 5, TEXTURE, 0);

    getSprite().setRotation(90);
  }

  public void updateX(float delta) {
    getSprite().setX(getSprite().getX() + delta);
  }

  /**
   * Renders the Sprite on the SpriteBatch.
   *
   * @param batch SpriteBatch to be rendered onto
   */
  public void draw(SpriteBatch batch) {
    getSprite().draw(batch);
  }

}
