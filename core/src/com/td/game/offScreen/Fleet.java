package com.td.game.offScreen;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.td.game.onScreen.Ship;
import java.util.ArrayList;
import java.util.List;

public class Fleet {

  private List<Ship> ships;

  public Fleet() {
    this.ships = new ArrayList<>();
    this.ships.add(new Ship(869, 700));
  }

  public void run(Wave wave) {
    this.ships.forEach(ship -> {
      ship.run(wave);
    });
  }

  public void addShip(int x, int y) {
    Ship newShip = new Ship(x, y);
    this.ships.add(newShip);
  }

  public void batchDraw(SpriteBatch batch) {
    this.ships.forEach(ship -> ship.batchDraw(batch));
  }

}
