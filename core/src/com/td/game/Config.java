package com.td.game;

public final class Config {

  public static final int SCREEN_HEIGHT = 720;
  public static final int SCREEN_WIDTH = 1280;

  private static final String LEVEL_1_FILEPATH = "tiles/level-one.tmx";
  private static final String LEVEL_2_FILEPATH = "tiles/level-two.tmx";
  private static final String LEVEL_3_FILEPATH = "tiles/level-three.tmx";

  public static String getLevelFilepath(int levelNumber) {
    switch (levelNumber) {
      case 1 :
        return LEVEL_1_FILEPATH;

      case 2 :
        return LEVEL_2_FILEPATH;

      case 3:
        return LEVEL_3_FILEPATH;

      default:
        return "";
    }
  }

}
