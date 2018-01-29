package com.moment.logconverge.test.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.moment.logconverge.LogConverge;
import com.moment.logconverge.download.FileDownloaderManager;
import com.moment.logconverge.test.TestActivity;
import com.moment.logconverge.test.R;
import com.moment.logconverge.test.base.BaseFragment;

import java.io.File;
import java.util.List;

/**
 * Created by moment on 2017/11/27.
 */

public class MainFragment extends BaseFragment implements MainContract.MainView {

    MainContract.MainPresenter presenter;
    TextView textView, delete, tv_download;
    private static final String url = "";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, null);
        textView = view.findViewById(R.id.tv_name);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<String> list = LogConverge.create().getAllLogs();
                if (list != null && list.size() > 0) {
                    Toast.makeText(getActivity(), list.size() + "", Toast.LENGTH_SHORT).show();
                }
                Intent intent = new Intent(getActivity(), TestActivity.class);
                startActivity(intent);

            }
        });
        delete = view.findViewById(R.id.tv_name_);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogConverge.create().clearAllLog();
            }
        });

        tv_download = view.findViewById(R.id.tv_download);

        FileDownloaderManager.init(getActivity());
        try {
            FileDownloaderManager.registerDownload(new FileDownloaderManager.DownloadCallback() {
                @Override
                public void onPrepare() {

                }

                @Override
                public void onProgress(int progress) {

                }

                @Override
                public void onComplete(File file) {

                }

                @Override
                public void onFail(String msg) {

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

        tv_download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    FileDownloaderManager.download(url);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        FileDownloaderManager.unbinder();
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.start();
    }

    @Override
    public void setPresenter(MainContract.MainPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void refreshViews(String txt) {
        textView.setText("" + txt);
        Toast.makeText(getActivity(), txt, Toast.LENGTH_SHORT).show();
    }
}
