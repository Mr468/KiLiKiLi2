package com.mk.kilikili.widget.recyclerview.decoration;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Mr_468 on 2017/4/7.
 */

public class CardItemDecoration extends RecyclerView.ItemDecoration {

    private int mSpace;

    private int mColumnCount;

    private int mSinglePos;

    public CardItemDecoration(int space, int columnCount, int singlePos) {
        this.mSpace = space;
        this.mColumnCount = columnCount;
        this.mSinglePos = singlePos;
    }

    public int getmSpace() {
        return mSpace;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        int pos = parent.getChildAdapterPosition(view);
        int count = parent.getAdapter().getItemCount();

        if (pos < count - mColumnCount)
            outRect.bottom = mSpace;
        else
            outRect.bottom = 0;

        if (mColumnCount > 1) {//如果只有一列 只用设置底部分割

            if (mSinglePos != 0) {//如果有一行占全格的情况，需要分全格和非全格情况设置
                if (!(pos == mSinglePos * (pos / mSinglePos) + (pos / mSinglePos - 1))) {//非一格占全行

                    if (pos > mSinglePos) {
                        pos = pos - ((pos - mSinglePos) / (mSinglePos + 1) + 1);
                    }
                    if (pos % mColumnCount == 0) {
                        outRect.right = mSpace / 2;
                    } else if (pos % mColumnCount == mColumnCount - 1) {
                        outRect.left = mSpace / 2;
                    } else {
                        outRect.left = mSpace / 2;
                        outRect.right = mSpace / 2;
                    }
                }
            } else {
                if (pos % mColumnCount == 0) {
                    outRect.right = mSpace / 2;
                } else if (pos % mColumnCount == mColumnCount - 1) {
                    outRect.left = mSpace / 2;
                } else {
                    outRect.left = mSpace / 2;
                    outRect.right = mSpace / 2;
                }
            }
        }
    }

}
