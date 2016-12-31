package com.ma.john.weebrunner;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.WindowManager;


public class MainActivity extends Activity {

    private Intent intent;
    private float x1, x2;
    private float y1, y2;
    private GamePanel game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Full Screen on Create
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(game = new GamePanel(this));


    }

    public boolean onTouchEvent(MotionEvent event){

        if(event.getAction() == MotionEvent.ACTION_DOWN){

            x1 = event.getX();
            y1 = event.getY();

        }

        if(event.getAction() == MotionEvent.ACTION_UP) {

            x2 = event.getX();
            y2 = event.getY();

            if (y1 > y2) {//up swipe

                game.changePlayerY(-GamePanel.HEIGHT/3);

            }

            if (y2 > y1) {//down swipe

                game.changePlayerY(GamePanel.HEIGHT/3);

            }

        }

            return false;

    }

    protected void onPause(){

        super.onPause();

    }

    protected void onDestroy() {

        super.onDestroy();

    }
}
