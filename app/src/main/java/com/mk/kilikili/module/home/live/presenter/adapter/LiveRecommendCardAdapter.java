package com.mk.kilikili.module.home.live.presenter.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableStringBuilder;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mk.kilikili.R;
import com.mk.kilikili.widget.recyclerview.BaseRecyclerViewAdapter;
import com.mk.kilikili.widget.recyclerview.BaseViewHolder;
import com.mk.kilikili.image.GlideUtils;
import com.mk.kilikili.module.home.live.entity.LiveAppIndexInfo;
import com.mk.kilikili.utils.manager.StringUtil;
import com.mk.mvpbase.presenter.Presenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.http.PATCH;


/**
 * Created by Mr_468 on 2017/4/5.
 */

public class LiveRecommendCardAdapter extends BaseRecyclerViewAdapter<Object> {

    public LiveRecommendCardAdapter(Context context, Presenter presenter) {
        super(context, presenter);
    }

    @Override
    protected void onSetData(SparseIntArray layouts, SparseArray<Class<? extends BaseViewHolder>> holders) {
        layouts.put(VIEW_TYPE_DEFAULT, R.layout.item_live_cardview);
        holders.put(VIEW_TYPE_DEFAULT, LiveRecommendCardViewViewHolder.class);
    }

    static class LiveRecommendCardViewViewHolder extends BaseViewHolder<Object> {

        @BindView(R.id.iv_cardview_media)
        ImageView iv_cardview_media;

        @BindView(R.id.tv_cardview_title)
        TextView tv_cardview_title;

        @BindView(R.id.tv_cardview_username)
        TextView tv_cardview_username;

        @BindView(R.id.tv_cardview_online)
        TextView tv_cardview_online;


        public LiveRecommendCardViewViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void convert(Object data, int position) {
            if (data instanceof LiveAppIndexInfo.DataBean.RecommendDataBean.BannerDataBean) {
                LiveAppIndexInfo.DataBean.RecommendDataBean.BannerDataBean bean = (LiveAppIndexInfo.DataBean.RecommendDataBean.BannerDataBean) data;

                GlideUtils.loadCardImage(mContext, bean.getCover().getSrc(), iv_cardview_media);

                SpannableStringBuilder title = StringUtil.changeSpan(mContext
                        , "#" + bean.getArea().trim() + "# " + bean.getTitle().trim(), 0, bean.getArea().trim().length() + 2);
                tv_cardview_title.setText(title);
                tv_cardview_username.setText(bean.getOwner().getName());
                tv_cardview_online.setText(String.valueOf(bean.getOnline()));
            } else {
                LiveAppIndexInfo.DataBean.RecommendDataBean.LivesBean bean = (LiveAppIndexInfo.DataBean.RecommendDataBean.LivesBean) data;

                GlideUtils.loadCardImage(mContext, bean.getCover().getSrc(), iv_cardview_media);

                SpannableStringBuilder title = StringUtil.changeSpan(mContext
                        , "#" + bean.getArea().trim() + "# " + bean.getTitle().trim(), 0, bean.getArea().trim().length() + 2);
                tv_cardview_title.setText(title);
                tv_cardview_username.setText(bean.getOwner().getName());
                tv_cardview_online.setText(String.valueOf(bean.getOnline()));
            }


//
//            final int singlePosition = Math.round((getItemCount(data) - 1) / (data.getBanner_data().size() + 1));
//
//            if ((position - singlePosition) % (singlePosition + 1) == 0) {
//                LiveAppIndexInfo.DataBean.RecommendDataBean.BannerDataBean bean = data.getBanner_data()
//                        .get((position - singlePosition) / (singlePosition + 1));
//
//                GlideUtils.loadCardImage(mContext, bean.getCover().getSrc(), iv_cardview_media);
//
//                SpannableStringBuilder title = StringUtil.changeSpan(mContext
//                        , "#" + bean.getArea().trim() + "# " + bean.getTitle().trim(), 0, bean.getArea().trim().length() + 2);
//                tv_cardview_title.setText(title);
//                tv_cardview_username.setText(bean.getOwner().getName());
//                tv_cardview_online.setText(String.valueOf(bean.getOnline()));
//            } else {
//                LiveAppIndexInfo.DataBean.RecommendDataBean.LivesBean bean = null;
//                if (position < singlePosition) {
//                    bean = data.getLives().get(position);
//                } else {
//                    bean = data.getLives()
//                            .get(position - ((position - singlePosition) / (singlePosition + 1) + 1));
//                }
//
//                GlideUtils.loadCardImage(mContext, bean.getCover().getSrc(), iv_cardview_media);
//
//                SpannableStringBuilder title = StringUtil.changeSpan(mContext
//                        , "#" + bean.getArea().trim() + "# " + bean.getTitle().trim(), 0, bean.getArea().trim().length() + 2);
//                tv_cardview_title.setText(title);
//                tv_cardview_username.setText(bean.getOwner().getName());
//                tv_cardview_online.setText(String.valueOf(bean.getOnline()));
//            }
        }

        private int getItemCount(LiveAppIndexInfo.DataBean.RecommendDataBean mData) {
            int size = 0;
            if (mData.getBanner_data() != null) {
                size += mData.getBanner_data().size();
            }
            if (mData.getLives() != null) {
                size += mData.getLives().size();
            }
            return size;
        }
    }
}
