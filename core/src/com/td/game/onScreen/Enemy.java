package com.td.game.onScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.td.game.offScreen.Wave;

import java.util.List;

/**
 * Enemy class stores the state of an enemy character.
 */
public class Enemy extends Component {

  private static final Texture TEXTURE = new Texture(Gdx.files.internal("alien.png"));
  private static final float RADIUS = 32;
  private Base targetBase;
  private AlienFire fire;

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

  public void run(Base base) {
    // If the ship doesn't have a target, look for one
    if (targetBase == null) {
      targetBase = scanForTarget(base.getBases());
    } else {
      if(targetBase.isDead()) {
        base.getBases().remove(targetBase);
        targetBase = null;
        sprite.setRotation(0);
      } else if(!Intersector.overlaps(collisionCircle, targetBase.collisionCircle)) {
        targetBase = null;
        sprite.setRotation(0);
      } else {
        engageTarget();
      }
    }
  }

  private void engageTarget() {
    // TODO Fix the buggy missile

    // If the missile isn't initialised then initialise it
    if (this.fire == null) {
      this.fire = new AlienFire(getCentreX(), getCentreY());
    }

    Vector2 shipVector = new Vector2(this.getCentreX(), this.getCentreY());
    Vector2 targetVector = new Vector2(targetBase.getCentreX(), targetBase.getCentreY());

    float opp = targetVector.x - shipVector.x;  // Length of the opposite side
    float adj = shipVector.y - targetVector.y;  // Length of the adjacent side

    // Arc tan to find angle between ship & target
    float angle = MathUtils.radiansToDegrees * MathUtils.atan2(opp, adj);

    sprite.setRotation(angle);

    // Determines the vector for the missile to head towards
    Vector2 missileDestination = targetVector.sub(shipVector);
    fire.updatePosition(missileDestination, sprite.getRotation());

    if (this.targetBase.isFireColliding(fire)) {
      System.out.println("hello");
      this.targetBase.decreaseHealth();
      this.fire = null;
    } else if (this.fire.isLost()) {
      this.fire = null;
    }
  }


  private Base scanForTarget(List<Base> bases) {
    return bases.stream()
            .filter(base -> Intersector.overlaps(base.collisionCircle, collisionCircle))
            .findFirst()
            .orElse(null);
  }
}
