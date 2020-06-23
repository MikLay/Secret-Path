package com.justdoit.secretpath.levels;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.justdoit.secretpath.R;

public class WrongLanguageLevelFragment extends LevelModelFragment {

    private static final String LOG_TAG =
            WrongLanguageLevelFragment.class.getSimpleName();

    public WrongLanguageLevelFragment()  {
        this.levelDetails = new LevelDetails(
                9,
                "Тільки не це",
                new String[]{"Таке стається часто коли користуєшся клавіатурою й не дивишся на екран", "Не забудь змінити мову вводу", "Спробуй ввести те ж саме на клавіатурі, але іншою мовою"});
    }

    @Override
    protected View provideFragmentView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        Log.d(LOG_TAG, "Fragment rendering");
        View view = inflater.inflate(R.layout.level09, parent, false);
        return view;
    }

    @Override
    public void handleInput(String input) {
        Log.d(LOG_TAG, "Handle userInput");
        if (input.equalsIgnoreCase("відповідь")) {
            this.callback.levelCompleted("Ехх і чому гугл раніше не придумав автоматично шукати з іншої клавіатури?");
        }else{
            this.callback.wrongInput();
        }
    }
}
