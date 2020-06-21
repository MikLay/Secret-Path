package com.justdoit.secretpath;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {
    private static final String LOG_TAG =
            SettingsActivity.class.getSimpleName();

    private String LEVEL_KEY;
    private String PROGRESS_KEY;
    private SharedPreferences mPreferences;

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_levels);
//
//        mPreferences = getSharedPreferences(
//                getString(R.string.sharedPreferencesFileName), MODE_PRIVATE);
//
//        progress = mPreferences.getInt(getString(R.string.progressKey), 0);
//
//        levelNumber = findViewById(R.id.selectLevelNumber);
//        levelNumber.setText(String.valueOf(progress + 1));
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        LEVEL_KEY = getString(R.string.levelKey);
        PROGRESS_KEY = getString(R.string.progressKey);

        mPreferences = getSharedPreferences(
                getString(R.string.sharedPreferencesFileName), MODE_PRIVATE);


    }

    public void backButtonOnClick(View view) {
        Log.d(LOG_TAG, "BackButton clicked");
        finish();
    }

    public void switchSoundOnClick(View view) {
        Log.d(LOG_TAG, "SwitchSound toggled");

    }

    public void switchThemeOnClick(View view) {
        Log.d(LOG_TAG, "SwitchTheme toggled");

    }

    public void shareButtonOnClick(View view) {
        Log.d(LOG_TAG, "ShareButton clicked");

    }
}
