package com.ma.john.weebrunner;

import android.graphics.Bitmap;

/**
 * Created by John on 12/25/2016.
 */

public class Animation {

    private Bitmap [] frames;
    private int delay;
    private int currentFrame = 0;

    public void setFrame(Bitmap [] image){

        frames = image;

    }

    public void setDelay(int delay){

        this.delay = delay;

    }

    public void update(){

        currentFrame++;

        if(currentFrame >= frames.length){

            currentFrame -= currentFrame;

        }

    }

    public int getDelay(){

        return delay;

    }

    public Bitmap getImage(){

        return frames [currentFrame];

    }

}
