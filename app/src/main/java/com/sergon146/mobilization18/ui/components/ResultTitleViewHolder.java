package com.sergon146.mobilization18.ui.components;

import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.sergon146.business.model.base.ResultTitle;
import com.sergon146.mobilization18.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Sergon146 (sergon146@gmail.com).
 * @since 28.04.2018
 */

public class ResultTitleViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.search_result)
    TextView title;

    public ResultTitleViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(ResultTitle resultTitle) {
        int resultCount = resultTitle.count;
        Resources res = itemView.getResources();
        title.setText(res.getString(R.string.search_result, resultTitle.keyword,
            res.getQuantityString(R.plurals.hours_count_to_watch, resultCount, resultCount)));
    }
}
