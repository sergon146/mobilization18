package com.sergon146.mobilization18.ui.view;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.ViewGroup;

import com.sergon146.mobilization18.R;
import com.sergon146.mobilization18.utils.Const;

/**
 * @author Sergon146 (sergon146@gmail.com).
 * @since 15.04.2018
 */
public class AutoFitGridLayoutManager extends GridLayoutManager {
    private RecyclerView recyclerView;
    private int spanCount = Const.NONE;

    public AutoFitGridLayoutManager(Context context, RecyclerView recyclerView) {
        super(context, 1);
        this.recyclerView = recyclerView;
    }

    @Override
    public void onMeasure(RecyclerView.Recycler recycler,
                          RecyclerView.State state,
                          int widthSpec,
                          int heightSpec) {
        super.onMeasure(recycler, state, widthSpec, heightSpec);
        if (state.getItemCount() > 0
                && state.isMeasuring()
                && getSpanCount() != spanCount) {
            int spaceWidth = recyclerView
                    .getResources()
                    .getDimensionPixelOffset(R.dimen.list_space);

            setSpanCount(spanCount);

            recyclerView.addItemDecoration(
                    new CatalogDecorations(spanCount, spaceWidth));
        }
    }

    @Override
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return generateLayoutParams();
    }

    @Override
    public RecyclerView.LayoutParams generateLayoutParams(Context c, AttributeSet attrs) {
        return generateLayoutParams();
    }

    @Override
    public RecyclerView.LayoutParams generateLayoutParams(ViewGroup.LayoutParams lp) {
        return generateLayoutParams();
    }

    private RecyclerView.LayoutParams generateLayoutParams() {
        if (spanCount == Const.NONE) {
            Resources resources = recyclerView.getResources();
            int childWidth = resources.getDimensionPixelOffset(R.dimen.list_image_width);
            int availableSpace = getWidth();
            int spaceWidth = resources.getDimensionPixelOffset(R.dimen.list_space);

            spanCount = availableSpace / childWidth;

            if (spanCount * (childWidth + spaceWidth) - spaceWidth > availableSpace) {
                spanCount--;
            }
        }

        return new GridLayoutManager.LayoutParams(
                LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT);
    }
}
