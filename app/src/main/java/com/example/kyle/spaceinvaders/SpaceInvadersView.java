package com.example.kyle.spaceinvaders;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class SpaceInvadersView extends SurfaceView implements SurfaceHolder.Callback
{
    int HEIGHT;
    int WIDTH;
    private SurfaceHolder holder;
    private Bitmap Allien = BitmapFactory.decodeResource(getResources(), R.drawable.allien1);
    private Bitmap Mysteryship = BitmapFactory.decodeResource(getResources(),R.drawable.mysteryship1);
    private Bitmap Airship = BitmapFactory.decodeResource(getResources(),R.drawable.airship1);

    Draw D;
    boolean running;

    public SpaceInvadersView(Context context)
    {
        super(context);
        getHolder().addCallback(this);
        setFocusable(true);
        holder = getHolder();
        HEIGHT = getHeight();
        WIDTH = getWidth();
    }

    SpaceThread st;
    boolean firsttime=true;

    @Override

    protected void onDraw(Canvas c) {
        c.drawColor(Color.BLACK);
        if (firsttime) {
            D = new Draw(HEIGHT, WIDTH);
            firsttime = false;
        }
        D.draw1(c, Allien, Mysteryship, Airship);

        this.running = D.gaming();
        if (!running) {
            Paint paint = new Paint();
            paint.setColor(Color.WHITE);
            paint.setStyle(Paint.Style.FILL);
            c.drawPaint(paint);

            paint.setColor(Color.BLACK);
            paint.setTextSize(60);
            c.drawText("GAME OVER", WIDTH/10*3, HEIGHT / 2, paint);
            c.drawText("You finished round " + D.roundcount, WIDTH/10*3, HEIGHT / 2 + HEIGHT / 10, paint);
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder)
    {
        HEIGHT = getHeight();
        WIDTH = getWidth();
        Canvas c = holder.lockCanvas(null);
        onDraw(c);
        holder.unlockCanvasAndPost(c);
        st = new SpaceThread(getHolder(), this);
        st.setRunning(true);
        st.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height)
    {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder)
    {
        st.interrupt();
    }
    @Override
    public boolean onTouchEvent(MotionEvent e)
    {
        float xxx;
        float yyy;
        if(e.getAction()==MotionEvent.ACTION_DOWN)
        {
            if (e.getY()<=getHeight()/2)
            {
                //fire
                D.fire();
                return true;
            }
            if(e.getY()>=getHeight()/2 && e.getX() <= getWidth()/2)
            {
                //move left
                xxx = D.Airship1.getX();
                yyy = D.Airship1.getY();
                if (xxx >= getWidth()/12)
                {
                    D.Airship1.movement(xxx-WIDTH/60, yyy);
                }
                return true;
            }
            if (e.getY()>=getHeight()/2 && e.getX() >= getWidth()/2)
            {
                //move right
                xxx = D.Airship1.getX();
                yyy = D.Airship1.getY();
                if (xxx<=getWidth()/10*8.5)
                {
                    D.Airship1.movement(xxx+WIDTH/60, yyy);
                }
                return true;
            }
        }
        else if(e.getAction()==MotionEvent.ACTION_UP)
        {
            return false;
        }
        else
        {
            return false;
        }
        return false;
    }




}


