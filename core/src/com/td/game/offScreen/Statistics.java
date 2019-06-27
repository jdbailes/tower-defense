package com.td.game.offScreen;

/**
 * @author tautvydasponelis
 */
public class Statistics {

  private int killCounter;
  private int currentFleet;
  private int startingCurrency;
  private int currentFleetBig;

  private Wave wave;
  private Fleet fleet;

  Statistics(int startingCurrency) {
    this.startingCurrency = startingCurrency;
  }

  void registerWave(Wave wave) {
    this.wave = wave;

  }

  void registerFleet(Fleet fleet) {
    this.fleet = fleet;

  }

  int setCurrentCurrency() {
    killCounter = this.wave.getKillCounter();
    int shipCost = 50;
    int bigShipCost = 100;
    int currencyPerKill = 20;
    int currency = this.startingCurrency;

    return (currency + (currencyPerKill * killCounter) - ((currentFleet
        * shipCost) + (currentFleetBig * bigShipCost)));
  }

  int setCurrentXP() {
    int experiencePerKill = 50;
    return (experiencePerKill * killCounter);

  }

  void setCurrentFleet() {
    currentFleet = this.fleet.getCurrentFleet();
  }

  void setCurrentFleetBig() {
    currentFleetBig = this.fleet.getCurrentFleetBig();
  }
}
