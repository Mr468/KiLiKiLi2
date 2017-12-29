package com.mk.kilikili.module.home.recommend.presenter.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mk.kilikili.R;
import com.mk.kilikili.image.GlideUtils;
import com.mk.kilikili.module.home.recommend.entity.RecommendInfo;
import com.mk.kilikili.widget.recyclerview.BaseRecyclerViewAdapter;
import com.mk.kilikili.widget.recyclerview.BaseViewHolder;
import com.mk.kilikili.widget.recyclerview.decoration.CardItemDecoration;
import com.mk.mvpbase.presenter.Presenter;

import butterknife.BindView;


/**
 * Created by Mr_468 on 2017/4/19.
 */

public class RecommendFragmentAdapter extends BaseRecyclerViewAdapter<RecommendInfo.ResultBean> {

    private final int TYPE_REGION = 1;

    private final int TYPE_WEB = 2;

    private final int TYPE_ACTIVITY = 3;

    public RecommendFragmentAdapter(Context context, Presenter presenter) {
        super(context, presenter);
    }

    @Override
    protected void onSetData(SparseIntArray layouts, SparseArray<Class<? extends BaseViewHolder>> holders) {

        layouts.put(TYPE_REGION, R.layout.item_recommend_region);
        holders.put(TYPE_REGION, RegionViewHolder.class);

        layouts.put(TYPE_WEB, R.layout.item_recommend_web);
        holders.put(TYPE_WEB, WebViewHolder.class);

        layouts.put(TYPE_ACTIVITY, R.layout.item_recommend_activity);
        holders.put(TYPE_ACTIVITY, ActivityViewHolder.class);
    }

    @Override
    protected int onGetItemViewType(int position) {
        switch (mData.get(position).getType()) {
            case "weblink":
                return TYPE_WEB;
            case "activity":
                return TYPE_ACTIVITY;
        }
        return TYPE_REGION;
    }
}

class RegionViewHolder extends BaseViewHolder<RecommendInfo.ResultBean> {

    @BindView(R.id.tv_partition_name)
    TextView tv_partition_name;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    RecommendRegionAdapter adapter;

    public RegionViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void convert(RecommendInfo.ResultBean data, int position) {
        tv_partition_name.setText(data.getHead().getTitle());
        if (adapter == null) {
            adapter = new RecommendRegionAdapter(mContext, null, R.layout.item_live_cardview
                    , RecommendRegionAdapter.RecommendRegionViewHolder.class);
            recyclerView.setLayoutManager(new GridLayoutManager(mContext,2));
            recyclerView.setAdapter(adapter);
            recyclerView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    return event.getAction() == MotionEvent.ACTION_CANCEL;
                }
            });
            recyclerView.addItemDecoration(new CardItemDecoration(24,2,0));
        }
        adapter.setData(data.getBody());
    }
}

class WebViewHolder extends BaseViewHolder<RecommendInfo.ResultBean> {

    @BindView(R.id.iv_cardview_media)
    ImageView iv_cardview_media;

    public WebViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void convert(RecommendInfo.ResultBean data, int position) {
        GlideUtils.loadCardImage(mContext, data.getBody().get(0).getCover(), iv_cardview_media);
    }
}

class ActivityViewHolder extends BaseViewHolder<RecommendInfo.ResultBean> {

    public ActivityViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void convert(RecommendInfo.ResultBean data, int position) {

    }
}
