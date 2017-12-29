package com.mk.kilikili.widget.recyclerview;

import android.content.Context;
import android.util.SparseArray;
import android.view.ViewGroup;

import com.mk.kilikili.widget.recyclerview.entity.MultiItemEntity;

import java.util.List;

/**
 *
 */

public abstract class BaseMultiTypeAdapter<T extends MultiItemEntity> extends BaseRecyclerAdapter {
    private SparseArray<Integer> layouts;

    public BaseMultiTypeAdapter(Context context, List data) {
        super(context, data);
        //add item layout
        addItemLayout();
        //open multi item type
        openMultiItemType(true);
    }

    /**
     * @param type
     * @param layoutResId
     */
    protected void addItemType(int type, int layoutResId) {
        if (layouts == null) {
            layouts = new SparseArray<>();
        }
        layouts.put(type, layoutResId);
    }

    /**
     * @param viewType
     */
    private int getItemLayoutId(int viewType) {
        return layouts == null ? 0 : layouts.get(viewType);
    }

    @Override
    protected int getMultiItemViewType(int position) {
        return ((MultiItemEntity) mData.get(position)).itemType;
    }

    @Override
    protected BaseVHolder onBaseViewHolder(ViewGroup parent, int viewType) {
        return createBaseViewHolder(parent, getItemLayoutId(viewType));
    }

    @Override
    protected void convert(BaseVHolder helper, Object item) {
        convert(helper, (T) item);
    }

    protected abstract void convert(BaseVHolder helper, T item);

    protected abstract void addItemLayout();

}
