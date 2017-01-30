package com.example.kyle.spaceinvaders;


import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Alliens
{
    int AllienNumber = 20;
    public int numbercount = 20;
    public Alliencraft[] ACraft;
    int i;
    int j;
    int AlliensHeight;
    int AlliensWidth;
    public float Allienspeed = 1;
    boolean mysship = false;
    Alliencraft mysteryship = new Alliencraft(0, 0);


    public Alliens(int Height, int Width)
    {
        ACraft = new Alliencraft[AllienNumber];
        for (i=0; i<4; i++){
            for (j=0; j<5; j++){
                ACraft[5*i+j] = new Alliencraft(Width/12*j, Height/18*i);
            }
        }
        mysteryship.alive = false;
        AlliensHeight = Height;
        AlliensWidth = Width;
    }

    public void draw(Canvas c, Bitmap Allien, Bitmap Sp)
    {
        for (i = 0; i < AllienNumber; i++){
            if (ACraft[i].checkalive())
            {
                c.drawBitmap(Allien, ACraft[i].getX(), ACraft[i].getY(), null);
            }
        }

        if (!mysteryship.alive) {
            mysship = mysshippossibility();
        }
        if (mysship && mysteryship.alive)
        {
            c.drawBitmap(Sp, mysteryship.getX(), mysteryship.getY(), null);
        }
    }

    public boolean mysshippossibility()
    {
        if (Math.random()>0.97)
        {
            mysteryship = new Alliencraft(0, 0);
            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean firepossibility()
    {
        if (Math.random()>0.9)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    boolean changedir = true;
    public void moving(int height, int width) {
        if (mysteryship.alive)
        {
            mysteryship.movement(mysteryship.getX()+20*Allienspeed,mysteryship.getY(),true);
            if (mysteryship.getX()>=width/12*11)
            {
                mysteryship.alive=false;
            }
        }


        if (changedir) {
            for (i = 0; i < AllienNumber; i++) {
                ACraft[i].movement(ACraft[i].getX() + 25*Allienspeed, ACraft[i].getY(), changedir);
            }
        }
        else{
            for (i = 0; i < AllienNumber; i++) {
                ACraft[i].movement(ACraft[i].getX() - 25*Allienspeed, ACraft[i].getY(), changedir);
            }
        }

        for (i=0;i<AllienNumber;i++)
        {
            if (ACraft[i].checkalive())
            {
                if (ACraft[i].getX()>=width/10*9 && changedir == true)
                {
                    for (j=0;j<AllienNumber;j++)
                    {
                        ACraft[j].movement(ACraft[j].getX(), ACraft[j].getY()+80,changedir);
                    }
                    changedir = false;
                }
            }
        }

        for (i=0;i<AllienNumber;i++)
        {
            if (ACraft[i].checkalive())
            {
                if (ACraft[i].getX()<=width/10 && changedir == false)
                {
                    for (j=0;j<AllienNumber;j++)
                    {
                        ACraft[j].movement(ACraft[j].getX(), ACraft[j].getY()+80,changedir);
                    }
                    changedir = true;
                }
            }
        }
    }
}
