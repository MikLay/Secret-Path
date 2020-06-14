package com.example.secretpath;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {


    private static final String LOG_TAG =
            MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void playButtonOnClick(View view) {
        Log.d(LOG_TAG, "PlayButton clicked");
        Intent playIntent = new Intent(this, PlayLevelActivity.class);
        startActivity(playIntent);
    }

    public void levelsButtonOnClick(View view) {
        Log.d(LOG_TAG, "LevelsButton clicked");
        Intent levelsIntent = new Intent(this, LevelsActivity.class);
        startActivity(levelsIntent);
    }

    public void settingsButtonOnClick(View view) {
        Log.d(LOG_TAG, "SettingsButton clicked");
        Intent settingsIntent = new Intent(this, SettingsActivity.class);
        startActivity(settingsIntent);
    }
}
