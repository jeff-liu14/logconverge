package com.moment.logconverge.test;

import android.os.Bundle;

import com.moment.logconverge.test.base.BaseActivity;
import com.moment.logconverge.test.main.MainFragment;
import com.moment.logconverge.test.main.MainPresenter;
import com.moment.logconverge.test.R;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MainFragment fragment = new MainFragment();
        addToActivity(R.id.fl_content, fragment);
        new MainPresenter(fragment);
    }
}
