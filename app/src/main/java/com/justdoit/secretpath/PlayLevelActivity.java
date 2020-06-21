package com.justdoit.secretpath;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.justdoit.secretpath.levels.LevelModelFragment;
import com.justdoit.secretpath.levels.SimplestLevelFragment;
import com.justdoit.secretpath.levels.WifiLevelFragment;

public class PlayLevelActivity extends AppCompatActivity implements LevelModelFragment.ActivityCallBack {

    private static final String LOG_TAG =
            PlayLevelActivity.class.getSimpleName();
    protected static final LevelModelFragment[] LEVELS = {
            new SimplestLevelFragment(),
            new WifiLevelFragment()
    };

    private static final String LEVEL_KEY = "current_level";
    private static final String PROGRESS_KEY = "progress";
    private static final String HINT_INDEX_KEY = "hint_index";

    private SharedPreferences mPreferences;
    private LevelModelFragment currentLevel;
    private EditText userInputEditText;
    private int hintIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_level);

        userInputEditText = findViewById(R.id.userInput);

        mPreferences = getSharedPreferences(getString(R.string.sharedPreferencesFileName), MODE_PRIVATE);
        setLevel(mPreferences.getInt(LEVEL_KEY, 0));
    }


    public void handleUserInput(View view) {
        Log.d(LOG_TAG, "Handled user input");
        currentLevel.handleInput(userInputEditText.getText().toString());
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

    public void hintsButtonOnClick(View view) {
        Log.d(LOG_TAG, "HintsButton clicked");

        String[] hints = currentLevel.getLevelDetails().getHints();

        new AlertDialog.Builder(this)
                .setTitle("Hint #" + (hintIndex + 1))
                .setMessage(hints[hintIndex])
                .show();

        if (hintIndex < hints.length - 1) {
            hintIndex++;

            SharedPreferences.Editor preferencesEditor = mPreferences.edit();
            preferencesEditor.putInt(HINT_INDEX_KEY + currentLevel.getId(), hintIndex);
            preferencesEditor.apply();
        }
    }

    @Override
    public void levelCompleted() {
        int levelId = mPreferences.getInt(LEVEL_KEY, 0);
        if (LEVELS.length > levelId + 1) {
            ++levelId;
            setLevel(levelId);

            SharedPreferences.Editor preferencesEditor = mPreferences.edit();
            preferencesEditor.putInt(LEVEL_KEY, levelId);

            int progress = mPreferences.getInt(PROGRESS_KEY, 0);
            if (progress < levelId) {
                preferencesEditor.putInt(PROGRESS_KEY, levelId);
            }
            preferencesEditor.apply();
        }

        levelsButtonOnClick(null);
    }

    private void setLevel(int id) {
        System.out.println(id);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (currentLevel == null) {
            Log.d(LOG_TAG, "Set new LevelFragment");
            currentLevel = LEVELS[id];
            fragmentTransaction.add(R.id.levelFragmentContainer, currentLevel);
            fragmentTransaction.commit();
        } else {
            // TODO fix level name rendering
            TextView titleView = findViewById(R.id.title);
            titleView.setText(currentLevel.getLevelDetails().getName());
            currentLevel = LEVELS[id];
            Log.d(LOG_TAG, "Set next LevelFragment");
            fragmentTransaction.replace(R.id.levelFragmentContainer, currentLevel);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }

        hintIndex = mPreferences.getInt(HINT_INDEX_KEY + id, 0);
    }
}
