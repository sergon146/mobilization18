package com.sergon146.mobilization18.ui.view;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * @author Sergon146 (sergon146@gmail.com).
 * @since 15.04.2018
 */
public class CatalogDecorations extends RecyclerView.ItemDecoration {
    private int spanCount;
    private int right;

    public CatalogDecorations(int spanCount, int right) {
        this.spanCount = spanCount;
        this.right = right;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
                               RecyclerView.State state) {
        int position = parent.getChildAdapterPosition(view);
        int column = position % spanCount;
        int rightOffset = right;
        int offset = rightOffset / 2;

        if (column == spanCount - 1) {
            outRect.set(offset, 0, 0, 0);
        } else if (column == 0) {
            outRect.set(0, 0, offset, 0);
        } else {
            outRect.set(offset, 0, offset, 0);
        }
    }
}