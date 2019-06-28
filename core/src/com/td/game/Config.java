package com.td.game;

public class Config {

  public static final int SCREEN_HEIGHT = 960;
  public static final int SCREEN_WIDTH = 1920;

  private static final String LEVEL_1_FILEPATH = "tiles/Level_1.tmx";
  private static final String LEVEL_2_FILEPATH = "tiles/Level_2.tmx";
  private static final String LEVEL_3_FILEPATH = "tiles/Level_3.tmx";

  private static final String LEVEL_1_CONFIG_FILEPATH = "core/assets/configuration/level1_config.json";
  private static final String LEVEL_2_CONFIG_FILEPATH = "core/assets/configuration/level2_config.json";
  private static final String LEVEL_3_CONFIG_FILEPATH = "core/assets/configuration/level3_config.json";

  public static String getLevelFilepath(int levelNumber) {
    switch (levelNumber) {
      case 1:
        return LEVEL_1_FILEPATH;

      case 2:
        return LEVEL_2_FILEPATH;

      case 3:
        return LEVEL_3_FILEPATH;

      default:
        return "";
    }
  }

  public static String getConfigFilepath(int levelNumber) {
    switch (levelNumber) {
      case 1:
        return LEVEL_1_CONFIG_FILEPATH;

      case 2:
        return LEVEL_2_CONFIG_FILEPATH;

      case 3:
        return LEVEL_3_CONFIG_FILEPATH;

      default:
        return "";
    }
  }
}
