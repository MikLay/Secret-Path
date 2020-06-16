package com.justdoit.secretpath;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class LevelsActivity extends AppCompatActivity {
    private static final String LOG_TAG =
            LevelsActivity.class.getSimpleName();

    private SharedPreferences mPreferences;
    private String sharedPrefFile =
            "com.justdoit.secretpath";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_levels);

        mPreferences = getSharedPreferences(
                sharedPrefFile, MODE_PRIVATE);
    }

}