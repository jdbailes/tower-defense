package com.td.game.offScreen;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.td.game.onScreen.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Fleet {

  private List<Ship> ships;
  private List<Missile> missiles;
  private List<BigShip> bigShips;
  private List<BigMissile> bigMissiles;

  private int shipCount;

  private Statistics stats;

  private int currentStatistics;

  private int currentFleet;
  private int currentFleetBig;

  public Fleet() {
    this.ships = new ArrayList<>();
    this.missiles = new ArrayList<>();

    this.bigShips = new ArrayList<>();
    this.bigMissiles = new ArrayList<>();

    this.shipCount = 0;


  }

  public void registerStats(Statistics stats) {
    this.stats = stats;
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

    this.bigShips.forEach(shipBig -> {
      // Set the rotation of the ship based on its target if it has one
      shipBig.setRotation();

      if (shipBig.getCurrentTarget() == null) {
        // If the ship doesn't have a target, check for one
        shipBig.checkForTarget(wave);
      } else {
        // If the ship does have a target, remove it if the enemy isn no longer in the wave
        if (!wave.getEnemies().contains(shipBig.getCurrentTarget())) {
          shipBig.setCurrentTarget(null);
        }
      }

      if (shipBig.getCurrentTarget() != null && !missilesContainShipBig(shipBig)) {
        // If the ship has a target but hasn't fired a missile, create one
        BigMissile missile = new BigMissile(shipBig, shipBig.getCurrentTarget());
        bigMissiles.add(missile);
      }

      if (shipBig.getCurrentTarget() != null && !shipBig.getCurrentTarget().getCollisionCircle()
              .overlaps(shipBig.getCollisionCircle())) {
        shipBig.setCurrentTarget(null);
      }
    });

    if (!this.bigMissiles.isEmpty()) {
      // Remove missile that have hit an enemy of left the screen
      this.bigMissiles = this.bigMissiles.stream()
              .filter(missile -> !missile.isTerminated())
              .collect(Collectors.toList());

      // Update the position of each enemy
      this.bigMissiles.forEach(BigMissile::updatePosition);
    }
  }

  private boolean missilesContainShipBig(BigShip shipBig) {
    return bigMissiles.stream().anyMatch(missile -> missile.hasShip(shipBig));
  }

  private boolean missilesContainShip(Ship ship) {
    return missiles.stream().anyMatch(missile -> missile.hasShip(ship));

  }


  public void addNormalShip(int x, int y) {
    //if the player has enough currency, they will be able to spawn a turret
    if (this.stats.setCurrentCurrency() > 49 && currentFleet <= 8) {
      currentFleet++;
      Ship newShip = new NormalShip(x, y);
      Circle circle1 = new Circle(newShip.getCollisionCircle().x, newShip.getCollisionCircle().y,
              64);

      boolean overlaps = this.ships.stream().anyMatch(ship -> {
        Circle circle2 = new Circle(ship.getCollisionCircle().x, ship.getCollisionCircle().y, 64);
        return Intersector.overlaps(circle1, circle2);
      });

      if (!overlaps) {
        this.ships.add(newShip);
        shipCount++;
      }
    }
  }

  public void addBigShip(int x, int y) {
    //if the player has enough currency, they will be able to spawn a turret
    if (this.stats.setCurrentCurrency() > 99 && currentFleetBig <= 5) {
      currentFleetBig++;
      BigShip newShipBig = new BigShip(x, y);
      Circle circle1 = new Circle(newShipBig.getCollisionCircle().x, newShipBig.getCollisionCircle().y,
              64);

      boolean overlaps = this.ships.stream().anyMatch(ship -> {
        Circle circle2 = new Circle(ship.getCollisionCircle().x, ship.getCollisionCircle().y, 64);
        return Intersector.overlaps(circle1, circle2);
      });

      if (!overlaps) {
        this.bigShips.add(newShipBig);
        shipCount++;
      }
    }
  }

  public int getCurrentFleet() {
    return currentFleet;
  }

  public int getCurrentFleetBig() {
    return currentFleetBig;
  }

  public void draw(SpriteBatch batch) {
    this.ships.forEach(ship -> ship.draw(batch));
    this.bigShips.forEach(ship -> ship.draw(batch));
    this.missiles.forEach(missile -> missile.draw(batch));
    this.bigMissiles.forEach(missile -> missile.draw(batch));
  }

  public void draw(SpriteBatch batch, ShapeRenderer renderer) {
    this.ships.forEach(ship -> ship.draw(batch, renderer));
    this.bigShips.forEach(ship -> ship.draw(batch, renderer));
    this.missiles.forEach(missile -> missile.draw(batch, renderer));
    this.bigMissiles.forEach(missile -> missile.draw(batch, renderer));
  }
}
