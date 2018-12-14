package com.mygdx.game;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

public class Entity {


    private Vector3 pos;
    private Texture texture;
    private float width;
    private float height;

    //private Enums.ENTITYTYPE type;
    private float speed;


    float directionX = 0;
    float directionY = 0;

    public Entity(){
        pos = new Vector3();

    } //Entity


    public void draw(SpriteBatch batch){
        batch.draw(texture, pos.x, pos.y, width, height);

    } //draw

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public Vector3 getPos() {
        return pos;
    }

    public void setPos(Vector3 pos) {
        this.pos = pos;
    }

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }




} //class Entity
