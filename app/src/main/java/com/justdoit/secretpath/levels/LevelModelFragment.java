package com.justdoit.secretpath.levels;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public abstract class LevelModelFragment extends Fragment {

    protected LevelDetails levelDetails;
    protected ActivityCallBack callback;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public LevelDetails getLevelDetails() {
        return levelDetails;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // We can add specific components here
        return provideFragmentView(inflater, container, savedInstanceState);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof ActivityCallBack) {
            callback = (ActivityCallBack) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement ActivityCallBack");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        callback = null;
    }

    protected abstract View provideFragmentView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState);


    public abstract void handleInput(String input);

    public interface ActivityCallBack {
        void levelCompleted();
    }
}
