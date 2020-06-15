package com.example.secretpath;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class LevelsActivity extends AppCompatActivity {

    private SharedPreferences mPreferences;
    private String sharedPrefFile =
            "com.example.secretpath";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_levels);

        mPreferences = getSharedPreferences(
                sharedPrefFile, MODE_PRIVATE);
    }




}
