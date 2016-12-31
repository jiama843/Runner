package com.ma.john.weebrunner;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import static java.lang.Math.abs;

/**
 * Created by John on 12/25/2016.
 */

public class Player extends GameObject_Character{

    private Bitmap spriteSheet;
    private int score;
    //private double xSpeed;
    private int ySpeed;
    private boolean playing;
    private Animation animate = new Animation();
    private long startTime;
    private int multiplier = 1;
    private int currentTime = 0;
    private int desiredY = GamePanel.HEIGHT/3;
    private int initialY = GamePanel.HEIGHT/3;

    public Player (Bitmap res, int w, int h, int numSprites, int speed){

        spriteSheet = res;
        x = GamePanel.WIDTH/20;
        y = GamePanel.HEIGHT/3;
        score = 0;
        width = w;
        height = h;
        ySpeed = speed;
        final int scaleX = GamePanel.WIDTH/6;
        final int scaleY = GamePanel.HEIGHT/3;

        Bitmap [] image = new Bitmap [numSprites];

        for(int i = 0; i < numSprites; i++){

            image [i] = Bitmap.createScaledBitmap(Bitmap.createBitmap(spriteSheet, i*spriteSheet.getWidth()/4 , 0, spriteSheet.getWidth()/4, spriteSheet.getHeight())
            , scaleX, scaleY, false);

        }

        animate.setFrame(image);
        animate.setDelay(10);
        startTime = System.nanoTime();

    }

    public void update(){

        currentTime++;

        //long elapsedTime = (System.nanoTime() - startTime)/1000000;
        //if(elapsedTime >= 1000){

            score += multiplier;
        //    startTime = (System.nanoTime());

        //}

        if(currentTime == animate.getDelay()) {

            animate.update();
            currentTime -= currentTime;

        }

    }

   //public static Bitmap createCroppedBitmap(Bitmap src, int top, int left, int width, int height) {
        /*
            bug: returns incorrect region, so must do it manually
            return Bitmap.createBitmap(src, left, top,width, height);
        */
     /*   int stride = width; // ints per row, if want padding at end of row, make stride larger
        int offset = 0;
        int []pixels = new int[width*height];
        src.getPixels(pixels, offset, stride, left, top, width, height);
        return Bitmap.createBitmap(pixels, width, height, src.getConfig());

    }*/

    public void draw (Canvas canvas){

        // Use initialY > desiredY && y <= desiredY || initialY < desiredY && y >= desiredY
        // BUG NOTICE: INSPECT initialY > desiredY && y <= desiredY WITH RESPECT TO CHANGES IN desiredY caused in
        // setDesiredY.

        if(initialY == desiredY || initialY > desiredY && y <= desiredY || initialY < desiredY && y >= desiredY){

            setY(desiredY);
            initialY = desiredY;
            canvas.drawBitmap(animate.getImage(), x, y, null);

        }else if(desiredY > initialY){

            setY(y+ySpeed);
            canvas.drawBitmap(animate.getImage(), x, y, null);

        }else{

            setY(y-ySpeed);
            canvas.drawBitmap(animate.getImage(), x, y, null);

        }

    }

    public void setX(int x){

        this.x = x;

    }

    public void setY(int y){

        this.y = y;

    }

    public void setDesiredY(int y){

        if((this.y+y) < 0){

            this.initialY = this.y;
            this.desiredY = 0;

        }else if((this.y+y) > 2*GamePanel.HEIGHT/3) {

            this.initialY = this.y;
            this.desiredY = 2*GamePanel.HEIGHT/3;

        }else {

            this.initialY = this.y;
            this.desiredY += y;

        }

    }

    public int getScore(){

        return score;

    }

    public boolean getPlaying(){

        return playing;

    }

    public void setPlaying(boolean play){

        playing = play;

    }

    public void resetScore(){

        score = 0;

    }

    public Bitmap getImage(){

        return animate.getImage();

    }

    public void setMultiplier(int mult){

        this.multiplier = mult;

    }

    public void changeYSpeed(int v){

        this.ySpeed += v;

    }

}
