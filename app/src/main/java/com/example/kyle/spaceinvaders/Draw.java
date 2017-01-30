package com.example.kyle.spaceinvaders;


import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Draw
{
    Alliens Alliens;
    Airship Airship1;
    ArrayList<Cannon> cannons = new ArrayList<Cannon>();
    ArrayList<Cannon> Ecannons = new ArrayList<Cannon>();
    int screenheight;
    int screenwidth;
    int i;
    int j;
    int k;
    public int roundcount;

    public Draw(int height, int width)
    {
        screenheight = height;
        screenwidth = width;
        Alliens = new Alliens(height, width);
        Airship1 = new Airship(screenwidth/2, screenheight/10*9, height, width);
    }

    public void draw1 (Canvas c, Bitmap Allien, Bitmap Mysteryship, Bitmap Airship)
    {
        Alliens.draw(c, Allien, Mysteryship);
        c.drawBitmap(Airship, Airship1.getX(), Airship1.getY(), null);

        for (i=0; i<cannons.size();i++)
        {
            if (cannons.get(i).checkflying())
            {
                Paint Ball = new Paint();
                Ball.setColor(Color.GREEN);
                c.drawCircle(cannons.get(i).getX()+screenwidth/25, cannons.get(i).getY()-screenheight/100, 12, Ball);
            }
        }

        for (j=0; j<Ecannons.size();j++)
        {
            if (Ecannons.get(j).checkflying())
            {
                Paint Ball2 = new Paint();
                Ball2.setColor(Color.RED);
                c.drawCircle(Ecannons.get(j).getX()+33,Ecannons.get(j).getY()+50, 12, Ball2);
            }
        }
    }

    public void fire()
    {
        cannons.add(new Cannon(Airship1.getX(), Airship1.getY()));
    }

    public boolean gaming()
    {
        for (i=0; i<cannons.size();i++)
        {
            if (cannons.get(i).checkflying())
            {
                cannons.get(i).movement(cannons.get(i).getX(), cannons.get(i).getY()-screenheight/50);
            }

            if (cannons.get(i).checkflying())
            {
                float cx = cannons.get(i).getX();
                float cy = cannons.get(i).getY();
                float mysX = Alliens.mysteryship.getX();
                float mysY = Alliens.mysteryship.getY();

                if (Alliens.mysteryship.checkalive())
                {
                    if (cx>=mysX-60 && cx <= mysX+35 && cy>=mysY-20 && cy<=mysY+30)
                    {
                        cannons.get(i).flying=false;
                        Alliens.mysteryship.alive=false;
                    }
                }

                for (k = 0; k < 20; k++)
                {
                    if (Alliens.ACraft[k].checkalive())
                    {
                        float xxx = Alliens.ACraft[k].getX();
                        float yyy = Alliens.ACraft[k].getY();
                        if (cx>=xxx-40 && cx<=xxx+25 && cy>=yyy-30 && cy<=yyy+60)
                        {
                            cannons.get(i).flying=false;
                            Alliens.ACraft[k].alive=false;
                            Alliens.numbercount--;
                            if (Alliens.numbercount==0)
                            {
                                roundcount++;
                                Alliens.Allienspeed=Alliens.Allienspeed+1;
                                Alliens.numbercount=20;
                                Alliens.ACraft = new Alliencraft[Alliens.AllienNumber];
                                for (int o=0; o<4; o++){
                                    for (int p=0; p<5; p++){
                                        Alliens.ACraft[5*o+p] = new Alliencraft(screenwidth/12*p, screenheight/18*o);
                                    }
                                }
                                Alliens.mysteryship = new Alliencraft(0, 0);
                                Alliens.mysteryship.alive = false;
                            return true;
                            }
                        }
                    }
                }
            }
        }

        if (Alliens.firepossibility())
        {
            int n = (int) (Math.random() * 20);
            if (Alliens.ACraft[n].checkalive())
            {
                float xx = Alliens.ACraft[n].getX();
                float yy = Alliens.ACraft[n].getY();
                Ecannons.add(new Cannon(xx, yy));
            }
        }

        for (j=0; j<Ecannons.size();j++)
        {
            if (Ecannons.get(j).checkflying())
            {
                Ecannons.get(j).movement(Ecannons.get(j).getX(), Ecannons.get(j).getY()+screenheight/50);
            }

            if (Ecannons.get(j).checkflying())
            {
                float x = Airship1.getX();
                float y = Airship1.getY();
                float cxx = Ecannons.get(j).getX();
                float cyy = Ecannons.get(j).getY();
                if (cxx>=x-50 && cxx<=x+50 && cyy>=y &&cyy<=y+90)
                {
                    return false;
                }
            }
        }

        Alliens.moving(screenheight, screenwidth);
        for (i=0;i<20;i++)
        {
            if (Alliens.ACraft[i].checkalive())
            {
                if (Alliens.ACraft[i].getY()>=screenheight*0.87)
                {
                    return false;
                }
            }
        }
        return true;
    }

}
