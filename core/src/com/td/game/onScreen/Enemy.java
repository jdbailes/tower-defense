package com.td.game.onScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.td.game.Config;

/**
 * Enemy class stores the state of an enemy character.
 */
public class Enemy extends Component {

  private static final Texture TEXTURE = new Texture(Gdx.files.internal("spaceAstronauts_005.png"));
  private static final float RADIUS = 16;

  private int health = 50;

  /**
   * Simple constructor for an Enemy object.
   *
   * @param x the x-position of the collision box for this enemy.
   * @param y the y-position of the collision box for this enemy.
   */
  public Enemy(float x, float y) {
    super(x, y, 32, 32, TEXTURE, RADIUS);
  }

  public boolean isDead() {
    return health <= 0;
  }

  public boolean isLost() {
    return this.getX() > Config.getScreenWidth() - 64;
  }

  public void decrementHealth() {
    if (this.health - 1 >= 0) {
      System.out.println("Health decreased from [" + health + "] to [" + (health - 1) + "]");
      health--;
    }
  }

  public boolean isMissileColliding(Missile missile) {
    Circle missileCollisionCircle = missile.getCollisionCircle();
    return Intersector.overlaps(missileCollisionCircle, super.getCollisionCircle());
  }
}
