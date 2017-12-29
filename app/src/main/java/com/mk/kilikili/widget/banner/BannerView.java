package com.mk.kilikili.widget.banner;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.Px;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.ViewTarget;
import com.mk.kilikili.R;
import com.mk.kilikili.image.GlideCornerTransform;
import com.mk.kilikili.image.GlideUtils;
import com.mk.kilikili.module.common.view.BrowserActivity;
import com.mk.kilikili.utils.CommonUtil;
import com.mk.kilikili.utils.DisplayUtil;
import com.mk.kilikili.utils.ToastUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * 自定义Banner无限轮播控件
 */
public class BannerView extends RelativeLayout
        implements BannerAdapter.ViewPagerOnItemClickListener {

    @BindView(R.id.layout_banner_viewpager)
    ViewPager viewPager;

    @BindView(R.id.layout_banner_points_group)
    LinearLayout points;

    //  private CompositeSubscription compositeSubscription;
    private CompositeDisposable compositeDisposable;

    //默认轮播时间，10s
    private int delayTime = 10;

    private List<ImageView> imageViewList;

    private List<BannerEntity> bannerList;

    //选中显示Indicator
    private int selectRes = R.drawable.shape_dots_select;

    //非选中显示Indicator
    private int unSelcetRes = R.drawable.shape_dots_default;

    //当前页的下标
    private int currrentPos;

    private BitmapTransformation[] transformations;


    public BannerView(Context context) {

        this(context, null);
    }


    public BannerView(Context context, AttributeSet attrs) {

        this(context, attrs, 0);
    }


    public BannerView(Context context, AttributeSet attrs, int defStyleAttr) {

        super(context, attrs, defStyleAttr);
        LayoutInflater.from(getContext()).inflate(R.layout.layout_custom_banner, this, true);
        ButterKnife.bind(this);

        imageViewList = new ArrayList<>();
    }


    /**
     * 设置轮播间隔时间
     *
     * @param time 轮播间隔时间，单位秒
     */
    public BannerView delayTime(int time) {

        this.delayTime = time;
        return this;
    }


    /**
     * 设置Points资源 Res
     *
     * @param selectRes   选中状态
     * @param unselcetRes 非选中状态
     */
    public void setPointsRes(int selectRes, int unselcetRes) {

        this.selectRes = selectRes;
        this.unSelcetRes = unselcetRes;
    }

    public void setTransformation(BitmapTransformation... transformations) {
        this.transformations = transformations;
    }


    /**
     * 图片轮播需要传入参数
     */
    public void build(List<BannerEntity> list) {

        if (list.size() == 0) {
            this.setVisibility(GONE);
            return;
        }

        if (!isStopScroll)
            stopScroll();

        bannerList = new ArrayList<>();
        bannerList.addAll(list);
        final int pointSize;
        pointSize = bannerList.size();

        if (pointSize == 1) {
            bannerList.addAll(list);
            bannerList.addAll(list);
        }

        if (pointSize == 2) {
            bannerList.addAll(list);
        }
        //判断是否清空 指示器点
        if (points.getChildCount() != 0) {
            points.removeAllViewsInLayout();
        }

        //初始化与个数相同的指示器点
        for (int i = 0; i < pointSize; i++) {
            View dot = new View(getContext());
            dot.setBackgroundResource(unSelcetRes);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    DisplayUtil.dp2px(getContext(), 5),
                    DisplayUtil.dp2px(getContext(), 5));
            params.leftMargin = 10;
            dot.setLayoutParams(params);
            dot.setEnabled(false);
            points.addView(dot);
        }

        points.getChildAt(0).setBackgroundResource(selectRes);

        for (int i = 0; i < bannerList.size(); i++) {
            ImageView mImageView = new ImageView(getContext());
            mImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

            GlideUtils.loadTransformImage(getContext(), bannerList.get(i).img, mImageView, transformations);

            imageViewList.add(mImageView);
        }

        //监听图片轮播，改变指示器状态
        viewPager.clearOnPageChangeListeners();
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }


            @Override
            public void onPageSelected(int pos) {

                pos = pos % pointSize;
                currrentPos = pos;
                for (int i = 0; i < points.getChildCount(); i++) {
                    points.getChildAt(i).setBackgroundResource(unSelcetRes);
                }
                points.getChildAt(pos).setBackgroundResource(selectRes);
            }


            @Override
            public void onPageScrollStateChanged(int state) {

                switch (state) {
                    case ViewPager.SCROLL_STATE_IDLE:
                        if (isStopScroll && pointSize > 1) {
                            startScroll();
                        }
                        break;
                    case ViewPager.SCROLL_STATE_DRAGGING:
                        if (!isStopScroll)
                            stopScroll();
                        break;
                }
            }
        });

        BannerAdapter bannerAdapter = new BannerAdapter(imageViewList);
        viewPager.setAdapter(bannerAdapter);
        bannerAdapter.notifyDataSetChanged();
        bannerAdapter.setmViewPagerOnItemClickListener(this);

        //图片开始轮播
        if (pointSize > 1)
            startScroll();
    }


    private boolean isStopScroll = true;


    /**
     * 图片开始轮播
     */
    private void startScroll() {

        compositeDisposable = new CompositeDisposable();
        isStopScroll = false;
        Disposable subscribe = Observable.timer(delayTime, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(@NonNull Long aLong) throws Exception {
                        if (isStopScroll) {
                            return;
                        }
                        isStopScroll = true;
                        viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                    }
                });
        compositeDisposable.add(subscribe);
    }


    /**
     * 图片停止轮播
     */
    private void stopScroll() {

        isStopScroll = true;
        compositeDisposable.dispose();

    }

    /**
     * 设置ViewPager的Item点击回调事件
     */
    @Override
    public void onItemClick() {

        ToastUtil.ShortToast("点击了banner。。。");

//        BrowserActivity.launch((Activity) getContext(),
//                bannerList.get(currrentPos).link,
//                bannerList.get(currrentPos).title);
    }
}
