package com.example.psit_project;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);



        ImageView imageView= findViewById(R.id.logo);
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade);
        imageView.startAnimation(animation);

        Thread td =new Thread(){
            public void run()
            {
                try {
                    sleep(4500);


                }catch (Exception e){
                    e.printStackTrace();

                }
                finally {
                    //Intent intent=new Intent(SplashActivity.this,MainActivity.class);
                    Intent intent=new Intent(SplashActivity.this,Login.class);
                    startActivity(intent);
                    finish();
                }
            }
        };td.start();
    }
}