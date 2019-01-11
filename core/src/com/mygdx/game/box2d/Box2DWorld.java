package com.mygdx.game.box2d;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.Control;
import com.mygdx.game.Entity;

import java.util.ArrayList;
import java.util.HashMap;

public class Box2DWorld {

    public World world;
    private Box2DDebugRenderer debugRenderer;
    private HashMap<Integer, Entity> entityMap; //holds entities

    private ArrayList<Entity> testArray;

    public Box2DWorld(){
        world = new World(new Vector2(0.0f, 0.0f), true);
        debugRenderer = new Box2DDebugRenderer();
        //Init entity array
        entityMap = new HashMap<Integer, Entity>();

        //Setup the world contacts listeners
        world.setContactListener(new ContactListener() {
            @Override
            public void beginContact(Contact contact) {
                Fixture fixtureA = contact.getFixtureA();
                Fixture fixtureB = contact.getFixtureB();

                processCollisions(fixtureA, fixtureB, true);



            }

            @Override
            public void endContact(Contact contact) {
                Fixture fixtureA = contact.getFixtureA();
                Fixture fixtureB = contact.getFixtureB();

                processCollisions(fixtureA, fixtureB, false);


            }

            @Override
            public void preSolve(Contact contact, Manifold oldManifold) {

            }

            @Override
            public void postSolve(Contact contact, ContactImpulse impulse) {

            }
        });

    } //Box2DWorld



    public void tick(OrthographicCamera camera, Control control){
        if (control.debug) {
            debugRenderer.render(world, camera.combined);
        }

        world.step(Gdx.app.getGraphics().getDeltaTime(), 6, 2);
        world.clearForces();

    } //tick

    //clear down all bodies
    public void clearAllBodies(){
        Array<Body> bodies = new Array<Body>();
        world.getBodies(bodies);
        for (Body b : bodies){
            world.destroyBody(b);
        }

        entityMap.clear();

    }  //clearAllBodies


    //check if the two bodies colliding exist in our array of entities
    //check that only one entity is a sensor
    //when entityA is a sensor then entityB is the player as its the only moving object
    //We want to call the Entity method collision for the hero:  Hero.collision(Tree, true)
    private void processCollisions(Fixture fixtureA, Fixture fixtureB, boolean begin) {
        Entity entityA = entityMap.get(fixtureA.hashCode());
        Entity entityB = entityMap.get(fixtureB.hashCode());

        if(entityA != null && entityB != null){


            if (fixtureA.isSensor() && !fixtureB.isSensor()){
                entityB.collision(entityA, begin);
            } else if (!fixtureA.isSensor() && fixtureB.isSensor()){
                entityA.collision(entityB, begin);
            }
        }

    } //processCollisions

    //Pass in Island entities and copy them into a new array that has a key (hashcode of entity)
    public void populateEntityMap(ArrayList<Entity> entities){
        entityMap.clear();
        for (Entity e : entities){
            entityMap.put(e.getEntityHashcode(), e);
        }
        System.out.println("Map size: " + entityMap.size());

        //for (Integer e : entityMap.keySet()){
        //    System.out.println(e + " - " + entityMap.get(e));
       // }


    } //populateEntityMap

    //When a tree/hero is added to the island we track it in the entity array
    public void addEntityToMap(Entity entity){
        entityMap.put(entity.hashCode(), entity);
    }


    //removes an entity from the Array
    public void removeEntity(Entity entity){
        entityMap.remove(entity.getEntityHashcode());

        System.out.println("Map size after remove: " + entityMap.size());

    }



} //class Box2DWorld
