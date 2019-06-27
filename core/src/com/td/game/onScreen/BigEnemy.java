package com.td.game.onScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class BigEnemy extends Enemy {

    private static final Texture TEXTURE = new Texture(Gdx.files.internal("spaceAstronauts_011.png"));
    private static final float RADIUS = 36;
    /**
     * Simple constructor for an Enemy object.
     *
     * @param x the x-position of the collision box for this enemy.
     * @param y the y-position of the collision box for this enemy.
     */
    public BigEnemy(float x, float y) {
        super(x, y, TEXTURE, RADIUS);
    }
}
