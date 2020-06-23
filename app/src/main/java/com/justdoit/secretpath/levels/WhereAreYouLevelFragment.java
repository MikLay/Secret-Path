package com.justdoit.secretpath.levels;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.justdoit.secretpath.R;

public class WhereAreYouLevelFragment extends LevelModelFragment {

    private static final String LOG_TAG =
            WhereAreYouLevelFragment.class.getSimpleName();

    public WhereAreYouLevelFragment() {
        final String[] hints = {"Хмм, а ці цифри часом не координати?", "А що якщо провести прямі між цими координатими", "В якому місці перетинаються координати", "Що там можуть вам зробити"};
        levelDetails = new LevelDetails(10, "Я пішов", hints);
    }

    @Override
    protected View provideFragmentView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        Log.d(LOG_TAG, "Fragment rendering");
        View view = inflater.inflate(R.layout.level10, parent, false);
        return view;
    }

    @Override
    public void handleInput(String input) {
        Log.d(LOG_TAG, "Handle userInput");
        if (input.equalsIgnoreCase("тату")) {
            this.callback.levelCompleted("Мені будь ласка сердечко на плечі!");
        }else{
            this.callback.wrongInput();
        }
    }
}
