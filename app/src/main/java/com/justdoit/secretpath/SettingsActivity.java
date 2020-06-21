package com.justdoit.secretpath;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {
    private static final String LOG_TAG =
            SettingsActivity.class.getSimpleName();

    private String THEME_KEY;
    private String VOLUME_KEY;
    private SharedPreferences mPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        THEME_KEY = getString(R.string.themeKey);
        VOLUME_KEY = getString(R.string.volumeKey);

        mPreferences = getSharedPreferences(
                getString(R.string.sharedPreferencesFileName), MODE_PRIVATE);

        int theme = mPreferences.getInt(THEME_KEY, 0); // Dark theme by default
        int volume = mPreferences.getInt(VOLUME_KEY, 1); // Sounds on by default

        if (theme == 1) {
            setTheme(R.style.LightTheme);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        setThemeLayoutState(theme);
        setVolumeLayoutState(volume);
    }

    public void backButtonOnClick(View view) {
        Log.d(LOG_TAG, "BackButton clicked");
        finish();
    }

    public void switchSoundOnClick(View view) {
        Log.d(LOG_TAG, "SwitchSound toggled");
        Switch volumeSwitch = findViewById(R.id.settingsSoundSwitch);
        ImageView volumeImage = findViewById(R.id.settingsSoundImage);
        if (volumeSwitch.isChecked()) {
            volumeImage.setImageDrawable(getResources().getDrawable(R.drawable.ic_volume_on));

            SharedPreferences.Editor preferencesEditor = mPreferences.edit();
            preferencesEditor.putInt(VOLUME_KEY, 1);
            preferencesEditor.apply();
        } else {
            volumeImage.setImageDrawable(getResources().getDrawable(R.drawable.ic_volume_off));

            SharedPreferences.Editor preferencesEditor = mPreferences.edit();
            preferencesEditor.putInt(VOLUME_KEY, 0);
            preferencesEditor.apply();
        }
    }

    public void switchThemeOnClick(View view) {
        Log.d(LOG_TAG, "SwitchTheme toggled");
        Switch themeSwitch = findViewById(R.id.settingsThemeSwitch);
        if (themeSwitch.isChecked()) {
            SharedPreferences.Editor preferencesEditor = mPreferences.edit();
            preferencesEditor.putInt(THEME_KEY, 1);
            preferencesEditor.apply();

            setTheme(R.style.LightTheme);
            recreate();
        } else {
            SharedPreferences.Editor preferencesEditor = mPreferences.edit();
            preferencesEditor.putInt(THEME_KEY, 0);
            preferencesEditor.apply();

            setTheme(R.style.AppTheme);
            recreate();
        }
    }

    public void shareButtonOnClick(View view) {
        Log.d(LOG_TAG, "ShareButton clicked");
        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        String shareBody = "Я зіграв в Secret Path! Спробуй і ти: https://github.com/MikLay/Secret-Path";
        sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "Secret Path");
        sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
        startActivity(Intent.createChooser(sharingIntent, "Share via"));
    }

    private void setThemeLayoutState(int theme) {
        Switch themeSwitch = findViewById(R.id.settingsThemeSwitch);
        if (theme == 1) {
            themeSwitch.setChecked(true);
        } else {
            themeSwitch.setChecked(false);
        }
    }

    private void setVolumeLayoutState(int volume) {
        Switch volumeSwitch = findViewById(R.id.settingsSoundSwitch);
        ImageView volumeImage = findViewById(R.id.settingsSoundImage);
        if (volume == 1) {
            volumeSwitch.setChecked(true);
            volumeImage.setImageDrawable(getResources().getDrawable(R.drawable.ic_volume_on));
        } else {
            volumeSwitch.setChecked(false);
            volumeImage.setImageDrawable(getResources().getDrawable(R.drawable.ic_volume_off));
        }
    }
}
