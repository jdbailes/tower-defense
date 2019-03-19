package com.td.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.td.game.TowerDefenseGame;

public class DesktopLauncher {

  public static void main(String[] arg) {
    LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

    // Manual config override
    config.title = "Tower Defense";
    config.width = 1600;
    config.height = 960;

    new LwjglApplication(new TowerDefenseGame(), config);
  }
}
