package com.justdoit.secretpath.levels;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.justdoit.secretpath.R;

public class WifiLevelFragment extends LevelModelFragment {

    private WifiManager wifiManager;

    private static final String LOG_TAG =
            WifiLevelFragment.class.getSimpleName();


    public WifiLevelFragment() {
        final String[] hints = {"Забудь про мережу назавжди", "А що вимикають перед взльотом?"};
        levelDetails = new LevelDetails(
                2,
                "Вжжж...",
                hints);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        wifiManager = (WifiManager) getActivity().getApplicationContext().getSystemService(Context.WIFI_SERVICE);
    }

    @Override
    protected View provideFragmentView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        Log.d(LOG_TAG, "Fragment rendering");
        View view = inflater.inflate(R.layout.level02, parent, false);
        //Now specific components here (you can initialize Buttons etc)
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(LOG_TAG, "Fragment rendering");

        IntentFilter intentFilter = new IntentFilter(WifiManager.WIFI_STATE_CHANGED_ACTION);
        getActivity().registerReceiver(wifiStateReceiver, intentFilter);
    }

    @Override
    public void onPause() {
        super.onPause();
        getActivity().unregisterReceiver(wifiStateReceiver);
    }

    @Override
    public void handleInput(String input) {
        Log.d(LOG_TAG, "Handle userInput");
        if (input.toLowerCase().contains("1234151")) {
            this.callback.levelCompleted("Як вам трошки року??");
        }else{
            this.callback.wrongInput();
        }
    }

    private BroadcastReceiver wifiStateReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            final String action = intent.getAction();
            if (action != null && action.equals(WifiManager.WIFI_STATE_CHANGED_ACTION)) {
                if (intent.getIntExtra(WifiManager.EXTRA_WIFI_STATE, WifiManager.WIFI_STATE_UNKNOWN) == WifiManager.WIFI_STATE_DISABLED) {
                    Log.d(LOG_TAG, "Wifi is disabled!");
                    callback.levelCompleted("Дякую за проходження");
                }
            }
        }
    };
}
