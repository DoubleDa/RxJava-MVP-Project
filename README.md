# RxJava、MVP架构、Retrofit结合使用项目

## 项目概述

> 1.MVP架构

> 2.Rxjava解决主线程发出网络请求的问题

> 3.Retrofit进行网络请求

## 简单使用MVP架构

* MVP架构图示

	- View层：
		View层是由对用户可见的Activity、Fragment、ViewGroup、View组成。通过实现抽象接口与Presenter层进行业务逻辑的交互。
	- Presenter层：
		Presenter层是作为View层与Model层的“媒婆”存在。
	- Model层：
		Model层主要进行数据管理，包含回调接口、接口实现类。

![](https://github.com/DoubleDa/RxJava-MVP-Project/blob/master/images/MVP%E6%9E%B6%E6%9E%84.png?raw=true)

* MVP架构项目代码结构图

![](https://github.com/DoubleDa/RxJava-MVP-Project/blob/master/images/%E9%A1%B9%E7%9B%AE%E7%BB%93%E6%9E%84.png?raw=true)

Person.java在JavaEE中被称作Java Bean，在MVC架构中被称作Model，主要用于创建对象、数据的序列化与反序列化。

```java
private int id;
private String name;
private int age;
private double height;
private double widget;
private String sex;

set/get方法……
```

onPersonInfoListener.java接口定义回调方法

```java
public interface onPersonInfoListener {
    void getPersonInfoSuccess(Person person);
    void getPersonInfoFailed();
}
```

IGetPerson.java接口获取信息抽象类

```java
public interface IGetPerson {
    void getPersonInfo(int id, onPersonInfoListener listener);
}
```

GetPersonInfo.java类是抽象接口的实现类

```java
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
```

PersonInfoPresenter.java类持有Model层接口、类引用

```java
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

```

IShowPersonView.java抽象类用于定义实现类要实现的方法

```java
public interface IShowPersonView {
    void showLoading();

    void dismissLoading();

    void toNextActivity(Person person);

    void showFailedError();
}
```

MainActivity.java实现View层与Presenter层的交互

```java
public class MainActivity extends Activity implements IShowPersonView {

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
}
```