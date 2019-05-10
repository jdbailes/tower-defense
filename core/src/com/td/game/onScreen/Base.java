package com.td.game.onScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector2;
import com.td.game.offScreen.Wave;


import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Base class stores the state of the base which gets attacked.
 */
public class Base extends Component {

    private static Texture baseTexture = new Texture(Gdx.files.internal("base.png"));

    private List<Base> bases = new ArrayList<>();
    //private Sprite sprite;              // Enemy configuration
   // private Circle collisionCircle;     // Encircles the enemy and acts as a detection collisionCircle
    //private Circle baseCircle;
    //private Enemy target;
    private boolean isDead = false;
    private int health = 100;

    public float getXPos() {
        return this.sprite.getX();
    }

    //public Circle getCollisionCircleBase() {
        //return this.collisionCircle;
    //}


    /**
     * Simple accessor method on the collision box's y-position.
     *
     * @return the y-position of the collision box.
     */
    private float getYPos() {
        return this.sprite.getY();
    }

    public Base(float x, float y) {
        super(x, y, 200, 200, baseTexture, 110);
      //  sprite = new Sprite(baseTexture);
        //sprite.setPosition(spawnX, spawnY);


        //collisionCircle = new Circle(sprite.getX(), sprite.getY(), 50);
    }

    public void batchDraw(SpriteBatch batch) {

        sprite.draw(batch);
    }

    public List<Base> getBases() {
        return bases;
    }

    public Vector2 getVector() {
        return new Vector2(getXPos(), getYPos());
    }

    public boolean isDead() {
        return this.isDead;
    }

    public void decreaseHealth() {
        if(this.health < 0) {
            this.health--;
        } else {
            setDead(true);
        }
    }

    public void setDead(boolean dead) {
        isDead = dead;
    }

    public boolean isFireColliding(AlienFire fire) {
        Circle missileCollisionCircle = fire.getCollisionCircle();
        return Intersector.overlaps(missileCollisionCircle, this.collisionCircle);
    }


}
