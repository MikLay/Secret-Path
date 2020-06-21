package com.justdoit.secretpath;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

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

    private final String LEVEL_KEY = getString(R.string.levelKey);
    private final String PROGRESS_KEY = getString(R.string.progressKey);

    private SharedPreferences mPreferences;
    private LevelModelFragment currentLevel;

    private EditText userInputEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_level);

        userInputEditText = findViewById(R.id.userInput);

        mPreferences = getSharedPreferences(
                getString(R.string.sharedPreferencesFileName), MODE_PRIVATE);
        setLevel(mPreferences.getInt(LEVEL_KEY, 0));
    }


    public void handleUserInput(View view) {
        Log.d(LOG_TAG, "Handled user input");
        currentLevel.handleInput(userInputEditText.getText().toString());
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
            currentLevel = LEVELS[id];
            Log.d(LOG_TAG, "Set next LevelFragment");
            fragmentTransaction.replace(R.id.levelFragmentContainer, currentLevel);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
    }
}
