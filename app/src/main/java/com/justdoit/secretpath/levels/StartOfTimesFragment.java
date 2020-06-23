package com.justdoit.secretpath.levels;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.justdoit.secretpath.R;

public class StartOfTimesFragment extends LevelModelFragment {
    private static final String LOG_TAG = StartOfTimesFragment.class.getSimpleName();

    public StartOfTimesFragment() {
        String[] hints = {"Подумай про початок в обчислювальній техніці", "Рік, що вважається початком відліку часу в комп'ютерах"};
        levelDetails = new LevelDetails(2, "Попрограмуємо?", hints);
    }



    @Override
    protected View provideFragmentView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        Log.d(LOG_TAG, "Fragment rendering");
        View view = inflater.inflate(R.layout.level03, parent, false);
        return view;
    }

    @Override
    public void handleInput(String input) {
        Log.d(LOG_TAG, "Handle userInput");
        if (input.equalsIgnoreCase("1970")) {
            this.callback.levelCompleted("Молодець! Рухаємось далі");
        }else{
            this.callback.wrongInput();
        }
    }
}
