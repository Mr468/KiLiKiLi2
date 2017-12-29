package com.mk.kilikili.module.home.live.view.holder;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mk.kilikili.R;
import com.mk.kilikili.utils.DisplayUtil;
import com.mk.kilikili.widget.recyclerview.BaseViewHolder;
import com.mk.kilikili.image.GlideUtils;
import com.mk.kilikili.module.home.live.entity.LiveAppIndexInfo;
import com.mk.kilikili.module.home.live.presenter.adapter.LiveRecommendCardAdapter;
import com.mk.kilikili.utils.manager.StringUtil;
import com.mk.kilikili.widget.recyclerview.decoration.CardItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Mr_468 on 2017/4/5.
 */

public class LiveRecommendViewViewHolder extends BaseViewHolder<LiveAppIndexInfo.DataBean.RecommendDataBean> {

    @BindView(R.id.iv_partition_icon)
    ImageView partitionIcon;

    @BindView(R.id.tv_partition_name)
    TextView partitionName;

    @BindView(R.id.tv_partition_caption)
    TextView partitionCaption;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private LiveRecommendCardAdapter adapter;

    private GridLayoutManager manager;

    private int singlePosition;

    private int size;

    private CardItemDecoration decoration;

    public LiveRecommendViewViewHolder(View itemView) {
        super(itemView);
    }


    @Override
    public void convert(LiveAppIndexInfo.DataBean.RecommendDataBean data, int position) {
        GlideUtils.loadImage(mContext, data.getPartition().getSub_icon().getSrc(), partitionIcon);
        partitionName.setText(data.getPartition().getName());

        String str = "当前" + data.getPartition().getCount() + "个直播";
        partitionCaption.setText(
                StringUtil.changeSpan(mContext, str, 2, str.length() - 3));

        initRecyclerView(data);
    }

    private void initRecyclerView(LiveAppIndexInfo.DataBean.RecommendDataBean data) {

        if (adapter == null) {
            adapter = new LiveRecommendCardAdapter(mContext, null);
            recyclerView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    return event.getAction() == MotionEvent.ACTION_CANCEL;
                }
            });
            recyclerView.setAdapter(adapter);
        }
        initManager(data);
        recyclerView.setLayoutManager(manager);
        if (decoration == null) {
            decoration = new CardItemDecoration(DisplayUtil.dp2px(mContext,
                    8), 2, singlePosition);
            recyclerView.addItemDecoration(decoration);
        }
        initData(data);
    }

    private void initManager(LiveAppIndexInfo.DataBean.RecommendDataBean data) {
        if (manager == null)
            manager = new GridLayoutManager(mContext, 2);
        size = 0;
        if (data.getBanner_data() != null) {
            size += 1;
        }
        if (data.getLives() != null) {
            size += data.getLives().size();
        }
        singlePosition = Math.round((size - 1) / (data.getBanner_data().size() + 1));
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (position == singlePosition * (position / singlePosition) + (position / singlePosition - 1))
                    return 2;
                return 1;
            }
        });
    }

    private void initData(LiveAppIndexInfo.DataBean.RecommendDataBean data) {
        List<Object> datas = new ArrayList<>();
        if (data.getLives() != null)
            datas.addAll(data.getLives());
        if (data.getBanner_data() != null && data.getBanner_data().size() > 0) {
            for (int i = 0; i < data.getBanner_data().size(); i++) {
                datas.add(singlePosition + (singlePosition + 1) * i, data.getBanner_data().get(i));
            }
        }
        adapter.setData(datas);
    }
}
