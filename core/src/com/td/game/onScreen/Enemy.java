package com.td.game.onScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;

/**
 * Enemy class stores the state of an enemy character.
 */
public class Enemy extends Component {

  private static final Texture TEXTURE = new Texture(Gdx.files.internal("alien.png"));
  private static final float RADIUS = 32;

  private int health = 20;

  /**
   * Simple constructor for an Enemy object.
   *
   * @param x the x-position of the collision box for this enemy.
   * @param y the y-position of the collision box for this enemy.
   */
  public Enemy(float x, float y) {
    super(x, y, 64, 64, TEXTURE, RADIUS);
  }

  public int getHealth() {
    return health;
  }

  public boolean isDead() {
    return health <= 0;
  }

  public void decrementHealth() {
    if (this.health - 1 >= 0) {
      health--;
    }
  }

  public boolean isMissileColliding(Missile missile) {
    Circle missileCollisionCircle = missile.getCollisionCircle();
    return Intersector.overlaps(missileCollisionCircle, super.getCollisionCircle());
  }
}
