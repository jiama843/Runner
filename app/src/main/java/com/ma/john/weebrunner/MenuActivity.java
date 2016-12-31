package com.ma.john.weebrunner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {

    private Intent intent;
    //private int finalScore =

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        final Button start = (Button) findViewById(R.id.Start);
        start.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                intent = new Intent(MenuActivity.this, MainActivity.class);
                startActivity(intent);
                finish();

            }
        });

    }

}
