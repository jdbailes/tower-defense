package com.td.game.offScreen;

public class Statistics {

    private int startingCurrency = 200;
    private int startingXP;
    private int currencyPerKill = 50;
    private int experiencePerKill = 100;
    private int currentCurrency;
    private int currentXP;
    private int killCounter;

    private Wave wave;

    public Statistics(){

    }


    public void registerWave(Wave wave) {
        this.wave = wave;
        //killCounter = this.wave.getKillCounter();
    }



    public void setCurrentCurrency() {
        killCounter = this.wave.getKillCounter();
        currentCurrency = (startingCurrency + (currencyPerKill * killCounter));
        System.out.println(currentCurrency);

    }

    public void setCurrentXP(){
        currentXP = (startingXP + (experiencePerKill * killCounter));
        System.out.println(currentXP);

    }
}
