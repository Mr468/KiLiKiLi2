package com.mk.kilikili.module.home.live.view;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;

import com.mk.kilikili.R;
import com.mk.kilikili.base.BaseFragment;
import com.mk.kilikili.module.home.live.presenter.adapter.LiveFragmentAdapter;
import com.mk.kilikili.module.home.live.entity.LiveAppIndexInfo;
import com.mk.kilikili.module.home.live.presenter.LivePresenter;
import com.mk.mvpbase.factory.RequiresPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static android.R.attr.data;

/**
 * Created by Mr_468 on 2017/3/28.
 */

@RequiresPresenter(LivePresenter.class)
public class LiveFragment extends BaseFragment<LivePresenter> {

    @BindView(R.id.layout_refresh)
    SwipeRefreshLayout refreshLayout;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private LiveFragmentAdapter adapter;

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_live;
    }

    @Override
    public void finishCreateView(Bundle state) {
    }

    @Override
    protected void onPreLazyLoad() {
        initRecyclerView();
        initRefreshLayout();
    }

    @Override
    protected void initRecyclerView() {
        adapter = new LiveFragmentAdapter(getContext(), getPresenter());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
    }

    @Override
    protected void initRefreshLayout() {
        refreshLayout.setRefreshing(true);
        TypedValue typedValue = new TypedValue();
        getContext().getTheme().resolveAttribute(R.attr.colorPrimary,typedValue,true);
        refreshLayout.setColorSchemeResources(typedValue.resourceId);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getPresenter().refresh();
            }
        });
    }

    @Override
    public <T> void onDataLoaded(T data) {
        super.onDataLoaded(data);
        showDataView(data);
    }

    @Override
    public <T> void showDataView(T data) {
        adapter.setData((List<Object>) data);
        refreshLayout.setRefreshing(false);
    }

    @Override
    public void showEmptyView() {
        refreshLayout.setRefreshing(false);
    }
}
