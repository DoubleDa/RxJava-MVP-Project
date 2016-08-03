package com.dyx.rmp.model;

import com.dyx.rmp.bean.Person;

/**
 * project name：RxJava-MVP-Project
 * class describe：
 * create person：dayongxin
 * create time：16/8/3 上午11:19
 * alter person：dayongxin
 * alter time：16/8/3 上午11:19
 * alter remark：
 */
public class GetPersonInfo implements IGetPerson {

    @Override
    public void getPersonInfo(final int id, final onPersonInfoListener listener) {
        new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (id == 1) {
                    Person person = new Person();
                    person.setName("哒哒");
                    person.setAge(18);
                    person.setWidget(69.5);
                    person.setHeight(175);
                    person.setSex("man");
                    listener.getPersonInfoSuccess(person);
                } else {
                    listener.getPersonInfoFailed();
                }
            }
        }.start();
    }
}
