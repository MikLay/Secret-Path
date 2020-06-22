package com.justdoit.secretpath.levels;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.justdoit.secretpath.R;

public class SimplestLevelFragment extends LevelModelFragment {
    private static final String LOG_TAG =
            SimplestLevelFragment.class.getSimpleName();

    public SimplestLevelFragment() {
        this.levelDetails = new LevelDetails(
                1,
                "Дуже складно",
                new String[]{"Можливо вийдете й глянете як називається гра?", "Введіть назву гри, не засмучуйте мене..", "Secret ...", "Ну лол, Secret Pa..", "Secret Path"});
    }

    @Override
    protected View provideFragmentView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        Log.d(LOG_TAG, "Fragment rendering");
        View view = inflater.inflate(R.layout.level01, parent, false);
        //Now specific components here (you can initialize Buttons etc)
        return view;
    }

    @Override
    public void handleInput(String input) {
        Log.d(LOG_TAG, "Handle userInput");
        if (input.equalsIgnoreCase("Secret Path")) {
            this.callback.levelCompleted("Ну і як, складненько було?");
        }else{
            this.callback.wrongInput();
        }
    }
}
