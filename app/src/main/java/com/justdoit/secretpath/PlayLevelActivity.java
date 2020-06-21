package com.justdoit.secretpath;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.justdoit.secretpath.levels.LevelModelFragment;
import com.justdoit.secretpath.levels.SimplestLevelFragment;
import com.justdoit.secretpath.levels.WifiLevelFragment;

import java.util.Random;

public class PlayLevelActivity extends AppCompatActivity implements LevelModelFragment.ActivityCallBack {

    protected static final LevelModelFragment[] LEVELS = {
            new SimplestLevelFragment(),
            new WifiLevelFragment()
    };
    private static final String LOG_TAG =
            PlayLevelActivity.class.getSimpleName();
    private String LEVEL_KEY;
    private String PROGRESS_KEY;
    private static final String HINT_INDEX_KEY = "hint_index";

    private SharedPreferences mPreferences;
    private LevelModelFragment currentLevel;
    private EditText userInputEditText;
    private int theme;
    private int hintIndex;
    private String[] wrongText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mPreferences = getSharedPreferences(
                getString(R.string.sharedPreferencesFileName), MODE_PRIVATE);
        theme = mPreferences.getInt(getString(R.string.themeKey), 0);
        if (theme == 1) {
            setTheme(R.style.LightTheme);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_level);

        LEVEL_KEY = getString(R.string.levelKey);
        PROGRESS_KEY = getString(R.string.progressKey);

        wrongText = getResources().getStringArray(R.array.wrongInput);

        userInputEditText = findViewById(R.id.userInput);

        mPreferences = getSharedPreferences(
                getString(R.string.sharedPreferencesFileName), MODE_PRIVATE);

        System.out.println(mPreferences.getInt(PROGRESS_KEY, 0));
        setLevel(mPreferences.getInt(LEVEL_KEY, 0));
    }

    public void handleUserInput(View view) {
        Log.d(LOG_TAG, "Handled user input");
        if (!userInputEditText.getText().toString().equals(""))
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
    public void levelCompleted(String text) {
        int levelId = mPreferences.getInt(LEVEL_KEY, 0);
        if (LEVELS.length > levelId + 1) {
            final int nextLevel = levelId + 1;
            SharedPreferences.Editor preferencesEditor = mPreferences.edit();
            preferencesEditor.putInt(LEVEL_KEY, nextLevel);

            int progress = mPreferences.getInt(PROGRESS_KEY, 0);
            if (progress < nextLevel) {
                preferencesEditor.putInt(PROGRESS_KEY, nextLevel);
            }
            preferencesEditor.commit();

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Рівень пройдено!");
            builder.setMessage(text);

            builder.setPositiveButton("Продовжити", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    setLevel(nextLevel);
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        } else {

            levelsButtonOnClick(null);
        }
    }

    @Override
    public void wrongInput() {
        LayoutInflater inflater = getLayoutInflater();

        View toastLayout = inflater.inflate(R.layout.wrong_toast,
                (ViewGroup) findViewById(R.id.toast_root_view));

        TextView header = (TextView) toastLayout.findViewById(R.id.toast_header);
        header.setText("На жаль не вірно!");

        TextView body = (TextView) toastLayout.findViewById(R.id.toast_body);
        body.setText(wrongText[new Random().nextInt(wrongText.length)]);

        userInputEditText.setText("");

        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(toastLayout);
        toast.show();
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
            fragmentTransaction.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_in_right);
            fragmentTransaction.replace(R.id.levelFragmentContainer, currentLevel);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
            userInputEditText.setText("");
        }

        hintIndex = mPreferences.getInt(HINT_INDEX_KEY + id, 0);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mPreferences.getInt(getString(R.string.themeKey), 0) != theme)
            recreate();
    }
}
