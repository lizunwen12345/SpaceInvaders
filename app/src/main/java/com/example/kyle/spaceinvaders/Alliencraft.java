package com.example.kyle.spaceinvaders;


public class Alliencraft
{
    float x;
    float y;
    boolean direction;
    public boolean alive;

    public Alliencraft(float xx, float yy)
    {
        x = xx;
        y = yy;
        direction = true; // moves to right when true
        alive = true;
    }

    public void movement(float xx, float yy, boolean dir)
    {
        this.x = xx;
        this.y = yy;
        this.direction = dir;
    }

    public boolean checkalive()
    {
        return this.alive;
    }

    public float getX()
    {
        return this.x;
    }

    public float getY() {
        return this.y;
    }
}
