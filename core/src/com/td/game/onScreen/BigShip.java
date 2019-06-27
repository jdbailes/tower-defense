package com.td.game.onScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class BigShip extends Ship {

    private static final Texture TEXTURE = new Texture(Gdx.files.internal("playerShip1.png"));
    private static final float RADIUS = 500;

    /**
     * Simple constructor for a Ship object.
     *
     * @param x the x-position of the collision box for this tower.
     * @param y the y-position of the collision box for this tower.
     */
    public BigShip(int x, int y) {
        super(x, y, TEXTURE, RADIUS);
    }
}
