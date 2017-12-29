package com.mk.kilikili.image;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.bumptech.glide.BitmapRequestBuilder;
import com.bumptech.glide.DrawableRequestBuilder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.cache.ExternalCacheDiskCacheFactory;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.module.GlideModule;
import com.mk.kilikili.R;
import com.mk.kilikili.utils.manager.C;

/**
 * Created by Mr_468 on 2017/4/4.
 */

public class GlideUtils implements GlideModule {

    @Override
    public void applyOptions(Context context, GlideBuilder builder) {

        int cacheSize = 1024 * 1024 * 1024;

        builder.setDiskCache(new ExternalCacheDiskCacheFactory(context, cacheSize));
    }

    @Override
    public void registerComponents(Context context, Glide glide) {

    }

    private static DrawableRequestBuilder<String> getBuilder(Context context, String url) {
        return Glide.with(context)
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .placeholder(R.drawable.bili_default_image_tv)
                .centerCrop()
                .error(R.drawable.bili_default_image_tv)
                .dontAnimate();
    }

    public static void loadImage(Context context, String url, ImageView view) {

        getBuilder(context, url)
                .into(view);
    }

    public static void loadTransformImage(Context context, String url, ImageView view, BitmapTransformation... transform) {
        if (transform == null)
            loadImage(context, url, view);
        else {
            getBuilder(context, url)
                    .transform(transform)
                    .into(view);
        }
    }

    public static void loadCornerImage(Context context, String url, ImageView view, float[] radius) {

        loadTransformImage(context, url, view, new CenterCrop(context), new GlideCornerTransform(context, radius));
    }

    public static void loadCardImage(Context context, String url, ImageView view) {

        loadCornerImage(context, url, view, C.CARD_CORNERS);
    }
}
