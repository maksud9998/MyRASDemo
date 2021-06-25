package com.example.myrasdemo;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashScreenActivity extends AppCompatActivity {
    private static int timeOut=2500;
    Animation topanim,bottomanim;
    ImageView splash_screen_image;
    TextView splash_screen_text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        topanim = AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottomanim = AnimationUtils.loadAnimation(this,R.anim.bottom_animation);
        splash_screen_image = findViewById(R.id.splash_screen_image);
        splash_screen_text = findViewById(R.id.splash_screen_text);
        splash_screen_image.setAnimation(topanim);
        splash_screen_text.setAnimation(bottomanim);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(SplashScreenActivity.this,Register.class);
                startActivity(i);
                finish();
            }
        }, timeOut);
    }
}