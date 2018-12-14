package com.mygdx.game.map;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.mygdx.game.Entity;
import com.mygdx.game.Enums;
import com.mygdx.game.Media;

import java.util.ArrayList;
import java.util.Arrays;


public class Island {



    Tile centerTile;
    Tile clickedTile;

    //Chunks todo: add multiple chunks
    //public Map<Integer, ArrayList<Chunk> chunks = new Map<Integer, ArrayList<Chunk>();

    // One Chunk
    public Chunk chunk;
    ArrayList<Entity> entities = new ArrayList<Entity>();

    //Track click
    int currentTileNumber;
    int currentColumn;
    int currentRow;

    //Arrays for mapping code to texture
    String[] aGrassLeft = {"001001001","001001001", "001001000", "000001001"};
    String[] aGrassRight = {"100100100","100100000","000100100"};
    String[] aGrassRightEnd = {"100000000"};
    String[] aGrassLeftEnd = {"001000000"};
    String[] aGrassTop = {"000000111", "000000011","000000110"};
    String[] aGrassTopRight = {"000000100"};
    String[] aGrassTopLeft = {"000000001"};


    public Island(){
        setupTiles();
        codeTiles();

    }


    private void setupTiles(){
        chunk = new Chunk(33, 33, 8);

        int currentRow = 0;
        int rngWidth = MathUtils.random(5, 8);
        int rngHeight = MathUtils.random(5, 8);

        int centerTileRow = chunk.getNumberOfRows() / 2;
        int centerTileCol = chunk.getNumberOfCols()/ 2;
        int firstTileRow = centerTileRow - (rngHeight);

        int maxRow = centerTileRow + rngHeight;
        int minRow = centerTileRow - rngHeight;
        int maxCol = centerTileCol + rngWidth;
        int minCol = centerTileCol - rngWidth;

        //Chunk Row
        ArrayList<Tile> chunkRow = new ArrayList<Tile>();

        //if number of tiles is needed.
        //int numTiles = ((maxCol - minCol) - 1) * ((maxRow - minRow) - 1);

        for (int row = 0; row < chunk.getNumberOfRows(); row++){
            for (int col = 0; col < chunk.getNumberOfCols(); col++){
                //Create Tile
                //initially create all water
                Tile tile = new Tile(col, row, chunk.getTileSize(), Enums.TILETYPE.WATER, randomWater());

                //Make a small island
                if (row > minRow && row < maxRow && col > minCol && col < maxCol){
                    tile.texture = randomGrass();
                    tile.type = Enums.TILETYPE.GRASS;


                    if (row == firstTileRow + 1){
                        tile.texture = Media.cliff;
                        tile.type = Enums.TILETYPE.CLIFF;

                    } else {
                        //chance to add trees etc
                    }

                }

                //add tile to chunk
                if (currentRow == row){
                    chunkRow.add(tile);

                    //last row and column?
                    if (row == chunk.getNumberOfRows() - 1 && col == chunk.getNumberOfCols() - 1){
                        chunk.tiles.add(chunkRow);
                    }


                } else {
                    //new Row
                    currentRow = row;

                    //add row to chunk
                    chunk.tiles.add(chunkRow);

                    //clear chunk row
                    chunkRow = new ArrayList<Tile>();

                    //add first tile to the new row
                    chunkRow.add(tile);

                }


            }

            //set center tile for camera positioning
            centerTile = chunk.getTile(centerTileRow, centerTileCol);

        }

    } //setupTiles


    private Texture randomGrass() {
        Texture grass;

        int tile = MathUtils.random(20);
        switch (tile){
            case 1:
                grass = Media.grass01;
                break;
            case 2:
                grass = Media.grass02;
                break;
            case 3:
                grass = Media.grass03;
                break;
            case 4:
                grass = Media.grass04;
                break;
            default: grass = Media.grass01;
                break;

        }

        return grass;

    } //randomGrass



    private Texture randomWater() {
        Texture water;

        int tile = MathUtils.random(20);
        switch (tile){
            case 1:
                water = Media.water01;
            break;
            case 2:
                water = Media.water02;
            break;
            case 3:
                water = Media.water03;
            break;
            case 4:
                water = Media.water04;
            break;
            default: water = Media.water01;
            break;

        }

        return water;

    } //randomWater


    private void codeTiles(){
        // loop all tiles and set the inital code

        //1 chunk only atm
        for (ArrayList<Tile> row : chunk.tiles){
            for (Tile tile : row){
                // Check all surrounding tiles and set 1 for pass 0 for non pass
                // 0 0 0
                // 0 X 0
                // 0 0 0

                int[] rows = {1, 0, -1}; //represents ones row above, same row, row below current
                int[] cols = {-1, 0, 1}; //represents col below current, same col, col above

                for (int r : rows){
                    for (int c: cols){
                        tile.code += chunk.getTileCode(tile.row + r, tile.col + c);
                        updateImage(tile);
                    }
                }

            }
        }


    } //codeTiles

    private void updateImage(Tile tile) {
        //secondary texture is to add edges to tiles
        if (Arrays.asList(aGrassLeft).contains(tile.code)){
            tile.secondaryTexture = Media.grassLeft;
        } else if (Arrays.asList(aGrassRight).contains(tile.code)){
            tile.secondaryTexture = Media.grassRight;
        } else if (Arrays.asList(aGrassRightEnd).contains(tile.code)){
            tile.secondaryTexture = Media.grassLeftUpperEdge;
        } else if (Arrays.asList(aGrassLeftEnd).contains(tile.code)){
            tile.secondaryTexture = Media.grassRightUpperEdge;
        } else if (Arrays.asList(aGrassTop).contains(tile.code)){
            tile.secondaryTexture = Media.grassTop;
        } else if (Arrays.asList(aGrassTopRight).contains(tile.code)){
            tile.secondaryTexture = Media.grassTopRight;
        } else if (Arrays.asList(aGrassTopLeft).contains(tile.code)){
            tile.secondaryTexture = Media.grassTopLeft;
        }
    }

    public Tile getCenterTile(){
        return centerTile;
    }


} //class Island
