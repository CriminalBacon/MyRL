package com.mygdx.game.map;

import java.util.ArrayList;

public class Chunk {

    private int numberOfRows;
    private int numberOfCols;
    private int tileSize;

    //tiles are split into arrays of rows
    public ArrayList<ArrayList<Tile>> tiles;

    public Chunk(int rows, int cols, int tileSize){
        tiles = new ArrayList<ArrayList<Tile>>();
        this.numberOfRows = rows;
        this.numberOfCols = cols;
        this.tileSize = tileSize;

    } //Chunk


    public Tile getTile(int row, int col){
        ArrayList<Tile> chunkRow;
        if(tiles.size() > row && row >= 0){
            chunkRow = tiles.get(row);

            if (chunkRow != null && chunkRow.size() > col && col > 0){
                return chunkRow.get(col);
            }

        }

        return null;

    } //getTile

    public String getTileCode(int row, int col){
        Tile tile;

        ArrayList<Tile> chunkrow;

        if(tiles.size() > row && row >= 0){
            chunkrow = tiles.get(row);

            if (chunkrow != null && chunkrow.size() > col && col >= 0){
                tile = chunkrow.get(col);
                return tile.isGrass() ? "1" : "0";
            }


        }

        return "0";


    } //getTileCode

    public int getNumberOfRows() {
        return numberOfRows;
    }

    public void setNumberOfRows(int numberOfRows) {
        this.numberOfRows = numberOfRows;
    }

    public int getNumberOfCols() {
        return numberOfCols;
    }

    public void setNumberOfCols(int numberOfCols) {
        this.numberOfCols = numberOfCols;
    }

    public int getTileSize() {
        return tileSize;
    }

    public void setTileSize(int tileSize) {
        this.tileSize = tileSize;
    }

    public ArrayList<ArrayList<Tile>> getTiles() {
        return tiles;
    }

    public void setTiles(ArrayList<ArrayList<Tile>> tiles) {
        this.tiles = tiles;
    }
} //class Chunk
