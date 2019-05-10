package com.td.game.onScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.td.game.Config;

public class AlienFire extends Component {

    private static final Texture TEXTURE = new Texture(Gdx.files.internal("alien-fire.png"));
    private static final float RADIUS = 16;
    //private Sprite sprite;              // Missile configuration
    //private Circle collisionCircle;       // Missile collision collisionCircle
    //private float speed = 1.0f;


    private float speed = 3.0f;

    /**
     * Constructor for a Missile.
     *
     * @param x the starting x position for the missile
     * @param y the starting y position for the missile
     */
    public AlienFire(float x, float y) {
        super(x, y, 8,32, TEXTURE, RADIUS);
    }

    /**
     * Updates the position of the Missile in relation to the target it's heading towards.
     *
     * @param direction the direction the missile needs to travel in
     */
    public void updatePosition(Vector2 direction, Float rotation) {

        sprite.setRotation(rotation);

        float newXPos = sprite.getX() + this.speed * Gdx.graphics.getDeltaTime() * direction.x;
        float newYPos = sprite.getY() + this.speed * Gdx.graphics.getDeltaTime() * direction.y;

        sprite.setPosition(newXPos, newYPos);
        collisionCircle.setPosition(newXPos, newYPos);
    }

    public boolean isLost() {
        return x > Config.getScreenWidth() + 100 || x < -100 || y > Config.getScreenHeight() + 100
                || y < -100;

    }
}
