package com.td.game.onScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.td.game.offScreen.Wave;
import java.util.List;

/**
 * Ship class stores the state of a ship character.
 */
public class Ship extends Component {

  private static final Texture TEXTURE = new Texture(Gdx.files.internal("ship.png"));
  private static final float RADIUS = 300;

  private Enemy target;           // Identifies the current target for the tower
  private Missile missile;        // Every ship has a missile to fire - can be created/deleted for repeat firing

  /**
   * Simple constructor for a Ship object.
   *
   * @param x the x-position of the collision box for this tower.
   * @param y the y-position of the collision box for this tower.
   */
  public Ship(int x, int y) {
    super(x, y, 64, 64, TEXTURE, RADIUS);
  }

  /**
   * Runs the ship for a single frame.
   *
   * @param wave the current state of the wave for this frame
   */
  public void run(Wave wave) {
    // If the ship doesn't have a target, look for one
    if (target == null) {
      target = scanForTarget(wave.getEnemies());
    } else {
      if(target.isDead()) {
        wave.getEnemies().remove(target);
        target = null;
        sprite.setRotation(0);
      } else if(!Intersector.overlaps(collisionCircle, target.collisionCircle)) {
        target = null;
        sprite.setRotation(0);
      } else {
        engageTarget();
      }
    }
  }

  /**
   * Ship engages with a target - includes rotating towards the target and firing a missile at the
   * target.
   */
  private void engageTarget() {
    // TODO Fix the buggy missile
    // If the missile isn't initialised then initialise it
    if (this.missile == null) {
      this.missile = new Missile(getCentreX(), getCentreY());
    }

    Vector2 shipVector = new Vector2(this.getCentreX(), this.getCentreY());
    Vector2 targetVector = new Vector2(target.getCentreX(), target.getCentreY());

    float opp = targetVector.x - shipVector.x;  // Length of the opposite side
    float adj = shipVector.y - targetVector.y;  // Length of the adjacent side

    // Arc tan to find angle between ship & target
    float angle = MathUtils.radiansToDegrees * MathUtils.atan2(opp, adj);

    sprite.setRotation(angle);

    // Determines the vector for the missile to head towards
    Vector2 missileDestination = targetVector.sub(shipVector);
    missile.updatePosition(missileDestination, sprite.getRotation());

    if (this.target.isMissileColliding(missile)) {
      this.target.decrementHealth();
      this.missile = null;
    } else if (this.missile.isLost()) {
     this.missile = null;
    }
  }

  /**
   * Given a wave of enemies, will identify the first enemy to be in the range of the ship and set
   * it as target.
   *
   * @param enemies the current wave of enemies
   * @return Enemy if a target is found, otherwise null
   */
  private Enemy scanForTarget(List<Enemy> enemies) {
    return enemies.stream()
        .filter(enemy -> Intersector.overlaps(enemy.collisionCircle, collisionCircle))
        .findFirst()
        .orElse(null);
  }

  /**
   * Renders the Sprite on the SpriteBatch.
   *
   * @param batch SpriteBatch to be rendered onto
   */
  public void batchDraw(SpriteBatch batch) {
    if (missile != null) {
      missile.batchDraw(batch);
    }
    this.getSprite().draw(batch);
  }
}
