package com.marah.moblelibrarys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplachScreenActivity extends AppCompatActivity {
    private final int SPLASH_DISPLAY_LENGTH = 2000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splach_screen);

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                Intent mainIntent = new Intent(SplachScreenActivity.this,HomeActivity.class);
                SplachScreenActivity.this.startActivity(mainIntent);
                SplachScreenActivity.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }

}