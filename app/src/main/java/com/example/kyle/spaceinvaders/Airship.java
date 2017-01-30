package com.example.kyle.spaceinvaders;


public class Airship
{
    float x;
    float y;
    int shipwidth;
    int shipheight;
    public Airship(int xx, int yy, int height, int width)
    {
        x = xx;
        y = yy;
        shipheight = height;
        shipwidth = width;
    }

    public void movement(float xx, float yy)
    {
        this.x = xx;
        this.y = yy;
    }

    public float getX()
    {
        return x;
    }

    public float getY()
    {
        return y;
    }


}
