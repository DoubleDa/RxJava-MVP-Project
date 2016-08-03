package com.dyx.rmp.ui;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;

import com.dyx.rmp.R;
import com.dyx.rmp.adapter.NetworkAdapter;
import com.dyx.rmp.base.BaseActivity;
import com.dyx.rmp.bean.json.NewsBean;
import com.dyx.rmp.constant.ApiConstant;
import com.dyx.rmp.service.NewsService;
import com.dyx.rmp.widget.DividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;


/**
 * project name：RxJava-MVP-Project
 * class describe：
 * create person：dayongxin
 * create time：16/8/3 下午4:59
 * alter person：dayongxin
 * alter time：16/8/3 下午4:59
 * alter remark：
 */
public class NetworkActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener {
    private static final String TAG = "NetworkActivity";
    @Bind(R.id.id_recycler_view)
    RecyclerView idRecyclerView;
    @Bind(R.id.id_swipe_refresh_layout)
    SwipeRefreshLayout idSwipeRefreshLayout;

    private NetworkAdapter mNetworkAdapter;

    private int lastVisibleItem;

    private LinearLayoutManager manager;

    private boolean isRefresh = false;
    private int currentPage = 12;

    private List<NewsBean.TngouEntity> listData = new ArrayList<>();

    Retrofit retrofit = new Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create()).baseUrl(ApiConstant.HOST_API).build();

    NewsService service = retrofit.create(NewsService.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    private void initView() {
        /**
         *  RecyclerView
         */
        manager = new LinearLayoutManager(this);
        idRecyclerView.setLayoutManager(manager);
        idRecyclerView.setHasFixedSize(true);
        idRecyclerView.setItemAnimator(new DefaultItemAnimator());
        idRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        mNetworkAdapter = new NetworkAdapter(listData, NetworkActivity.this);
        idRecyclerView.setAdapter(mNetworkAdapter);


        idRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItem = manager.findLastVisibleItemPosition();
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE
                        && lastVisibleItem + 1 == mNetworkAdapter.getItemCount()) {
                    currentPage += 12;
                    fetchDataFromServer();
                    idSwipeRefreshLayout.setRefreshing(true);
                }
            }
        });

        /**
         * SwipeRefreshLayout
         */
        idSwipeRefreshLayout.setColorSchemeResources(R.color.red, R.color.orange, R.color.green, R.color.blue);
        idSwipeRefreshLayout.setOnRefreshListener(this);
        idSwipeRefreshLayout.setProgressViewOffset(false, 0, (int) TypedValue
                .applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources()
                        .getDisplayMetrics()));
    }


    private void initData() {
        fetchDataFromServer();
    }

    private void fetchDataFromServer() {
        idSwipeRefreshLayout.setRefreshing(true);
        service.news(currentPage).subscribeOn(Schedulers.newThread()).observeOn(Schedulers.io()).doOnNext(new Action1<NewsBean>() {
            @Override
            public void call(NewsBean newsBean) {

            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<NewsBean>() {
            @Override
            public void onCompleted() {
                idSwipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onError(Throwable e) {
                idSwipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onNext(NewsBean newsBean) {
                if (newsBean.getTngou().size() > 0) {
                    idSwipeRefreshLayout.setVisibility(View.VISIBLE);
                    if (currentPage == 12) {
                        listData.clear();
                        listData.addAll(newsBean.getTngou());
                        mNetworkAdapter.notifyDataSetChanged();
                    } else {
                        listData.addAll(listData.size(), newsBean.getTngou());
                        mNetworkAdapter.notifyItemChanged(listData.size());
                    }
                }
            }
        });
    }

    @Override
    public void onRefresh() {
        isRefresh = true;
        loadRefreshData();
    }

    private void loadRefreshData() {
        currentPage = 12;
        listData.clear();
        fetchDataFromServer();
    }
}
