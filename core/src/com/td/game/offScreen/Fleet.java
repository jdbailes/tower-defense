package com.td.game.offScreen;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.td.game.onScreen.Enemy;
import com.td.game.onScreen.Missile;
import com.td.game.onScreen.Ship;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Fleet {

  private List<Ship> ships;
  private List<Missile> missiles;

  public Fleet() {
    this.ships = new ArrayList<>();
    this.missiles = new ArrayList<>();
  }

  public void run(Wave wave) {
    this.ships.forEach(ship -> {
      // Set the rotation of the ship based on its target if it has one
      ship.setRotation();

      if (ship.getCurrentTarget() == null) {
        // If the ship doesn't have a target, check for one
        ship.checkForTarget(wave);
      } else {
        // If the ship does have a target, remove it if the enemy isn no longer in the wave
        if (!wave.getEnemies().contains(ship.getCurrentTarget())) {
          ship.setCurrentTarget(null);
        }
      }

      if (ship.getCurrentTarget() != null && !missilesContainShip(ship)) {
        // If the ship has a target but hasn't fired a missile, create one
        Missile missile = new Missile(ship, ship.getCurrentTarget());
        missiles.add(missile);
      }

      if (ship.getCurrentTarget() != null && !ship.getCurrentTarget().getCollisionCircle()
          .overlaps(ship.getCollisionCircle())) {
        ship.setCurrentTarget(null);
      }
    });

    if (!this.missiles.isEmpty()) {
      // Remove missile that have hit an enemy of left the screen
      this.missiles = this.missiles.stream()
          .filter(missile -> !missile.isTerminated())
          .collect(Collectors.toList());

      // Update the position of each enemy
      this.missiles.forEach(Missile::updatePosition);
    }
  }

  private boolean missilesContainShip(Ship ship) {
    return missiles.stream().anyMatch(missile -> missile.hasShip(ship));
  }

  public void addShip(int x, int y) {
    Ship newShip = new Ship(x, y);
    this.ships.add(newShip);
  }

  public void draw(SpriteBatch batch) {
    this.ships.forEach(ship -> ship.draw(batch));
    this.missiles.forEach(missile -> missile.draw(batch));
  }

  public void draw(SpriteBatch batch, ShapeRenderer renderer) {
    this.ships.forEach(ship -> ship.draw(batch, renderer));
    this.missiles.forEach(missile -> missile.draw(batch, renderer));
  }
}
