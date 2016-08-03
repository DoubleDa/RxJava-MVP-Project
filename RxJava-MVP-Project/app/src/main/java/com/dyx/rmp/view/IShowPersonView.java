package com.dyx.rmp.view;

import com.dyx.rmp.bean.Person;

/**
 * project name：RxJava-MVP-Project
 * class describe：
 * create person：dayongxin
 * create time：16/8/3 上午11:28
 * alter person：dayongxin
 * alter time：16/8/3 上午11:28
 * alter remark：
 */
public interface IShowPersonView {
    void showLoading();

    void dismissLoading();

    void toNextActivity(Person person);

    void showFailedError();
}
