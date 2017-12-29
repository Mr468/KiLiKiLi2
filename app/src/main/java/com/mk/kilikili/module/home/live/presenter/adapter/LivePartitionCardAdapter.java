package com.mk.kilikili.module.home.live.presenter.adapter;

import android.content.Context;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mk.kilikili.R;
import com.mk.kilikili.image.GlideUtils;
import com.mk.kilikili.module.home.live.entity.LiveAppIndexInfo;
import com.mk.kilikili.utils.manager.StringUtil;
import com.mk.kilikili.widget.recyclerview.BaseRecyclerViewAdapter;
import com.mk.kilikili.widget.recyclerview.BaseViewHolder;
import com.mk.mvpbase.presenter.Presenter;

import java.util.List;

import butterknife.BindView;

import static android.R.attr.data;

/**
 * Created by Mr_468 on 2017/4/16.
 */

public class LivePartitionCardAdapter extends BaseRecyclerViewAdapter<LiveAppIndexInfo.DataBean.PartitionsBean.LivesBean> {
    public LivePartitionCardAdapter(Context context, Presenter presenter, int layoutId, Class<? extends BaseViewHolder> holder) {
        super(context, presenter, layoutId, holder);
    }

    public class LivePartitionCardViewHolder extends BaseViewHolder<LiveAppIndexInfo.DataBean.PartitionsBean.LivesBean> {

        @BindView(R.id.iv_cardview_media)
        ImageView iv_cardview_media;

        @BindView(R.id.tv_cardview_title)
        TextView tv_cardview_title;

        @BindView(R.id.tv_cardview_username)
        TextView tv_cardview_username;

        @BindView(R.id.tv_cardview_online)
        TextView tv_cardview_online;

        public LivePartitionCardViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void convert(LiveAppIndexInfo.DataBean.PartitionsBean.LivesBean bean, int position) {
            GlideUtils.loadCardImage(mContext, bean.getCover().getSrc(), iv_cardview_media);

            SpannableStringBuilder title = StringUtil.changeSpan(mContext
                    , "#" + bean.getArea().trim() + "# " + bean.getTitle().trim(), 0, bean.getArea().trim().length() + 2);
            tv_cardview_title.setText(title);
            tv_cardview_username.setText(bean.getOwner().getName());
            tv_cardview_online.setText(String.valueOf(bean.getOnline()));
        }
    }
}
