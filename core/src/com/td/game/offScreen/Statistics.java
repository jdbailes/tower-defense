package com.td.game.offScreen;

/**
 * @author tautvydasponelis
 */
public class Statistics {

  private int killCounter;
  private int currentFleet;


  private Wave wave;
  private Fleet fleet;

  Statistics() {
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
    int currencyPerKill = 20;
    int startingCurrency = 140;

    return (startingCurrency + (currencyPerKill * killCounter) - (currentFleet
        * shipCost));
  }

  int setCurrentXP() {
    int experiencePerKill = 50;
    return (experiencePerKill * killCounter);

  }

  void setCurrentFleet() {
    currentFleet = this.fleet.getCurrentFleet();
  }
}
