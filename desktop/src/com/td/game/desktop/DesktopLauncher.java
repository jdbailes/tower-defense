package com.td.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.td.game.Config;
import com.td.game.TowerDefenseGame;

public class DesktopLauncher {

  public static void main(String[] arg) {
    LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

    // Manual config override
    config.title = "Tower Defense by Team Apollo";
    config.width = Config.SCREEN_WIDTH;
    config.height = Config.SCREEN_HEIGHT;
    config.resizable = true;

    new LwjglApplication(new TowerDefenseGame(), config);
  }
}
