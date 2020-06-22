package com.justdoit.secretpath.levels;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.justdoit.secretpath.R;

public class SecretPathSongFragment extends LevelModelFragment {
    private static final String LOG_TAG = SecretPathSongFragment.class.getSimpleName();

    public SecretPathSongFragment() {
        String[] hints = {"Послухай музику та відпочинь", "Знаєш музичний альбом 'Secret Path'?"};
        levelDetails = new LevelDetails(5, "Меломанія SP", hints);
    }

    @Override
    protected View provideFragmentView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        Log.d(LOG_TAG, "Fragment rendering");
        View view = inflater.inflate(R.layout.level05, parent, false);
        return view;
    }

    @Override
    public void handleInput(String input) {
        Log.d(LOG_TAG, "Handle userInput");
        if (input.equalsIgnoreCase("6")) {
            this.callback.levelCompleted("Молодець, це було не просто!");
        }else{
            this.callback.wrongInput();
        }
    }
}
