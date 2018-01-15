package com.moment.logconverge.test.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by moment on 2017/11/27.
 */

public class BaseActivity extends AppCompatActivity {

    FragmentManager manager;
    FragmentTransaction transaction;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void addToActivity(int contentId, BaseFragment fragment) {
        if (manager == null) {
            manager = getSupportFragmentManager();
            if (transaction == null) {
                transaction = manager.beginTransaction();
            }
        }
        if (fragment != null) {
            transaction.add(contentId, fragment);
            transaction.commitAllowingStateLoss();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
