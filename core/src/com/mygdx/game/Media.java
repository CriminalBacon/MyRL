package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Media {

    //Tiles
    public static Texture grass01, grass02, grass03, grass04;
    public static Texture grassLeft, grassRight;
    public static Texture grassLeftUpperEdge, grassRightUpperEdge;
    public static Texture grassTop, grassTopRight, grassTopLeft;
    public static Texture water01, water02, water03, water04;
    public static Texture cliff, water;
    public static Texture hero;
    public static Texture tree;



    public static void loadAssets() {
        grass01 = new Texture("assets/grass/grass_01.png");
        grass02 = new Texture("assets/grass/grass_02.png");
        grass03 = new Texture("assets/grass/grass_03.png");
        grass04 = new Texture("assets/grass/grass_04.png");

        grassLeft = new Texture("assets/grass/right_grass_edge.png");
        grassRight = new Texture("assets/grass/left_grass_edge.png");

        grassLeftUpperEdge = new Texture("assets/grass/left_upper_edge.png");
        grassRightUpperEdge = new Texture("assets/grass/right_upper_edge.png");

        grassTop = new Texture("assets/grass/top.png");
        grassTopRight = new Texture("assets/grass/top_right.png");
        grassTopLeft = new Texture("assets/grass/top_left.png");

        water01 = new Texture("assets/water/water_01.png");
        water02 = new Texture("assets/water/water_02.png");
        water03 = new Texture("assets/water/water_03.png");
        water04 = new Texture("assets/water/water_04.png");
        cliff = new Texture(Gdx.files.internal("assets/cliff.png"));

        hero = new Texture("assets/entities/hero/hero.png");

        tree = new Texture("assets/entities/tree.png");

    } //loadAssets

    public void dispose(){
        grass01.dispose();
        grass02.dispose();
        grass03.dispose();
        grass04.dispose();
        grassLeft.dispose();
        grassRight.dispose();
        grassLeftUpperEdge.dispose();
        grassRightUpperEdge.dispose();
        grassTop.dispose();
        grassTopLeft.dispose();
        grassTopRight.dispose();
        water01.dispose();
        water02.dispose();
        water03.dispose();
        water04.dispose();
        cliff.dispose();
        tree.dispose();

    } //dispose



} //class Media
