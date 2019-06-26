package com.td.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.td.game.offScreen.LevelConfig;
import com.td.game.screens.MainMenuScreen;
import com.td.game.screens.UserConfig;
import java.io.File;
import java.io.IOException;

/**
 * Game abstract class provides an implementation of ApplicationListener for you to use, along with
 * some helper methods to set and handle Screen rendering.
 */
public class TowerDefenseGame extends Game {

  private static final String USER_CONFIG = "configuration/user_configuration.json";
  private final AssetManager assetManager = new AssetManager();

  public SpriteBatch batch;

  @Override
  public void create() {

    ObjectMapper mapper = new ObjectMapper();
    //JSON file to Java object
    UserConfig userConfig = new UserConfig();
    try {
      userConfig = mapper.readValue(new File(USER_CONFIG), UserConfig.class);
    } catch (IOException e) {
      e.printStackTrace();
    }


    this.batch = new SpriteBatch();
    this.assetManager.setLoader(TiledMap.class, new TmxMapLoader(new InternalFileHandleResolver()));
    this.setScreen(new MainMenuScreen(this, userConfig));
  }

  @Override
  public void render() {
    super.render(); // Call ensures Screen we set in create() is rendered
  }

  @Override
  public void dispose() {
    // Kills batch and font
    this.batch.dispose();
  }

  public AssetManager getAssetManager() {
    return assetManager;
  }
}
