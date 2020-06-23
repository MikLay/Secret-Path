package com.justdoit.secretpath.levels;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.justdoit.secretpath.R;

public class CowLevelFragment extends LevelModelFragment {

    private static final String LOG_TAG =
            CowLevelFragment.class.getSimpleName();


    public CowLevelFragment() {
            this.levelDetails = new LevelDetails(
                    8,
                    "Хитра корівка",
                    new String[]{"А раптом корівка почне тонути?", "А раптом корівка - шпигунка?", "А як подати SOS?", "Що це за секретна мова з короткими та довгими гудками?"});
    }

    @Override
    protected View provideFragmentView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        Log.d(LOG_TAG, "Fragment rendering");
        View view = inflater.inflate(R.layout.level08, parent, false);
        return view;
    }

    @Override
    public void handleInput(String input) {
        Log.d(LOG_TAG, "Handle userInput");
        if (input.equalsIgnoreCase("power")) {
            this.callback.levelCompleted("Ну тепер SOS ви точно зможете надіслати!");
        }else{
            this.callback.wrongInput();
        }
    }
}
