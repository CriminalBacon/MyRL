package com.mygdx.game;

import com.badlogic.gdx.math.Vector3;

import java.util.Random;

public class Rumble {

    private static float time = 0;
    private static float currentTime = 0;
    private static float power = 0;
    private static float currentPower = 0;
    private static Random random;
    private static Vector3 pos = new Vector3();

    public static void rumble(float rumblePower, float rumbleLength){
        random = new Random();
        power = rumblePower;
        time = rumbleLength;
        currentTime = 0;

    } //rumble

    public static Vector3 tick(float delta){
        if (currentTime <= time){
            currentPower = power * ((time - currentTime) /time);

            pos.x = (random.nextFloat() - 0.5f) * 2 * currentPower;
            pos.y = (random.nextFloat() - 0.5f) * 2 * currentPower;

            currentTime += delta;
        } else {
            time = 0;
        }

        return pos;

    } //tick

    public static float getRumbeTimeLeft(){
        return time;

    } //static

    public static Vector3 getPos(){
        return pos;

    } //getPos






} //class Rumble
