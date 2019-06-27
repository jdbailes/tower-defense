package com.td.game.offScreen;

public class Statistics {

    private int startingCurrency = 140;
    private int startingXP;
    private int currencyPerKill = 20;
    private int experiencePerKill = 50;
    private int currentCurrency;
    private int currentXP;
    private int killCounter;
    private int currentFleet;
    private int currentFleetBig;
    private int shipCost = 50;
    private int shipCostBig = 100;


    private Wave wave;
    private Fleet fleet;

    public Statistics(){

    }


    public void registerWave(Wave wave) {
        this.wave = wave;

    }

    public void registerFleet(Fleet fleet) {
        this.fleet = fleet;

    }

    public int setCurrentCurrency() {
        killCounter = this.wave.getKillCounter();
        currentCurrency = (startingCurrency + (currencyPerKill * killCounter) - ((currentFleet * shipCost) + (currentFleetBig * shipCostBig)));

        return currentCurrency;
    }

    public int setCurrentXP(){
        currentXP = (experiencePerKill * killCounter);
        return currentXP;

    }

    public void setCurrentFleet(){
        currentFleet = this.fleet.getCurrentFleet();
    }

    public void setCurrentFleetBig(){
        currentFleetBig = this.fleet.getCurrentFleetBig();
    }
}
