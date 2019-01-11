package com.mygdx.game.entities;


import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.mygdx.game.Control;
import com.mygdx.game.Enums.ENTITYTYPE;
import com.mygdx.game.Inventory;
import com.mygdx.game.Media;
import com.mygdx.game.box2d.Box2DHelper;
import com.mygdx.game.box2d.Box2DWorld;
import com.mygdx.game.entities.Entity;

import java.util.ArrayList;

public class Hero extends Entity {

    private ENTITYTYPE type;

    //array of entities currently overlapping
    private ArrayList<Entity> interactEntities;


    public Hero(Vector3 pos, Box2DWorld box2D){
        //this.type = ENTITYTYPE.HERO;
        setWidth(8);
        setHeight(8);
        getPos().x = pos.x;
        getPos().y = pos.y;
        setTexture(Media.hero);
        setSpeed(30);
        setEntityType(ENTITYTYPE.HERO);
        setInventory(new Inventory());

        //create a new Dynamic body
        reset(box2D, pos);

    } //Hero


    public void update(Control control){
        int directionX = 0;
        int directionY = 0;

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

        //printInteractEntities();
        //System.out.println("Interact map size: " + interactEntities.size());


        //if interact key pressed and interactEntities present, interact with the first in list
        if (control.interact && interactEntities.size() > 0){
            interactEntities.get(0).interact(this);
        }

        //reset interact
        control.interact = false;

    } //update

    public float getCameraX(){
        return getPos().x + getWidth() / 2;

    } //getCameraX

    public float getCameraY(){
        return getPos().y + getHeight() / 2;
    }

    public ENTITYTYPE getType() {
        return type;
    }

    public void setType(ENTITYTYPE type) {
        this.type = type;
    }

    public void printDetails(){
        System.out.println("Pos: " + getPos().toString());
    }


    //reposition hero to center and recreate hitbox
    public void reset(Box2DWorld box2D, Vector3 pos){
        getPos().set(pos);
        setBody(Box2DHelper.createBody(box2D.world, getWidth()/2, getHeight()/2,
                getWidth()/4, 0, pos, BodyDef.BodyType.DynamicBody ));

        //set hashcode to that of the bodies fixture.  our body has a single fixture
        setHashcode(getBody().getFixtureList().get(0).hashCode());
        //init the entity array
        interactEntities = new ArrayList<Entity>();
    }

    @Override
    public void collision(Entity entity, boolean begin) {
        if (begin){
            //hero entered hitbox
            interactEntities.add(entity);
            System.out.println("entered in collision");
        } else {
            //hero left hitbox
            interactEntities.remove(entity);
            System.out.println("left collision");
        }
    } //collision

    public void printInteractEntities(){
        for (Entity entity : interactEntities){
            System.out.println("Entity :" + entity.getEntityHashcode());
        }

    }


} //class Hero
