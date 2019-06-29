package com.td.game.onScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.td.game.Config;

/**
 * Stores the state of a BigMissile component.
 *
 * @author tautvydasponelis
 */
public class BigMissile extends Component {

  private static final Texture TEXTURE = new Texture(Gdx.files.internal("game/laserRed09.png"));
  private static final float RADIUS = 12;
  private static final float SPEED = 3.0f;

  private final BigShip bigShip;
  private final Enemy enemy;

  /**
   * Constructor for a Missile.
   */
  public BigMissile(BigShip bigShip, Enemy enemy) {
    super(bigShip.getVector().x, bigShip.getVector().y, 86, 86, TEXTURE, RADIUS);
    this.bigShip = bigShip;
    this.enemy = enemy;
  }

  /**
   * Determines if BigMissile has BigShip.
   */
  public boolean hasShip(BigShip bigShip) {
    return this.bigShip.equals(bigShip);
  }

  /**
   * Updates the position of the Missile in relation to the target it's heading towards.
   */
  public void updatePosition() {
    Vector2 positionVector = this.getVector();
    Vector2 enemyVector = this.enemy.getVector();

    float opp = enemyVector.x - positionVector.x;  // Length of the opposite side
    float adj = positionVector.y - enemyVector.y;  // Length of the adjacent side

    // Arc tan to find angle between ship & target
    float angle = (MathUtils.radiansToDegrees * MathUtils.atan2(opp, adj) + 180);
    this.setRotation(angle);

    // Determines the vector for the missile to head towards
    Vector2 missileDestination = enemyVector.sub(positionVector);

    float newXPos = getX() + (SPEED * Gdx.graphics.getDeltaTime() * missileDestination.x);
    float newYPos = getY() + (SPEED * Gdx.graphics.getDeltaTime() * missileDestination.y);

    this.setX(newXPos);
    this.setY(newYPos);
  }

  /**
   * Decides whether enemy has been terminated.
   */
  public boolean isTerminated() {
    return hasHitEnemy() || isLost();
  }

  private boolean hasHitEnemy() {
    if (enemy.isMissileColliding(this)) {
      this.enemy.decrementHealthBig();
      return true;
    }
    return false;
  }

  private boolean isLost() {
    return getX() > Config.SCREEN_WIDTH + 100 || getX() < -100
        || getY() > Config.SCREEN_HEIGHT + 100 || getY() < -100;
  }
}
