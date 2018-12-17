package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class Control extends InputAdapter implements InputProcessor {

    public boolean up;
    public boolean down;
    public boolean left;
    public boolean right;
    public boolean debug;

    OrthographicCamera camera;
    int screenHeight;
    int screenWidth;


    public Control(int screenWidth, int screenHeight, OrthographicCamera camera) {
        this.camera = camera;
        this.screenHeight = screenHeight;
        this.screenWidth = screenWidth;

    } //Control

    @Override
    public boolean keyDown(int keycode) {

        switch (keycode){
            case Input.Keys.DOWN:
                down = true;
                break;
            case Input.Keys.UP:
                up = true;
                break;
            case Input.Keys.LEFT:
                left = true;
                break;
            case Input.Keys.RIGHT:
                right = true;
                break;
            case Input.Keys.S:
                down = true;
                break;
            case Input.Keys.W:
                up = true;
                break;
            case Input.Keys.A:
                left = true;
                break;
            case Input.Keys.D:
                right = true;
                break;
        } //switch

        return false;

    } //keyDown

    @Override
    public boolean keyUp(int keycode) {
        switch (keycode){
            case Input.Keys.DOWN:
                down = false;
                break;
            case Input.Keys.UP:
                up = false;
                break;
            case Input.Keys.LEFT:
                left = false;
                break;
            case Input.Keys.RIGHT:
                right = false;
                break;
            case Input.Keys.S:
                down = false;
                break;
            case Input.Keys.W:
                up = false;
                break;
            case Input.Keys.A:
                left = false;
                break;
            case Input.Keys.D:
                right = false;
                break;
            case Input.Keys.ESCAPE:
                Gdx.app.exit();
                break;
            case Input.Keys.BACKSPACE:
                debug = !debug;
        } //switch

        return false;
    }
} //class Control
