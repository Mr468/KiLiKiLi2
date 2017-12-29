package com.mk.kilikili.module.home.recommend.presenter.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.internal.bind.DateTypeAdapter;
import com.mk.kilikili.R;
import com.mk.kilikili.image.GlideUtils;
import com.mk.kilikili.module.home.recommend.entity.RecommendInfo;
import com.mk.kilikili.widget.recyclerview.BaseRecyclerViewAdapter;
import com.mk.kilikili.widget.recyclerview.BaseViewHolder;
import com.mk.mvpbase.presenter.Presenter;

import butterknife.BindView;

/**
 * Created by Mr_468 on 2017/4/21.
 */

public class RecommendRegionAdapter extends BaseRecyclerViewAdapter<RecommendInfo.ResultBean.BodyBean> {
    public RecommendRegionAdapter(Context context, Presenter presenter, int layoutId, Class<? extends BaseViewHolder> holder) {
        super(context, presenter, layoutId, holder);
    }

    class RecommendRegionViewHolder extends BaseViewHolder<RecommendInfo.ResultBean.BodyBean> {

        @BindView(R.id.iv_cardview_media)
        ImageView iv_cardview_media;

        @BindView(R.id.tv_cardview_title)
        TextView tv_cardview_title;

        @BindView(R.id.tv_cardview_username)
        TextView tv_cardview_username;

        @BindView(R.id.tv_cardview_online)
        TextView tv_cardview_online;

        public RecommendRegionViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void convert(RecommendInfo.ResultBean.BodyBean data, int position) {
            GlideUtils.loadCardImage(mContext, data.getCover(), iv_cardview_media);
            tv_cardview_title.setText(data.getTitle());
            if (data.getUp() != null)
                tv_cardview_username.setText(data.getUp());
            if(data.getDesc1()!=null)
                tv_cardview_username.setText(data.getDesc1());
            if(data.getPlay()!=null)
                tv_cardview_username.setText(data.getPlay());
//            if(data.getOnline()!=0)
//                tv_cardview_online.setText(String.valueOf(data.getOnline()));
        }
    }
}
