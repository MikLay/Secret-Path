package com.justdoit.secretpath.levels;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.justdoit.secretpath.R;

import static android.content.Context.MODE_PRIVATE;

public class DarkThemeFragment extends LevelModelFragment {
    private static final String LOG_TAG = DarkThemeFragment.class.getSimpleName();

    public DarkThemeFragment() {
        levelDetails = new LevelDetails(
                6,
                "Темрява",
                new String[]{"Увімкни день", "Зроби все довкола світлішим"});
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected View provideFragmentView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        Log.d(LOG_TAG, "Fragment rendering");
        View view = inflater.inflate(R.layout.level06, parent, false);
        //Now specific components here (you can initialize Buttons etc)
        return view;
    }

    @Override
    public void handleInput(String input) {
        if(input.equalsIgnoreCase("sunshine")) {
            callback.levelCompleted("Так дійсно світліше");
        } else {
            this.callback.wrongInput();
        }
    }
}
