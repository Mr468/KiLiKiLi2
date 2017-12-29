package com.mk.kilikili.widget.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import android.view.ViewGroup;

import com.mk.mvpbase.presenter.Presenter;

import java.lang.reflect.Constructor;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Mr_468 on 2017/4/8.
 */

public abstract class BaseRecyclerViewAdapter<T> extends RecyclerView.Adapter {

    protected static final int VIEW_TYPE_DEFAULT = 0;//只有一种布局类型时候默认类型为0
    private static final int VIEW_TYPE_HEADER = -1;//header
    private static final int VIEW_TYPE_FOOTER = -2;//footer
    private static final int VIEW_TYPE_EMPTY = 0x00003333;//empty
    private static final int VIEW_TYPE_LOADING = 0x00004444;//loading

    protected Context mContext;

    protected Presenter mPresenter;

    protected List<T> mData;

    protected SparseIntArray mLayouts;

    protected SparseArray<Class<? extends BaseViewHolder>> mHolders;

    private Unbinder mUnbinder;

    public BaseRecyclerViewAdapter(Context context, Presenter presenter) {
        this.mContext = context;
        this.mPresenter = presenter;
    }

    public BaseRecyclerViewAdapter(Context context, Presenter presenter, int layoutId, Class<? extends BaseViewHolder> holder) {
        this.mContext = context;
        this.mPresenter = presenter;
        getLayouts().put(0, layoutId);
        getHolders().put(0, holder);
    }

    public void setData(List<T> data) {
        this.mData = data;
        onSetData(getLayouts(), getHolders());
        notifyDataSetChanged();
    }

    public void addData() {

    }

    public void deleteData() {

    }

    public void setHeader(int headerLayout) {
        getLayouts().put(VIEW_TYPE_HEADER, headerLayout);
    }

    public void setFooter(int footerLayout) {
        getLayouts().put(VIEW_TYPE_FOOTER, footerLayout);
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (mLayouts.get(viewType) == 0)
            throw new RuntimeException("请设置正确的布局文件");
        if (mHolders.get(viewType) == null)
            throw new RuntimeException("请设置正确的ViewHolder");

        View view = View.inflate(mContext, mLayouts.get(viewType), null);
        BaseViewHolder holder = null;
        try {
            Constructor[] cs = mHolders.get(viewType).getDeclaredConstructors();
            int typeCount = cs[0].getParameterTypes().length;
            if (typeCount == 2)
                holder = (BaseViewHolder) cs[0].newInstance(this, view);
            else
                holder = (BaseViewHolder) cs[0].newInstance(view);
            mUnbinder = ButterKnife.bind(holder, view);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        //noinspection unchecked
        ((BaseViewHolder) holder).convert(mData.get(position), position);
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0 && hasHeader())
            return VIEW_TYPE_HEADER;
        if (position == getItemCount() - 1 && hasFooter())
            return VIEW_TYPE_FOOTER;
        return onGetItemViewType(position - getHeaderCount());
    }

    @Override
    public int getItemCount() {
        int size = 0;
        if (hasHeader()) {
            size += 1;
        }
        if (hasFooter())
            size += 1;
        size += onGetItemCount();
        return size;
    }

    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        mUnbinder.unbind();
        super.onDetachedFromRecyclerView(recyclerView);
    }

    /**
     * 根据位置返回viewType
     */
    protected int onGetItemViewType(int position) {
        return VIEW_TYPE_DEFAULT;
    }

    /**
     * 以键值对形式设置各viewType对应的layout和holder 键是viewType
     *
     * @param layouts 布局
     * @param holders holder
     */
    protected void onSetData(SparseIntArray layouts, SparseArray<Class<? extends BaseViewHolder>> holders) {
    }

    protected int onGetItemCount() {
        if (mData != null)
            return mData.size();
        return 0;
    }

    private boolean hasHeader() {
        return getLayouts().get(VIEW_TYPE_FOOTER) > 0;
    }

    private boolean hasFooter() {
        return getLayouts().get(VIEW_TYPE_FOOTER) > 0;
    }

    private int getHeaderCount() {
        return hasHeader() ? 1 : 0;
    }

    private SparseIntArray getLayouts() {
        if (mLayouts == null)
            mLayouts = new SparseIntArray();
        return mLayouts;
    }

    private SparseArray<Class<? extends BaseViewHolder>> getHolders() {
        if (mHolders == null) {
            mHolders = new SparseArray<>();
        }
        return mHolders;
    }
}
