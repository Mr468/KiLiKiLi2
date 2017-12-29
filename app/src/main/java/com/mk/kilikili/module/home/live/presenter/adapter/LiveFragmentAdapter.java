package com.mk.kilikili.module.home.live.presenter.adapter;

import android.content.Context;
import android.util.SparseArray;
import android.util.SparseIntArray;

import com.mk.kilikili.R;
import com.mk.kilikili.module.home.live.entity.LiveAppIndexInfo;
import com.mk.kilikili.module.home.live.view.holder.LiveBannerViewViewHolder;
import com.mk.kilikili.module.home.live.view.holder.LivePartitionViewViewHolder;
import com.mk.kilikili.module.home.live.view.holder.LiveRecommendViewViewHolder;
import com.mk.kilikili.widget.recyclerview.BaseRecyclerViewAdapter;
import com.mk.kilikili.widget.recyclerview.BaseViewHolder;
import com.mk.mvpbase.presenter.Presenter;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.data;

/**
 * Created by Mr_468 on 2017/4/12.
 */

public class LiveFragmentAdapter extends BaseRecyclerViewAdapter<Object> {

    private final int TYPE_BANNER = 1;

    private final int TYPE_RECOMMEND = 2;

    private final int TYPE_PARTITION = 3;

    public LiveFragmentAdapter(Context context, Presenter presenter) {
        super(context, presenter);
    }

    @Override
    protected void onSetData(SparseIntArray layouts, SparseArray<Class<? extends BaseViewHolder>> holders) {
        layouts.put(TYPE_BANNER, R.layout.item_live_banner);
        holders.put(TYPE_BANNER, LiveBannerViewViewHolder.class);

        layouts.put(TYPE_RECOMMEND, R.layout.item_live_recommend);
        holders.put(TYPE_RECOMMEND, LiveRecommendViewViewHolder.class);

        layouts.put(TYPE_PARTITION, R.layout.item_live_partition);
        holders.put(TYPE_PARTITION, LivePartitionViewViewHolder.class);
    }

    @Override
    protected int onGetItemViewType(int position) {
        if (mData.get(position) instanceof List
                && ((List) mData.get(position)).size() != 0
                && ((List) mData.get(position)).get(0) instanceof LiveAppIndexInfo.DataBean.BannerBean) {
            return TYPE_BANNER;
        }
        if (mData.get(position) instanceof LiveAppIndexInfo.DataBean.RecommendDataBean) {
            return TYPE_RECOMMEND;
        }
        return TYPE_PARTITION;
    }
}
