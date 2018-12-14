package com.mygdx.game;


import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.Enums.ENTITYTYPE;

public class Hero extends Entity {

    public Hero(Vector3 pos){
        type = ENTITYTYPE.HERO;
        width = 8;
        height = 8;
        this.pos.x = pos.x;
        this.pos.y = pos.y;
        texture = Media.hero;
        speed = 1;

    } //Hero


    public void update(Control control){
        directionX = 0;
        directionY = 0;

        if(control.down){
            directionY = -1;
        }
        if(control.up){
            directionY = 1;
        }
        if(control.left){
            directionX = -1;
        }
        if(control.right){
            directionX = 1;
        }

        pos.x += directionX * speed;
        pos.y += directionY * speed;


    } //update

    public float getCameraX(){
        return pos.x + width / 2;

    } //getCameraX

    public float getCameraY(){
        return pos.y + height / 2;
    }


} //class Hero
