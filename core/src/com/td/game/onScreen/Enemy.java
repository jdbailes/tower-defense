package com.td.game.onScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector2;
import com.td.game.Config;

/**
 * Enemy class stores the state of an enemy character.
 */
public class Enemy extends Component {

  private static final Texture TEXTURE = new Texture(Gdx.files.internal("game/space-enemy.png"));
  private static final float RADIUS = 16;
  private HealthBar healthbar;

  private int health;
  private boolean attackingBase = false;

  private Vector2 destination;

  /**
   * Simple constructor for an Enemy object.
   *
   * @param x the x-position of the collision box for this enemy.
   * @param y the y-position of the collision box for this enemy.
   */
  public Enemy(float x, float y, Vector2 destination, int health) {
    super(x, y, 32, 32, TEXTURE, RADIUS);

    this.health = health;
    this.destination = destination;

    this.healthbar = new HealthBar(x, y + 30);
  }


  public HealthBar getHealthBar() {
    return this.healthbar;
  }

  public void updateHealthBar() {
    this.healthbar.getSprite().setSize(5, this.health);

  }

  public boolean isDead() {
    return health <= 0;
  }

  public boolean isLost() {
    return this.getX() > Config.SCREEN_WIDTH - 64;
  }

  public void decrementHealth() {
    if (this.health - 1 >= 0) {
      health--;
    }
  }

  public void decrementHealthBig() {
    if (this.health - 1 >= 0) {
      health = health - 25;
    }
  }


  public void setAttackingBase(boolean attackingBase) {
    this.attackingBase = attackingBase;
  }

  public boolean isAttackingBase() {
    return attackingBase;
  }

  public boolean isMissileColliding(Missile missile) {
    Circle missileCollisionCircle = missile.getCollisionCircle();
    return Intersector.overlaps(missileCollisionCircle, super.getCollisionCircle());
  }

  public boolean isMissileColliding(BigMissile missile) {
    Circle missileCollisionCircle = missile.getCollisionCircle();
    return Intersector.overlaps(missileCollisionCircle, super.getCollisionCircle());
  }

  public void updatePosition(float delta) {

    int xCompare = Float.compare(getX(), destination.x);
    if (xCompare > 0) {
      increaseX(delta);
      healthbar.increaseX(delta);
    } else if (xCompare < 0) {
      decreaseX(delta);
      healthbar.decreaseX(delta);
    }

    int yCompare = Float.compare(getY(), destination.y);
    if (yCompare < 0) {
      increaseY(delta);
      healthbar.increaseY(delta);
    } else if (yCompare > 0) {
      decreaseY(delta);
      healthbar.decreaseY(delta);
    }

  }

  public boolean hasReachedDestination() {
    return getCollisionCircle().overlaps(new Circle(destination.x, destination.y, 16));
  }

  public Vector2 getDestination() {
    return destination;
  }

  public void setDestination(Vector2 destination) {
    this.destination = destination;
  }

  @Override
  public void draw(SpriteBatch batch) {
    this.getSprite().draw(batch);
    this.healthbar.draw(batch);
  }

  public String toString() {
    return this.destination.toString();
  }
}
