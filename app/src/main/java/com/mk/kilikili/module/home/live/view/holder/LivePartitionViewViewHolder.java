package com.mk.kilikili.module.home.live.view.holder;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mk.kilikili.R;
import com.mk.kilikili.image.GlideUtils;
import com.mk.kilikili.module.home.live.presenter.adapter.LivePartitionCardAdapter;
import com.mk.kilikili.utils.DisplayUtil;
import com.mk.kilikili.utils.manager.StringUtil;
import com.mk.kilikili.widget.recyclerview.BaseViewHolder;
import com.mk.kilikili.module.home.live.entity.LiveAppIndexInfo;
import com.mk.kilikili.widget.recyclerview.decoration.CardItemDecoration;

import java.util.List;

import butterknife.BindView;

/**
 * Created by Mr_468 on 2017/4/5.
 */

public class LivePartitionViewViewHolder extends BaseViewHolder<LiveAppIndexInfo.DataBean.PartitionsBean> {
    @BindView(R.id.iv_partition_icon)
    ImageView iv_partition_icon;

    @BindView(R.id.tv_partition_name)
    TextView tv_partition_name;

    @BindView(R.id.tv_partition_caption)
    TextView tv_partition_caption;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private LivePartitionCardAdapter adapter;

    public LivePartitionViewViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void convert(LiveAppIndexInfo.DataBean.PartitionsBean bean, int position) {

        GlideUtils.loadImage(mContext, bean.getPartition().getSub_icon().getSrc(), iv_partition_icon);
        tv_partition_name.setText(bean.getPartition().getName());

        String str = "当前" + bean.getPartition().getCount() + "个直播";
        tv_partition_caption.setText(
                StringUtil.changeSpan(mContext, str, 2, str.length() - 3));

        initRecyclerView(bean.getLives());
    }

    private void initRecyclerView(List<LiveAppIndexInfo.DataBean.PartitionsBean.LivesBean> data) {
        if (adapter == null) {
            adapter = new LivePartitionCardAdapter(mContext, null, R.layout.item_live_cardview, LivePartitionCardAdapter.LivePartitionCardViewHolder.class);
            recyclerView.setLayoutManager(new GridLayoutManager(mContext, 2));
            recyclerView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {

                    return event.getAction() == MotionEvent.ACTION_CANCEL;
                }
            });
            recyclerView.addItemDecoration(new CardItemDecoration(DisplayUtil.dp2px(mContext, 8), 2, 0));
            recyclerView.setAdapter(adapter);
        }
        adapter.setData(data);
    }
}
