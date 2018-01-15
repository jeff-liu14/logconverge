package com.moment.logconverge.test.main;

import com.moment.logconverge.test.base.BasePresenter;
import com.moment.logconverge.test.base.BaseView;

/**
 * Created by moment on 2017/11/27.
 */

public interface MainContract {

    interface MainPresenter extends BasePresenter {
        void getData();
    }

    interface MainView extends BaseView<MainPresenter> {
        void refreshViews(String txt);
    }
}
