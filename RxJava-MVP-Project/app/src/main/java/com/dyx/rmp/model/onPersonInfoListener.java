package com.dyx.rmp.model;

import com.dyx.rmp.bean.Person;

/**
 * project name：RxJava-MVP-Project
 * class describe：
 * create person：dayongxin
 * create time：16/8/3 上午11:20
 * alter person：dayongxin
 * alter time：16/8/3 上午11:20
 * alter remark：
 */
public interface onPersonInfoListener {
    void getPersonInfoSuccess(Person person);

    void getPersonInfoFailed();
}
