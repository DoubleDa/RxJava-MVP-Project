package com.dyx.rmp.ui;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.widget.Button;
import android.widget.TextView;

import com.dyx.rmp.R;
import com.dyx.rmp.base.BaseActivity;
import com.dyx.rmp.bean.Person;
import com.dyx.rmp.presenter.PersonInfoPresenter;
import com.dyx.rmp.view.IShowPersonView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SimpleActivity extends BaseActivity implements IShowPersonView {

    @Bind(R.id.btn_get)
    Button btnGet;
    @Bind(R.id.tv_name)
    TextView tvName;
    @Bind(R.id.tv_age)
    TextView tvAge;
    @Bind(R.id.tv_height)
    TextView tvHeight;
    @Bind(R.id.tv_widget)
    TextView tvWidget;
    @Bind(R.id.tv_sex)
    TextView tvSex;

    private ProgressDialog pd = null;
    private PersonInfoPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        presenter = new PersonInfoPresenter(this);

        pd = new ProgressDialog(this);
        pd.setMessage(getResources().getString(R.string.msg_loding));

    }

    @OnClick(R.id.btn_get)
    public void onClick() {
        presenter.getPersonInfoToShow(1);
    }

    @Override
    public void showLoading() {
        pd.show();
    }

    @Override
    public void dismissLoading() {
        pd.cancel();
    }

    @Override
    public void toNextActivity(Person person) {
        tvName.setText(person.getName());
        tvAge.setText(person.getAge() + "");
        tvWidget.setText(person.getWidget() + "");
        tvHeight.setText(person.getHeight() + "");
        tvSex.setText(person.getSex());
    }

    @Override
    public void showFailedError() {
        Snackbar.make(btnGet, getResources().getString(R.string.msg_error), Snackbar.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
