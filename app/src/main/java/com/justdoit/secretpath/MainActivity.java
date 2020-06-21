package com.justdoit.secretpath;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private static final String LOG_TAG =
            MainActivity.class.getSimpleName();

    private String LEVEL_KEY;
    private String PROGRESS_KEY;
    private SharedPreferences mPreferences;
    private int progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LEVEL_KEY = getString(R.string.levelKey);
        PROGRESS_KEY = getString(R.string.progressKey);

        mPreferences = getSharedPreferences(
                getString(R.string.sharedPreferencesFileName), MODE_PRIVATE);

        progress = mPreferences.getInt(PROGRESS_KEY, 0);
    }

    public void playButtonOnClick(View view) {
        Log.d(LOG_TAG, "PlayButton clicked");
        Intent playIntent = new Intent(this, PlayLevelActivity.class);
        finish();
        startActivity(playIntent);
    }

    public void levelsButtonOnClick(View view) {
        Log.d(LOG_TAG, "LevelsButton clicked");
        Intent levelsIntent = new Intent(this, LevelsActivity.class);
        finish();
        startActivity(levelsIntent);
    }

    public void settingsButtonOnClick(View view) {
        Log.d(LOG_TAG, "SettingsButton clicked");
        Intent settingsIntent = new Intent(this, SettingsActivity.class);
        startActivity(settingsIntent);
    }

    @Override
    protected void onPause() {
        super.onPause();

    }
}
