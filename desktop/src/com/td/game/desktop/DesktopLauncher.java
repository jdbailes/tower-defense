package com.td.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.td.game.TowerDefenseGame;

public class DesktopLauncher {

  public static void main(String[] arg) {
    LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

    // Manual config override
    config.title = "Tower Defense by Team Apollo";
    config.width = 1920;
    config.height = 1080;

    new LwjglApplication(new TowerDefenseGame(), config);
  }
}
