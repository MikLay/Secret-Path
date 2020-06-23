package com.justdoit.secretpath.levels;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.justdoit.secretpath.R;

public class SkovorodaFragment extends LevelModelFragment {
    private static final String LOG_TAG = SkovorodaFragment.class.getSimpleName();


    public SkovorodaFragment() {
        final String[] hints = {"Цій людині належить ідея нерівної рівності"};
        levelDetails = new LevelDetails(5, "Високі думки", hints);
    }


    @Override
    protected View provideFragmentView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        Log.d(LOG_TAG, "Fragment rendering");
        View view = inflater.inflate(R.layout.level04, parent, false);
        return view;
    }

    @Override
    public void handleInput(String input) {
        Log.d(LOG_TAG, "Handle userInput");
        if (input.equalsIgnoreCase("сковорода")) {
            this.callback.levelCompleted("Це він!");
        }else{
            this.callback.wrongInput();
        }
    }
}
