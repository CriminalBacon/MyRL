package com.mygdx.game.map;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.entities.Entity;
import com.mygdx.game.Enums.TILETYPE;

public class Tile extends Entity {

    private int size;
    private int row;
    private int col;
    private String code;
    private Texture secondaryTexture;
    private TILETYPE type;

    public Tile(float x, float y, int size, TILETYPE type, Texture texture){
        super();
        getPos().x = x * size;
        getPos().y = y * size;
        this.size = size;
        setTexture(texture);
        this.col = (int) x;
        this.row = (int) y;
        this.type = type;
        this.code = "";

    } //Tile

    //public String details(){
        //return "x: " + pos.x + " y: " + pos.y + " row: " + row + " col: " + col + " code: " + code + " type: " + type.toString();
    //} //details

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

    public boolean isAllWater(){
        return code.equals("000000000");

    } //isAllWater

    public boolean notIsAllWater(){
        return !isAllWater();

    } //notIsAllWater




    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public Texture getSecondaryTexture() {
        return secondaryTexture;
    }

    public void setSecondaryTexture(Texture secondaryTexture) {
        this.secondaryTexture = secondaryTexture;
    }

    public String getCode() {
        return code;
    }

    public void appendSetCode(String code) {
        this.code += code;
    }


    public TILETYPE getType() {
        return type;
    }

    public void setType(TILETYPE type) {
        this.type = type;
    }
} //class Tile
