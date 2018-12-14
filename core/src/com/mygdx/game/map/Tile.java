package com.mygdx.game.map;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.Entity;
import com.mygdx.game.Enums.TILETYPE;

public class Tile extends Entity {

    public int size;
    public int row;
    public int col;
    public String code;
    public Texture secondaryTexture;
    public Texture texture;
    public TILETYPE type;

    public Tile(float x, float y, int size, TILETYPE type, Texture texture){
        super();
        pos.x = x * size;
        pos.y = y * size;
        this.size = size;
        this.texture = texture;
        this.col = (int) x;
        this.row = (int) y;
        this.type = type;
        this.code = "";

    } //Tile

    public String details(){
        return "x: " + pos.x + " y: " + pos.y + " row: " + row + " col: " + col + " code: " + code + " type: " + type.toString();
    } //details

    public boolean isGrass(){
        return type == TILETYPE.GRASS;

    } //isGrass

    public boolean isWater(){
        return type == TILETYPE.WATER;

    } //isWater


    public boolean isCliff(){
        return type == TILETYPE.CLIFF;

    } //isCliff


    public boolean isPassable(){
        return !isWater() && !isCliff();

    } //isPassable

    public boolean isNotPassable(){
        return !isPassable();

    } //isNotPassable




} //class Tile
