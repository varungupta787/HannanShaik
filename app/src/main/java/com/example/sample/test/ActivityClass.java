package com.example.sample.test;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;

public class ActivityClass extends Activity {
    private String TAG = "ActivityClass";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e(TAG, "onCreate : " + this.getClass().getSimpleName());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment, new FragmentClass());
        fragmentTransaction.commit();
    }
}
