package com.example.jobsi;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.widget.NestedScrollView;

import com.pierfrancescosoffritti.androidyoutubeplayer.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.player.YouTubePlayerView;
import com.pierfrancescosoffritti.androidyoutubeplayer.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.player.listeners.YouTubePlayerInitListener;

public class Learn extends AppCompatActivity {

    CardView papaCard, pimientoCard, cebollaCard;
    NestedScrollView scroll;
    YouTubePlayerView youtubePlayer;
    LinearLayout btnListo;
    TextView descripcion, descripcion2;
    ImageView pala;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn);

        papaCard = findViewById(R.id.papaCard);
        pimientoCard = findViewById(R.id.pimientoCard);
        cebollaCard = findViewById(R.id.cebollaCard);
        scroll = findViewById(R.id.scroll);
        youtubePlayer = findViewById(R.id.youtubePlayer);
        btnListo = findViewById(R.id.btnListo);
        descripcion = findViewById(R.id.descripcion);
        descripcion2 = findViewById(R.id.descripcion2);
        pala = findViewById(R.id.pala);

        papaCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scroll.setVisibility(View.GONE);
                youtubePlayer.setVisibility(View.VISIBLE);
                btnListo.setVisibility(View.VISIBLE);
                descripcion.setVisibility(View.VISIBLE);
                descripcion2.setVisibility(View.VISIBLE);
                pala.setVisibility(View.VISIBLE);
            }
        });

        btnListo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scroll.setVisibility(View.VISIBLE);
                youtubePlayer.setVisibility(View.GONE);
                btnListo.setVisibility(View.GONE);
                descripcion.setVisibility(View.GONE);
                descripcion2.setVisibility(View.GONE);
                pala.setVisibility(View.GONE);
            }
        });

        getLifecycle().addObserver(youtubePlayer);

        youtubePlayer.initialize(new YouTubePlayerInitListener() {

            @Override
            public void onInitSuccess(@NonNull final YouTubePlayer initializedYoutubePlayer) {
                initializedYoutubePlayer.addListener(new AbstractYouTubePlayerListener() {
                    @Override
                    public void onReady() {
                        String videoId = "huTKy4PZNLw";
                        initializedYoutubePlayer.loadVideo(videoId, 0);
                    }
                });
            }
        }, true);


    }

    public void atrasRegistro(View view) {
        onBackPressed();
        finish();
    }
}