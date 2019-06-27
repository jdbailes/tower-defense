package com.td.game.onScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.td.game.offScreen.Wave;

/**
 * Ship class stores the state of a ship character.
 */
public class Ship extends Component {

  private static final Texture TEXTURE = new Texture(Gdx.files.internal("spaceShips_002.png"));
  private static final float RADIUS = 320;

  private Enemy currentTarget = null;

  /**
   * Simple constructor for a Ship object.
   *
   * @param x the x-position of the collision box for this tower.
   * @param y the y-position of the collision box for this tower.
   */
  public Ship(int x, int y) {
    super(x, y, 64, 64, TEXTURE, RADIUS);
  }

  public Ship(int x, int y, Texture texture, float radius){
    super(x,y,64,64,texture,radius);
  }

  public void checkForTarget(Wave wave) {
    this.currentTarget = wave.getEnemies().stream()
        .filter(enemy -> enemy.getCollisionCircle().overlaps(this.getCollisionCircle()))
        .findFirst().orElse(null);
  }

  public void setRotation() {
    if(this.currentTarget != null) {
      float opp =
          this.currentTarget.getVector().x - this.getVector().x;  // Length of the opposite side
      float adj =
          this.getVector().y - this.currentTarget.getVector().y;  // Length of the adjacent side

      // Arc tan to find angle between ship & target
      float angle = (MathUtils.radiansToDegrees * MathUtils.atan2(opp, adj));

      this.setRotation(angle);
    } else {
      this.setRotation(0);
    }
  }

  public Enemy getCurrentTarget() {
    return this.currentTarget;
  }

  public void setCurrentTarget(Enemy enemy) {
    this.currentTarget = enemy;
  }
}
