package com.ma.john.weebrunner;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.Random;


/**
 * Created by John on 12/21/2016.
 */

public class GamePanel extends SurfaceView implements SurfaceHolder.Callback{

    public static final int WIDTH = getScreenWidth();
    public static final int HEIGHT = getScreenHeight();
    public static final int SPEED = WIDTH/120;
    private MainThread thread;
    private Background background;
    private Player player;
    private int spikeSpeed = WIDTH/240;
    private int difficultyDelay = 0;
    private int scoreMultiplier = 1;
    private Obstacle_One spike = new Obstacle_One(BitmapFactory.decodeResource(getResources(), R.drawable.obstacle1), 1, 256, 294, spikeSpeed);
    private Paint scorePaint;
    private Color col = new Color();
    private Context context;
    private Intent intent;


    //float x1, y1;
    //float x2, y2;

    public GamePanel(Context context) {
        super(context);

        //Interrupt Events
        getHolder().addCallback(this);
        this.context = context;


        thread = new MainThread(getHolder(), this);

        }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height){}

    @Override
    public void surfaceDestroyed(SurfaceHolder holder){

        boolean retry = true;
        while(retry){

            try{

                thread.setRunning(false);
                thread.join();

            }catch(InterruptedException e){e.printStackTrace();}

            retry = false;
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder){

        background = new Background(BitmapFactory.decodeResource(getResources(), R.drawable.background1));
        player = new Player(BitmapFactory.decodeResource(getResources(), R.drawable.standardflyingtsuchi), 97, 130, 4, HEIGHT/30);
        scorePaint = new Paint();
        scorePaint.setColor(col.WHITE);
        scorePaint.setTextSize(24);
        background.setVector(SPEED);
        thread.setRunning(true);
        thread.start();

    }

    @Override
    public boolean onTouchEvent(MotionEvent event){

       /* switch(event.getAction()){

            case MotionEvent.ACTION_DOWN:{

            x1 = event.getX();
            y1 = event.getY();
                break;

            }

            case MotionEvent.ACTION_UP:{

            x2 = event.getX();
            y2 = event.getY();

            if (y1 > y2) {

                player.setDesiredY(GamePanel.HEIGHT);

            }

            if (y2 > y1) {

                player.setDesiredY(GamePanel.HEIGHT/3);

            }

                break;
            }

        }

            return false;*/
        return super.onTouchEvent(event);

    }

    public void update(){

        background.update();
        player.update();

        if (spike.getX() < -spike.width) {

            Random rand = new Random();
            int n = rand.nextInt(3) + 1;

            difficultyDelay++;

            if(spikeSpeed >= WIDTH/120){

                difficultyDelay -= difficultyDelay;

            }

            //DEBUG this if statement is never called
            if(spikeSpeed >= WIDTH/240){

                n = 2;

            }

            if(difficultyDelay >= 5) {

                difficultyDelay -= difficultyDelay;
                spikeSpeed += WIDTH/1200;
                background.setVector(SPEED/2-spikeSpeed);
                scoreMultiplier *= 2;
                player.setMultiplier(scoreMultiplier);
                player.changeYSpeed(HEIGHT/480);

            }

            spike = new Obstacle_One(BitmapFactory.decodeResource(getResources(), R.drawable.obstacle1), n, 256, 294, spikeSpeed);

        }

        spike.update();

        if(Collisions.isCollisionDetected(player.getImage(), player.x, player.y, spike.getBitmap(),
                spike.x, spike.y) || Collisions.isCollisionDetected(player.getImage(), player.x, player.y, spike.getBitmap(),
                spike.x, spike.getY2())){

            thread.setRunning(false);
            intent = new Intent(context, MenuActivity.class);
            context.startActivity(intent);
            ((MainActivity)context).finish(); // Why is casting neccessary?

        }

    }

    @Override
    public void draw(Canvas canvas) {

        //final float scaleX = (WIDTH*1.f)/background.getWidth();
        //final float scaleY = (HEIGHT*1.f)/background.getHeight();

        if (canvas != null) {
        //final int saved = canvas.save();
        //canvas.scale(scaleX, scaleY);
        background.draw(canvas);
        //canvas.restoreToCount(saved);

       }

        player.draw(canvas);
        spike.draw(canvas);
        canvas.drawText("Score: "+String.valueOf(player.getScore())+" ("+String.valueOf(scoreMultiplier)+"x)"
                , 0, scorePaint.getTextSize(), scorePaint);

    }

    public void changePlayerY(int y){

        player.setDesiredY(y);

    }

    public static int getScreenWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }

    public static int getScreenHeight() {
        return Resources.getSystem().getDisplayMetrics().heightPixels;
    }

}
