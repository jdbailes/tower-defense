package com.td.game.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import java.util.List;

public class Tower {

  private static final Texture TEXTURE = new Texture(Gdx.files.internal("ship.png"));

  private final Sprite sprite;    // Tower configuration
  private final Circle range;     // Encircles the tower and acts as a detection range

  private Enemy target;           // Identifies the current target for the tower

  /**
   * Simple constructor for a Tower object.
   *
   * @param xPos the x-position of the collision box for this tower.
   * @param yPos the y-position of the collision box for this tower.
   */
  public Tower(int xPos, int yPos) {
    sprite = new Sprite(TEXTURE);
    sprite.setPosition(xPos, yPos);

    range = new Circle(sprite.getX(), sprite.getY(), 300);
  }

  public void scan(List<Enemy> enemyList) {
    // Check to see if a target already exists
    if (target != null) {
      // Check to see if the target is still in range of tower
      if (!target.getZone().overlaps(this.range)) {
        // If it's out of range then delete the enemy
        target = null;
        sprite.setRotation(0);
      }
    } else {
      // Look for a new target
      target = enemyList.stream()
          .filter(enemy -> enemy.getZone().overlaps(this.range))
          .findFirst()
          .orElse(null);
    }
  }

  public void lockTarget() {
    if (this.target != null) {
      Vector2 towerVector = new Vector2(sprite.getX(), sprite.getY());
      Vector2 targetVector = target.getVector();

      float opp = targetVector.x - towerVector.x;
      float adj = towerVector.y - targetVector.y;

      float angle = MathUtils.radiansToDegrees * MathUtils.atan2(opp, adj);

      sprite.setRotation(angle);
    }
  }

  public void batchDraw(SpriteBatch batch) {
    sprite.draw(batch);
  }
}
