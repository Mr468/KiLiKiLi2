package com.mk.kilikili.image;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;

/**
 * Created by Mr_468 on 2017/4/5.
 */

public class GlideCornerTransform extends BitmapTransformation {

    private float[] radiusArr = new float[8];

    private final static float DEFAULT_RADIUS = 10;

    public GlideCornerTransform(Context context) {
        super(context);
        for (int i = 0; i < radiusArr.length; i++)
            radiusArr[i] = DEFAULT_RADIUS;
    }

    /**
     * @param radius 圆角像素
     */
    public GlideCornerTransform(Context context, float radius) {
        super(context);
        if (radius < 1) {
            for (int i = 0; i < radiusArr.length; i++)
                radiusArr[i] = DEFAULT_RADIUS;
        } else {
            for (int i = 0; i < radiusArr.length; i++)
                radiusArr[i] = radius;
        }

    }

    public GlideCornerTransform(Context context, float[] radiusArr) {
        super(context);
        this.radiusArr = radiusArr;
    }

    @Override
    protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
        return cornersCrop(pool, toTransform);
    }

    private Bitmap cornersCrop(BitmapPool pool, Bitmap source) {
        if (source == null) return null;

        Bitmap result = pool.get(source.getWidth(), source.getHeight(), Bitmap.Config.ARGB_8888);
        if (result == null) {
            result = Bitmap.createBitmap(source.getWidth(), source.getHeight(), Bitmap.Config.ARGB_8888);
        }

        Canvas canvas = new Canvas(result);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setShader(new BitmapShader(source, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP));
        RectF rectF = new RectF(0f, 0f, source.getWidth(), source.getHeight());
        Path path = new Path();
        path.addRoundRect(rectF, radiusArr, Path.Direction.CW);
        canvas.drawPath(path, paint);
        return result;
    }

    @Override
    public String getId() {
        return getClass().getName();
    }
}
