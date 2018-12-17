package com.mygdx.game;


import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.mygdx.game.Enums.ENTITYTYPE;
import com.mygdx.game.box2d.Box2DHelper;
import com.mygdx.game.box2d.Box2DWorld;

public class Hero extends Entity {

    private ENTITYTYPE type;

    public Hero(Vector3 pos, Box2DWorld box2D){
        this.type = ENTITYTYPE.HERO;
        setWidth(8);
        setHeight(8);
        getPos().x = pos.x;
        getPos().y = pos.y;
        setTexture(Media.hero);
        setSpeed(30);

        //create a new Dynamic body
        setBody(Box2DHelper.createBody(box2D.world, getWidth(), getHeight()/2, pos, BodyDef.BodyType.DynamicBody ));

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

        //moving the Box2D body instead of entity position, but setting the linear velocity
        //the set the position of the Entity to the x and y of the body.
        getBody().setLinearVelocity(directionX * getSpeed(), directionY * getSpeed());
        getPos().x = getBody().getPosition().x - getWidth() / 2;
        getPos().y = getBody().getPosition().y - getHeight() / 4;


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
