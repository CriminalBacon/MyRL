package com.mygdx.game;

import com.mygdx.game.entities.Entity;

import java.util.HashMap;

public class Inventory {

    HashMap<Integer, Entity> entityHashMap;

    public Inventory(){
        reset();


    } //Inventory

    public int getInventorySize(){
        return entityHashMap.size();

    } //getInvetorySize

    public void addEntity(Entity entity){
        entityHashMap.put(getInventorySize(), entity);

    } //addEntity

    public HashMap<Integer, Entity> getInventory(){
        return entityHashMap;

    } //getInventory

    public void print(){
        System.out.println("*** Inventory ****");
        for (int i = 0; i < entityHashMap.size(); i++){
            Entity entity = entityHashMap.get(i);
            System.out.println(" ["+ i + "] " + entity.getEntityType().toString());

        }
        System.out.println("*****************");

    } //print


    private void reset() {
        entityHashMap = new HashMap<Integer, Entity>();

    } //reset


} //class Inventory
