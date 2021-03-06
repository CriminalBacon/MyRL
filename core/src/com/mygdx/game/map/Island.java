package com.mygdx.game.map;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.mygdx.game.entities.Entity;
import com.mygdx.game.Enums;
import com.mygdx.game.Media;
import com.mygdx.game.entities.Tree;
import com.mygdx.game.box2d.Box2DHelper;
import com.mygdx.game.box2d.Box2DWorld;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;


public class Island {



    Tile centerTile;
    Tile clickedTile;

    //Chunks todo: add multiple chunks
    //public Map<Integer, ArrayList<Chunk> chunks = new Map<Integer, ArrayList<Chunk>();

    // One Chunk
    public Chunk chunk;
    private ArrayList<Entity> entities = new ArrayList<Entity>();

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


    public Island(Box2DWorld box2D){
        reset(box2D);

    }

    // loops through all the tiles checking that they are not passable, but not all water
    private void generateHitBoxes(Box2DWorld box2D) {
        for(ArrayList<Tile> row : chunk.getTiles()){
            for (Tile tile : row){
                if (tile.isNotPassable() && tile.notIsAllWater()){
                    Box2DHelper.createBody(box2D.world, chunk.getTileSize(), chunk.getTileSize(), 0, 0, tile.getPos(), BodyDef.BodyType.StaticBody);
                }
            }
        }

    } //generateHitBoxes


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
                    tile.setTexture(randomGrass());
                    tile.setType(Enums.TILETYPE.GRASS);


                    if (row == firstTileRow + 1){
                        tile.setTexture(Media.cliff);
                        tile.setType(Enums.TILETYPE.CLIFF);

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
                        tile.appendSetCode(chunk.getTileCode(tile.getRow() + r, tile.getCol() + c));
                        updateImage(tile);
                    }
                }

            }
        }


    } //codeTiles

    private void updateImage(Tile tile) {
        //secondary texture is to add edges to tiles
        if (Arrays.asList(aGrassLeft).contains(tile.getCode())){
            tile.setSecondaryTexture(Media.grassLeft);
        } else if (Arrays.asList(aGrassRight).contains(tile.getCode())){
            tile.setSecondaryTexture(Media.grassRight);
        } else if (Arrays.asList(aGrassRightEnd).contains(tile.getCode())){
            tile.setSecondaryTexture(Media.grassLeftUpperEdge);
        } else if (Arrays.asList(aGrassLeftEnd).contains(tile.getCode())){
            tile.setSecondaryTexture(Media.grassRightUpperEdge);
        } else if (Arrays.asList(aGrassTop).contains(tile.getCode())){
            tile.setSecondaryTexture(Media.grassTop);
        } else if (Arrays.asList(aGrassTopRight).contains(tile.getCode())){
            tile.setSecondaryTexture(Media.grassTopRight);
        } else if (Arrays.asList(aGrassTopLeft).contains(tile.getCode())){
            tile.setSecondaryTexture(Media.grassTopLeft);
        }
    }

    public Tile getCenterTile(){
        return centerTile;
    }


    public void addEntities(Box2DWorld box2D){
        //Loop all tiles and add random trees
        for (ArrayList<Tile> row : chunk.getTiles()){
            for (Tile tile : row){
                if (tile.isGrass()){
                if (MathUtils.random(100) > 90) {
                    entities.add(new Tree(tile.getPos(), box2D));
                    }

                }
            }
        }

    } //addEntities

    public ArrayList<Entity> getEntities() {
        return entities;
    }


    //Reset entities
    public void reset(Box2DWorld box2D){
        entities.clear();
        box2D.clearAllBodies();
        setupTiles();
        codeTiles();
        generateHitBoxes(box2D);
        addEntities(box2D);

    } //reset


    public void printEntities(){
        for (Entity entity : entities){
            System.out.println(entity.getTexture().toString() + " X: " + entity.getPos().x + " " + " Y: " + entity.getPos().y + " " );

        }
    } //printEntities

    public void clearRemovedEntities(Box2DWorld box2D) {
        Iterator<Entity> it = entities.iterator();
        while(it.hasNext()){
            Entity e = it.next();
            if (e.isRemove()){
                e.removeBodies(box2D);
                box2D.removeEntity(e);

                it.remove();
            }
        }


    } //clearRemovedEntities



} //class Island
