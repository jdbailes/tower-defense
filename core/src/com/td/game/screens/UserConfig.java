package com.td.game.screens;

public class UserConfig {

  private boolean newGame;
  private boolean levelOneUnlocked;
  private boolean levelTwoUnlocked;
  private boolean levelThreeUnlocked;

  public UserConfig() {
  }

  public UserConfig(boolean newGame, boolean levelOneUnlocked, boolean levelTwoUnlocked,
      boolean levelThreeUnlocked) {
    this.newGame = newGame;
    this.levelOneUnlocked = levelOneUnlocked;
    this.levelTwoUnlocked = levelTwoUnlocked;
    this.levelThreeUnlocked = levelThreeUnlocked;
  }

  public boolean isNewGame() {
    return newGame;
  }

  public void setNewGame(boolean newGame) {
    this.newGame = newGame;
  }

  public boolean isLevelOneUnlocked() {
    return levelOneUnlocked;
  }

  public void setLevelOneUnlocked(boolean levelOneUnlocked) {
    this.levelOneUnlocked = levelOneUnlocked;
  }

  public boolean isLevelTwoUnlocked() {
    return levelTwoUnlocked;
  }

  public void setLevelTwoUnlocked(boolean levelTwoUnlocked) {
    this.levelTwoUnlocked = levelTwoUnlocked;
  }

  public boolean isLevelThreeUnlocked() {
    return levelThreeUnlocked;
  }

  public void setLevelThreeUnlocked(boolean levelThreeUnlocked) {
    this.levelThreeUnlocked = levelThreeUnlocked;
  }
}
