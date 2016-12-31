package com.ma.john.weebrunner;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * Created by John on 12/25/2016.
 */

public class Background {

    private Bitmap image;
    private int x,y;
    private int move;

    public Background (Bitmap res){

        final int scaleX = GamePanel.WIDTH;
        final int scaleY = GamePanel.HEIGHT;

        image = Bitmap.createScaledBitmap(res, scaleX, scaleY, false);

    }

    public void update(){

        x+=move;

        if(x < -GamePanel.WIDTH){

            x -= x;

        }

    }

    public void draw(Canvas canvas){

        canvas.drawBitmap(image, x, y, null);

        if(x < 0){

            canvas.drawBitmap(image, x+GamePanel.WIDTH, y, null);

        }

    }

    public float getWidth(){

        return image.getWidth();

    }

    public float getHeight(){

        return image.getHeight();

    }

    public void setVector(int move){

        this.move = move;

    }

}
