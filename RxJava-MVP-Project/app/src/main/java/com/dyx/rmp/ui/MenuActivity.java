package com.dyx.rmp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.dyx.rmp.R;
import com.dyx.rmp.adapter.MenuAdapter;
import com.dyx.rmp.base.BaseActivity;
import com.dyx.rmp.util.ResUtil;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * project name：RxJava-MVP-Project
 * class describe：
 * create person：dayongxin
 * create time：16/8/3 下午3:52
 * alter person：dayongxin
 * alter time：16/8/3 下午3:52
 * alter remark：
 */
public class MenuActivity extends BaseActivity {
    @Bind(R.id.id_rv_menu)
    RecyclerView idRvMenu;

    private MenuAdapter mMenuAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        ButterKnife.bind(this);
        initView();
    }


    private void initView() {
        mMenuAdapter = new MenuAdapter(ResUtil.getResArray(this, R.array.menu));
        idRvMenu.setLayoutManager(new LinearLayoutManager(this));
        idRvMenu.setHasFixedSize(true);
        idRvMenu.setAdapter(mMenuAdapter);

        mMenuAdapter.setOnMyClickListener(new MenuAdapter.OnMyClickListener() {
            @Override
            public void onItemClick(int pos) {
                switch (pos) {
                    case 0:
                        intentTo(SimpleActivity.class);
                        break;
                    case 1:
                        intentTo(NetworkActivity.class);
                        break;
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
