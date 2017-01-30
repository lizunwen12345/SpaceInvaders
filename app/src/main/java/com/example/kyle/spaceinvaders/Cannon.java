package com.example.kyle.spaceinvaders;


public class Cannon {
    float x;
    float y;
    public boolean flying;

    public Cannon(float xx, float yy) {
        x = xx;
        y = yy;
        flying = true;
    }

    public void setactive(boolean input) {
        flying = input;
    }

    public boolean checkflying() {
        return flying;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void movement(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void crash() {
        flying = false;
    }

}
