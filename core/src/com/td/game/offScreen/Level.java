package com.td.game.offScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.td.game.Config;
import com.td.game.onScreen.Base;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Level class stores the state of a level. By having this in a Level class rather than the
 * GameManager, it means that different levels could be configured in the future.
 */
public class Level {

  private float spawnProbability;  // The the chance of an enemy spawning in a frame

  private static final int STATS_Y_POS = Config.SCREEN_HEIGHT - 30;
  private static final int CURRENCY_X_POS = 50;
  private static final int CURRENCY_VALUE_X_POS = 350;
  private static final int XP_X_POS = Config.SCREEN_WIDTH - 200;
  private static final int XP_VALUE_X_POS = Config.SCREEN_WIDTH - 100;

  // The core on-screen components of the level
  private final Wave wave;
  private final Fleet fleet;
  private final Base base;
  private final Statistics stats;

  // Flag is flipped once the base is destroyed
  private boolean baseDestroyed = false;
  private BitmapFont font;

  /**
   * Constructor for a Level.
   */
  public Level(List<Vector2> breadCrumbs, int level) {

    ObjectMapper mapper = new ObjectMapper();
    //JSON file to Java object
    LevelConfig levelConfig = new LevelConfig();
    try {
      levelConfig = mapper.readValue(new File(Config.getConfigFilepath(level)), LevelConfig.class);
    } catch (IOException e) {
      e.printStackTrace();
    }

    spawnProbability = levelConfig.getSpawnProbability();

    this.wave = new Wave(breadCrumbs, levelConfig.getWaveSize(), levelConfig.getEnemyHealth());

    this.stats = new Statistics(levelConfig.getStartingCredit());
    this.stats.registerWave(wave);

    this.fleet = new Fleet();
    this.fleet.registerStats(stats);

    this.stats.registerFleet(fleet);

    Vector2 baseVector = breadCrumbs.get(breadCrumbs.size() - 1);
    this.base = new Base(baseVector.x, baseVector.y);

    font = new BitmapFont();
    font.setColor(Color.WHITE);
    font.getData().setScale(4, 4);

  }

  /**
   * Method invoked by GameManager with the rendering of each frame.
   */
  public boolean update() {

    this.stats.setCurrentCurrency();
    this.stats.setCurrentFleet();
    this.stats.setCurrentXP();

    float delta = 100 * Gdx.graphics.getDeltaTime();

    this.wave.updatePositions(delta);
    this.wave.cleanUp();
    this.wave.spawnEnemy(spawnProbability);
    this.wave.updateHealthBars();

    this.fleet.run(this.wave);

    if (!this.baseDestroyed) {
      this.base.run(this.wave);

      // If the base health drops to 0 or below, the base is flagged as destroyed
      if (this.base.getHealth() <= 0) {
        this.baseDestroyed = true;
        this.wave.getEnemies().forEach(enemy -> enemy.setAttackingBase(false));
      }
    }

    return this.baseDestroyed;
  }

  public boolean levelComplete() {
    return wave.waveKilled();
  }

  /**
   * Encapsulates addShip() method in Fleet.
   *
   * @param x x-position of the ship to be added
   * @param y y-position of the ship to be added
   */
  public void addShip(int x, int y) {
    this.fleet.addShip(x, y);
  }

  /**
   * Method invoked by GameManager and calls rendering methods on wave and ship.
   *
   * @param batch SpriteBatch passed from GameManager ensures all components are drawn on the same
   * batch
   */
  public void draw(SpriteBatch batch) {
    this.wave.draw(batch);

    displayStats(batch);

    this.fleet.draw(batch);
    if (!baseDestroyed) {
      this.base.draw(batch);
    }
  }

  private void displayStats(SpriteBatch batch) {
    font.draw(batch, "Currency:", CURRENCY_X_POS, STATS_Y_POS);
    font.draw(batch, String.valueOf(this.stats.setCurrentCurrency()), CURRENCY_VALUE_X_POS, STATS_Y_POS);


    font.draw(batch, "XP:", XP_X_POS, STATS_Y_POS);
    font.draw(batch, String.valueOf(this.stats.setCurrentXP()), XP_VALUE_X_POS, STATS_Y_POS);
  }

  /**
   * Debug method invoked by GameManager and calls rendering methods on wave and ship as well as the
   * collision zones which will also be displayed on the screen
   *
   * @param batch SpriteBatch passed from GameManager ensures all components are drawn on the same
   * batch
   * @param renderer ShapeRenderer renders the collision zones on the screen for debugging
   */
  public void draw(SpriteBatch batch, ShapeRenderer renderer) {
    this.wave.draw(batch, renderer);
    this.fleet.draw(batch, renderer);
    if (!baseDestroyed) {
      this.base.draw(batch, renderer);
    }
  }
}
