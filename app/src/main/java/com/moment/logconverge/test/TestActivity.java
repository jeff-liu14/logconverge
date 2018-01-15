package com.moment.logconverge.test;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import com.moment.logconverge.LogConverge;
import com.moment.logconverge.cache.NetCacheUtil;
import com.moment.logconverge.test.R;
import com.moment.logconverge.test.base.BaseActivity;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

/**
 * Created by moment on 2018/1/10.
 */

public class TestActivity extends BaseActivity {

    private int count;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        findViewById(R.id.tv_test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                Map<String, Object> hashMap = new HashMap<String, Object>();
                hashMap.put("name", "moment");
                hashMap.put("age", 17);
                hashMap.put("gender", "male");
                LogConverge.create().log(hashMap);
            }
        });

        findViewById(R.id.tv_upload).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String json = LogConverge.create().getUploadLog();
                Toast.makeText(getApplicationContext(), json, Toast.LENGTH_SHORT).show();
                Observable.timer(1, TimeUnit.SECONDS)
                        .subscribe(new Consumer<Long>() {
                            @Override
                            public void accept(Long aLong) throws Exception {
                                LogConverge.create().onUploadSucc(true);
                            }
                        });
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}
