package com.ma.john.weebrunner;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import java.util.Random;

/**
 * Created by John on 2016-12-28.
 */

public class Obstacle_One extends GameObject_Character {

    private Bitmap spike;
    private long startTime;
    private int quantity;
    private int y2;
    private int xSpeed;
    private Random rand = new Random();
    private int s1 = rand.nextInt(4)+1; //Issues may arise for s1 and s2 DEBUG WARNING
    private int s2 = rand.nextInt(4)+1;


    public Obstacle_One(Bitmap res, int n, int w, int h, int speed) {

        quantity = n;
        this.width = w;
        this.height = h;
        this.xSpeed = speed;

        final int scaleX = GamePanel.WIDTH/4;
        final int scaleY = GamePanel.HEIGHT/4;

        spike = Bitmap.createScaledBitmap(res, scaleX, scaleY, false); // Remember to adjust to screen

        this.x = GamePanel.WIDTH+scaleX; // Set to GamePanel.WIDTH + this.width

        if(quantity == 2){

            if(s1 == 1){

                this.y = 0; // Set to one of (0, GamePanel.HEIGHT/3, or 2*GamePanel.HEIGHT/3)

                if(s2 == 1){

                    this.y2 = 0;

                }else if(s2 == 2){

                    this.y2 = GamePanel.HEIGHT/3;

                }else{

                    this.y2 = 2*GamePanel.HEIGHT/3;

                }

            }else if(s1 == 2){

                this.y = GamePanel.HEIGHT/3; // Set to one of (0, GamePanel.HEIGHT/3, or 2*GamePanel.HEIGHT/3)

                if(s2 == 1){

                    this.y2 = 0;

                }else if(s2 == 2){

                    this.y2 = GamePanel.HEIGHT/3;

                }else{

                    this.y2 = 2*GamePanel.HEIGHT/3;

                }

            }else{

                this.y = 2*GamePanel.HEIGHT/3; // Set to one of (0, GamePanel.HEIGHT/3, or 2*GamePanel.HEIGHT/3)

                if(s2 == 1){

                    this.y2 = 0;

                }else if(s2 == 2){

                    this.y2 = GamePanel.HEIGHT/3;

                }else{

                    this.y2 = 2*GamePanel.HEIGHT/3;

                }

            }

        }else{

            if(s1 == 1){

                this.y = 0;
                this.y2 = -GamePanel.HEIGHT/3;

            }else if(s1 == 2){

                this.y = GamePanel.HEIGHT/3;
                this.y2 = -GamePanel.HEIGHT/3;

            }else{

                this.y = 2*GamePanel.HEIGHT/3;
                this.y2 = -GamePanel.HEIGHT/3;

            }

        }

    }

    public void draw(Canvas canvas){

        canvas.drawBitmap(spike, x, y, null);

        if(quantity == 2) {

            canvas.drawBitmap(spike, x, y2, null);

        }
    }

    public void update(){

        x-=xSpeed;

    }

    public int getY2(){

        return y2;

    }

    public Bitmap getBitmap(){

        return spike;

    }

}
