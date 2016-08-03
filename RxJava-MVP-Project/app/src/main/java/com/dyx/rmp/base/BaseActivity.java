package com.dyx.rmp.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * project name：RxJava-MVP-Project
 * class describe：
 * create person：dayongxin
 * create time：16/8/3 下午3:59
 * alter person：dayongxin
 * alter time：16/8/3 下午3:59
 * alter remark：
 */
public abstract class BaseActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void intentTo(Class<?> cla) {
        Intent intent = new Intent(this, cla);
        startActivity(intent);
    }


}
