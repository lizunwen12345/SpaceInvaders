package com.example.kyle.spaceinvaders;


import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class SpaceThread extends Thread
{
    SpaceInvadersView sv;
    SurfaceHolder surfaceHolder;
    boolean running=true;
    public SpaceThread(SurfaceHolder surfaceHolder, SpaceInvadersView sv) {
        super();
        this.surfaceHolder= surfaceHolder;
        this.sv=sv;
    }

    public void setRunning(boolean running)
    {
        this.running = running;
    }
    @Override
    public void run() {
        SurfaceHolder sh = sv.getHolder();
    // Main game loop.
        while( !Thread.interrupted() ) {
    //You might want to do game specific processing in a method you call here
            Canvas c = sh.lockCanvas(null);
            try {
                synchronized(sh) {
                    sv.onDraw(c);  //draw on canvas and update
                    this.running=sv.running;
                    if(!this.running) {
                        return;
                    }
                }
            } catch (Exception e) {
            } finally {
                if ( c != null ) {
                    sh.unlockCanvasAndPost(c);
                }
            }
    // Set the frame rate by setting this delay
            try {
                Thread.sleep(3);
            } catch (InterruptedException e) {
    // Thread was interrupted while sleeping.
                return;
            }
        }
    }
}

