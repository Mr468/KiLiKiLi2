package com.mk.kilikili.module.home.live.view.holder;

import android.view.View;

import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.facebook.stetho.common.ArrayListAccumulator;
import com.mk.kilikili.R;
import com.mk.kilikili.module.home.live.entity.LiveAppIndexInfo;
import com.mk.kilikili.widget.recyclerview.BaseViewHolder;
import com.mk.kilikili.image.GlideCornerTransform;
import com.mk.kilikili.utils.manager.C;
import com.mk.kilikili.widget.banner.BannerEntity;
import com.mk.kilikili.widget.banner.BannerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.reactivex.Flowable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

import static android.R.attr.data;

/**
 * Created by Mr_468 on 2017/4/5.
 */

public class LiveBannerViewViewHolder extends BaseViewHolder<List<LiveAppIndexInfo.DataBean.BannerBean>> {

    @BindView(R.id.banner)
    BannerView banner;

    public LiveBannerViewViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void convert(List<LiveAppIndexInfo.DataBean.BannerBean> bannerData, int position) {
        if (bannerData != null && bannerData.size() != 0) {
            final List<BannerEntity> banners = new ArrayList<>();
            Flowable.fromIterable(bannerData)
                    .forEach(new Consumer<LiveAppIndexInfo.DataBean.BannerBean>() {
                        @Override
                        public void accept(@NonNull LiveAppIndexInfo.DataBean.BannerBean bannerBean) throws Exception {
                            banners.add(new BannerEntity(bannerBean.getLink(), bannerBean.getTitle(), bannerBean.getImg()));
                        }
                    });
            banner.setTransformation(new CenterCrop(banner.getContext()),
                    new GlideCornerTransform(banner.getContext(), C.CARD_CORNERS));
            banner.delayTime(C.BANNER_DELAY).build(banners);
        }
    }
}
