package com.td.game;

public abstract class Config {

  private static final boolean DEBUG = false;
  private static final int SCREEN_HEIGHT = 1080;
  private static final int SCREEN_WIDTH = 1920;

  public static int getScreenHeight() {
    return SCREEN_HEIGHT;
  }

  public static int getScreenWidth() {
    return SCREEN_WIDTH;
  }

  public static boolean isDebug() {
    return DEBUG;
  }
}
