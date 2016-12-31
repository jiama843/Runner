package com.ma.john.weebrunner;

import android.graphics.Rect;

/**
 * Created by John on 12/25/2016.
 */

public abstract class GameObject_Character {

    protected int x;
    protected int y;
    protected int xSpeed;
    protected int ySpeed;
    protected int width;
    protected int height;

    public void setX(int x){

        this.x = x;

    }

    public void setY(int y){

        this.y = y;

    }

    public int getX(){

        return x;

    }

    public int getY(){

        return y;

    }

    public int getHeight(){

        return height;

    }

    public int getWidth(){

        return width;

    }

    public Rect getRectangle(){

        return new Rect(x, y, x+width, y+height);

    }

}
