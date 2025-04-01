package com.ayma.base.widget;

import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class SpacesItemDecoration extends RecyclerView.ItemDecoration {
    private int space;

    public SpacesItemDecoration(int space) {
        this.space = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.left = space;
        outRect.right = space;
        outRect.bottom = space;
        outRect.top = space;
//        if (parent.getLayoutManager() instanceof GridLayoutManager) {
//            GridLayoutManager layoutManager = (GridLayoutManager) parent.getLayoutManager();
//            int spanCount = layoutManager.getSpanCount();
//            if (parent.getChildLayoutPosition(view) < spanCount)
//                outRect.top = space;
//        } else {
//            // Add top margin only for the first item to avoid double space between items
//            if (parent.getChildLayoutPosition(view) == 0)
//                outRect.top = space;
//        }

    }
}