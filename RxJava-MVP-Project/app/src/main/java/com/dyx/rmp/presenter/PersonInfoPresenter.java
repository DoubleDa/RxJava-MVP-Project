package com.dyx.rmp.presenter;

import android.os.Handler;

import com.dyx.rmp.bean.Person;
import com.dyx.rmp.model.GetPersonInfo;
import com.dyx.rmp.model.IGetPerson;
import com.dyx.rmp.model.onPersonInfoListener;
import com.dyx.rmp.view.IShowPersonView;

/**
 * project name：RxJava-MVP-Project
 * class describe：
 * create person：dayongxin
 * create time：16/8/3 上午11:29
 * alter person：dayongxin
 * alter time：16/8/3 上午11:29
 * alter remark：
 */
public class PersonInfoPresenter {
    private IGetPerson mIGetPerson;
    private IShowPersonView mIShowPersonView;
    private Handler mHandler = new Handler();

    public PersonInfoPresenter(IShowPersonView mIShowPersonView) {
        this.mIGetPerson = new GetPersonInfo();
        this.mIShowPersonView = mIShowPersonView;
    }

    public void getPersonInfoToShow(int id) {
        mIShowPersonView.showLoading();
        mIGetPerson.getPersonInfo(id, new onPersonInfoListener() {
            @Override
            public void getPersonInfoSuccess(final Person person) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mIShowPersonView.toNextActivity(person);
                        mIShowPersonView.dismissLoading();
                    }
                });
            }

            @Override
            public void getPersonInfoFailed() {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mIShowPersonView.showFailedError();
                        mIShowPersonView.dismissLoading();
                    }
                });
            }
        });
    }

}
