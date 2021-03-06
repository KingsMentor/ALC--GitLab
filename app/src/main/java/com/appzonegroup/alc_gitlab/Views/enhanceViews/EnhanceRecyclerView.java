package com.appzonegroup.alc_gitlab.Views.enhanceViews;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * Created by zone2 on 3/8/17.
 */

public class EnhanceRecyclerView extends RecyclerView {
    public EnhanceRecyclerView(Context context) {
        super(context);
    }

    public EnhanceRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public EnhanceRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    private LinearLayoutManager mLayoutManager;
    private RecyclerView.Adapter mAdapter;

    @Override
    public void setLayoutManager(RecyclerView.LayoutManager layout) {
        super.setLayoutManager(layout);
        mLayoutManager = (LinearLayoutManager) layout;
    }

    @Override
    public void setAdapter(RecyclerView.Adapter adapter) {
        super.setAdapter(adapter);
        mAdapter = adapter;
    }

    @Override
    public void onScrolled(int dx, int dy) {
        super.onScrolled(dx, dy);
        if (mAdapter != null)
            if (mLayoutManager.findLastCompletelyVisibleItemPosition() == mAdapter.getItemCount() - 1) {
                if (scrollCallback != null)
                    scrollCallback.reachedEndOfList();
            }
    }

    private listenToScroll scrollCallback;

    public void listen(listenToScroll scrollCallback) {
        this.scrollCallback = scrollCallback;
    }

    public interface listenToScroll {
        void reachedEndOfList();
    }
}
