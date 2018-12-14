package com.mygdx.game;


import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.Enums.ENTITYTYPE;

public class Hero extends Entity {

    private ENTITYTYPE type;

    public Hero(Vector3 pos){
        setType(ENTITYTYPE.HERO);
        setWidth(8);
        setHeight(8);
        getPos().x = pos.x;
        getPos().y = pos.y;
        setTexture(Media.hero);
        setSpeed(1);

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

        getPos().x += directionX * getSpeed();
        getPos().y += directionY * getSpeed();


    } //update

    public float getCameraX(){
        return getPos().x + getWidth() / 2;

    } //getCameraX

    public float getCameraY(){
        return getPos().y + getHeight() / 2;
    }

    public Enums.ENTITYTYPE getType() {
        return type;
    }

    public void setType(Enums.ENTITYTYPE type) {
        this.type = type;
    }


} //class Hero
