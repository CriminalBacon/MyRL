package com.mygdx.game.entities;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.mygdx.game.Enums;
import com.mygdx.game.Inventory;
import com.mygdx.game.box2d.Box2DWorld;

public class Entity implements Comparable<Entity>{


    private Vector3 pos;
    private Texture texture;
    private float width;
    private float height;

    private int hashcode;
    private Body sensor;
    private boolean remove;


    private Enums.ENTITYTYPE entityType;
    private float speed;


    private float directionX = 0;
    private float directionY = 0;

    private Body body;

    private Inventory inventory;

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

    public Body getBody() {
        return body;
    }

    public void setBody(Body body) {
        this.body = body;
    }

    public int getEntityHashcode() {
        return hashcode;
    }

    public void setHashcode(int hashcode) {
        this.hashcode = hashcode;
    }

    public Body getSensor() {
        return sensor;
    }

    public void setSensor(Body sensor) {
        this.sensor = sensor;
    }

    public boolean isRemove() {
        return remove;
    }

    public void setRemove(boolean remove) {
        this.remove = remove;
    }

    public Inventory getInventory() {
        return inventory;

    } //getInventory

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;

    } //setInventory

    public void collision(Entity entity, boolean begin){

    } //collision

    public void interact(Entity entity){


    } //interact


    public void removeBodies(Box2DWorld box2D){
        if (sensor != null){
            box2D.world.destroyBody(sensor);
        }

        if (body != null){
            box2D.world.destroyBody(body);

        }

    } //removeBodies


    @Override
    public int compareTo(Entity e) {
        float tempY = e.getPos().y;
        float compareY = getPos().y;

        return (tempY < compareY) ? -1 : (tempY > compareY) ? 1 : 0;
    } //compareTo

    public Enums.ENTITYTYPE getEntityType(){
        return entityType;
    }

    public void setEntityType(Enums.ENTITYTYPE entityType) {
        this.entityType = entityType;
    }
} //class Entity
