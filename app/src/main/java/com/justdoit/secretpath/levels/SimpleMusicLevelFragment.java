package com.justdoit.secretpath.levels;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.justdoit.secretpath.R;

public class SimpleMusicLevelFragment  extends LevelModelFragment{

    private ImageView imageView;

    private static final String LOG_TAG =
            SimpleMusicLevelFragment.class.getSimpleName();


    public SimpleMusicLevelFragment() {
        this.levelDetails = new LevelDetails(
                3,
                "Melody...",
                new String[]{"I like nations, even if there are seven", "Something white and stripped"});
    }

    @Override
    protected View provideFragmentView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        Log.d(LOG_TAG, "Fragment rendering");
        View view = inflater.inflate(R.layout.level03, parent, false);
        imageView = (ImageView) view.findViewById(R.id.musicLogo);
        final MediaPlayer mediaPlayer = MediaPlayer.create(getContext(), R.raw.whitestripessample);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.start();
            }
        });
        return view;
    }



    @Override
    public void handleInput(String input) {
        Log.d(LOG_TAG, "Handle userInput");
        if (input.toLowerCase().contains("white stripes")) {
            this.callback.levelCompleted("Як вам трошки року??");
        }else{
            this.callback.wrongInput();
        }
    }
}
