package com.androdude.codeconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.TextView;

public class splash_screen extends AppCompatActivity {
    private ImageView i;
    private TextView t1,t2;
    private Handler h = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        setUI();

        i.setTranslationY(1000);
        t1.setTranslationY(1000);
        t2.setTranslationY(1000);

        i.animate().translationY(0).setDuration(1500);

        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                t1.animate().translationY(0).setDuration(1500);
            }
        },1500);

        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                t2.animate().translationY(0).setDuration(1500);
            }
        },2000);
       Handler h = new Handler();
        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
                overridePendingTransition(R.anim.fadein,R.anim.fadeout);
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        },3500);
    }

    private void setUI() {
        i= findViewById(R.id.logo);
        t1 = findViewById(R.id.text1);
        t2 = findViewById(R.id.text2);
    }
}
