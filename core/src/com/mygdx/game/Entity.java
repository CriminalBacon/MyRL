package com.mygdx.game;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class Entity {


    public Vector3 pos;
    public Texture texture;
    public float width;
    public float height;
    public Enums.ENTITYTYPE type;
    public float speed;


    float directionX = 0;
    float directionY = 0;

    public Entity(){
        pos = new Vector3();

    } //Entity


    public void draw(SpriteBatch batch){
        batch.draw(texture, pos.x, pos.y, width, height);

    } //draw


} //class Entity
