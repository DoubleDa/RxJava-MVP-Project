package com.dyx.rmp.model;

/**
 * project name：RxJava-MVP-Project
 * class describe：
 * create person：dayongxin
 * create time：16/8/3 上午11:19
 * alter person：dayongxin
 * alter time：16/8/3 上午11:19
 * alter remark：
 */
public interface IGetPerson {
    void getPersonInfo(int id, onPersonInfoListener listener);
}
