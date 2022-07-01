package com.example.jobsi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class HomeLogin extends AppCompatActivity {
    ConstraintLayout loginlinear;
    Animation animacion1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_login);

        animacion1 = AnimationUtils.loadAnimation(HomeLogin.this, R.anim.bottom_to_up);
        loginlinear = findViewById(R.id.loginlinear);
        loginlinear.setAnimation(animacion1);

    }

    public void pasarLogin(View v) {
        Intent intent = new Intent(HomeLogin.this, Login.class);
        startActivity(intent);
    }

    public void pasarRegistrar(View v) {
        Intent intent = new Intent(HomeLogin.this, Registrar.class);
        startActivity(intent);
    }
}