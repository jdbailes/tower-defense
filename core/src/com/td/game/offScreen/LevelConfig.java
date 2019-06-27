package com.td.game.offScreen;

public class LevelConfig {

  private int startingCredit;
  private int waveSize;
  private float spawnProbability;
  private int enemyHealth;

  public LevelConfig() {
  }

  public LevelConfig(int startingCredit, int waveSize, float spawnProbability, int enemyHealth) {
    this.startingCredit = startingCredit;
    this.waveSize = waveSize;
    this.spawnProbability = spawnProbability;
    this.enemyHealth = enemyHealth;
  }

  public int getStartingCredit() {
    return startingCredit;
  }

  public void setStartingCredit(int startingCredit) {
    this.startingCredit = startingCredit;
  }

  public int getWaveSize() {
    return waveSize;
  }

  public void setWaveSize(int waveSize) {
    this.waveSize = waveSize;
  }

  public float getSpawnProbability() {
    return spawnProbability;
  }

  public void setSpawnProbability(float spawnProbability) {
    this.spawnProbability = spawnProbability;
  }

  public int getEnemyHealth() {
    return enemyHealth;
  }

  public void setEnemyHealth(int enemyHealth) {
    this.enemyHealth = enemyHealth;
  }
}
