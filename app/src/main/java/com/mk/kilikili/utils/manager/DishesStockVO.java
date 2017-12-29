package com.mk.kilikili.utils.manager;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 传递数据序列化对象.
 */

public class DishesStockVO implements Parcelable {

    public boolean isShowMask;
    public int pageNum;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte(isShowMask ? (byte) 1 : (byte) 0);
        dest.writeInt(this.pageNum);
    }

    public DishesStockVO() {
    }

    protected DishesStockVO(Parcel in) {
        this.isShowMask = in.readByte() != 0;
        this.pageNum = in.readInt();
    }

    public static final Creator<DishesStockVO> CREATOR = new Creator<DishesStockVO>() {
        public DishesStockVO createFromParcel(Parcel source) {
            return new DishesStockVO(source);
        }

        public DishesStockVO[] newArray(int size) {
            return new DishesStockVO[size];
        }
    };

    @Override
    public String toString() {
        return "DishesStockVO{" +
                "isShowMask=" + isShowMask +
                ", pageNum=" + pageNum +
                '}';
    }
}
