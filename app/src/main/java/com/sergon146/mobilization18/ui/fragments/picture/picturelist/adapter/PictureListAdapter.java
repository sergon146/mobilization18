package com.sergon146.mobilization18.ui.fragments.picture.picturelist.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.view.SimpleDraweeView;
import com.sergon146.business.model.base.ResultTitle;
import com.sergon146.business.model.base.Throbber;
import com.sergon146.business.model.picture.Picture;
import com.sergon146.business.model.picture.PicturesList;
import com.sergon146.mobilization18.R;
import com.sergon146.mobilization18.ui.components.ResultTitleViewHolder;
import com.sergon146.mobilization18.ui.components.ThrobberViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Sergon146 (sergon146@gmail.com).
 * @since 15.04.2018
 */

public class PictureListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final int TITLE_VIEW_TYPE = 0;
    public static final int THROBBER_VIEW_TYPE = 2;
    public static final int PICTURE_VIEW_TYPE = 1;

    private PictureClickListener listener;
    private List<Object> listElements = new ArrayList<>();
    private List<Picture> pictures = new ArrayList<>();
    private boolean isThrobberActive = false;

    public PictureListAdapter(PictureClickListener listener) {
        this.listener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;

        switch (viewType) {
            case TITLE_VIEW_TYPE:
                view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.result_title, parent, false);
                return new ResultTitleViewHolder(view);
            case PICTURE_VIEW_TYPE:
                view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.list_item_picture, parent, false);
                return new PictureViewHolder(view);
            case THROBBER_VIEW_TYPE:
                view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.throbber, parent, false);
                return new ThrobberViewHolder(view);
            default:
                throw new RuntimeException("Unknown view type");
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case PICTURE_VIEW_TYPE:
                ((PictureViewHolder) holder).bind(position, (Picture) listElements.get(position));
                break;
            case TITLE_VIEW_TYPE:
                ((ResultTitleViewHolder) holder).bind((ResultTitle) listElements.get(0));
                break;
            case THROBBER_VIEW_TYPE:
                break;
            default:
                throw new RuntimeException("Unknown view type");
        }
    }

    @Override
    public int getItemViewType(int position) {
        Object item = listElements.get(position);
        if (item instanceof Picture) {
            return PICTURE_VIEW_TYPE;
        } else if (item instanceof Throbber) {
            return THROBBER_VIEW_TYPE;
        } else if (item instanceof ResultTitle) {
            return TITLE_VIEW_TYPE;
        } else {
            throw new RuntimeException("Unknown screen");
        }
    }

    public void showThrobber() {
        if (isThrobberActive) {
            return;
        }

        isThrobberActive = true;

        if (listElements.isEmpty()) {
            listElements.add(new Throbber());
            notifyItemInserted(0);
        } else {
            listElements.add(new Throbber());
            notifyItemInserted(listElements.size() - 1);
        }
    }

    public void hideThrobber() {
        if (listElements.isEmpty() || !isThrobberActive) {
            return;
        }
        isThrobberActive = false;

        int throbberPos = listElements.size() - 1;
        listElements.remove(throbberPos);
        notifyItemRemoved(throbberPos);
    }

    public void setItems(List<Picture> items, ResultTitle resultTitle) {
        listElements.clear();
        listElements.add(resultTitle);
        listElements.addAll(items);

        pictures.clear();
        pictures.addAll(items);
        notifyDataSetChanged();
    }

    public void addItems(List<Picture> items) {
        listElements.addAll(items);
        pictures.addAll(items);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return listElements.size();
    }

    public interface PictureClickListener {
        void onClick(PicturesList picturesDto);
    }

    class PictureViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.image)
        SimpleDraweeView image;

        PictureViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(int pos, Picture picture) {
            itemView.setOnClickListener(v -> listener.onClick(new PicturesList(pictures, pos - 1)));

            String url = picture.getWebformatURL();
            if (url == null || url.isEmpty()) {
                image.setBackground(null);
            } else {
                image.setImageURI(url);
            }
        }

    }
}
