package com.justdoit.secretpath;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.appcompat.app.AppCompatActivity;

import com.justdoit.secretpath.levels.LevelModelFragment;

public class LevelsActivity extends AppCompatActivity {
    private static final String LOG_TAG =
            LevelsActivity.class.getSimpleName();

    private SharedPreferences mPreferences;
    private int progress;
    private int levelsTotal = PlayLevelActivity.LEVELS.length;
    private TextView levelNumber;
    private int theme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mPreferences = getSharedPreferences(
                getString(R.string.sharedPreferencesFileName), MODE_PRIVATE);
        theme = mPreferences.getInt(getString(R.string.themeKey), 0);
        if (theme == 1) {
            setTheme(R.style.LightTheme);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_levels);

        progress = mPreferences.getInt(getString(R.string.progressKey), 0);
        levelNumber = findViewById(R.id.selectLevelNumber);
        levelNumber.setText(String.valueOf(mPreferences.getInt(getString(R.string.levelKey), 0) + 1));
    }

    public void menuButtonOnClick(View view) {
        Log.d(LOG_TAG, "MenuButton clicked");
        Intent menuIntent = new Intent(this, MainActivity.class);
        finish();
        startActivity(menuIntent);
    }

    public void settingsButtonOnClick(View view) {
        Log.d(LOG_TAG, "SettingsButton clicked");
        Intent settingsIntent = new Intent(this, SettingsActivity.class);
        startActivity(settingsIntent);
    }

    public void upButtonOnClick(View view) {
        Log.d(LOG_TAG, "UpButton clicked");
        int selected = Integer.parseInt((String) levelNumber.getText());
        int upperLimit = Math.min(progress + 1, levelsTotal);

        if (selected == upperLimit)
            levelNumber.setText("1");
        else
            levelNumber.setText(String.valueOf(selected + 1));
    }

    public void downButtonOnClick(View view) {
        Log.d(LOG_TAG, "DownButton clicked");
        int selected = Integer.parseInt((String) levelNumber.getText());
        int upperLimit = Math.min(progress + 1, levelsTotal);

        if (selected > 1)
            levelNumber.setText(String.valueOf(selected - 1));
        else
            levelNumber.setText(String.valueOf(upperLimit));
    }

    public void playButtonOnClick(View view) {
        Log.d(LOG_TAG, "PlayButton clicked");
        SharedPreferences.Editor preferencesEditor = mPreferences.edit();
        preferencesEditor.putInt(getString(R.string.levelKey), Integer.parseInt((String) levelNumber.getText()) - 1);
        preferencesEditor.apply();

        Intent playIntent = new Intent(this, PlayLevelActivity.class);
        finish();
        startActivity(playIntent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mPreferences.getInt(getString(R.string.themeKey), 0) != theme)
            recreate();
    }
}
