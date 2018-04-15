package com.sergon146.mobilization18.ui.fragments.photolist.adapter;

import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.view.SimpleDraweeView;
import com.sergon146.mobilization18.R;
import com.sergon146.mobilization18.core.api.entities.Picture;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Sergon146 (sergon146@gmail.com).
 * @since 15.04.2018
 */

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.ViewHolder> {
    private List<Picture> pictures = new ArrayList<>();

    public PhotoAdapter() {
    }

    public PhotoAdapter(List<Picture> pictures) {
        this.pictures = pictures;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_picture, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(pictures.get(position));
    }

    public void setItems(List<Picture> items) {
        pictures.clear();
        pictures.addAll(items);
        notifyDataSetChanged();
    }

    public void addItems(List<Picture> items) {
        pictures.addAll(items);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return pictures.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.image)
        SimpleDraweeView image;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(Picture picture) {
            image.setLayoutParams(new ConstraintLayout.LayoutParams(
                    picture.getWebformatWidth(), picture.getWebformatHeight()));

            String url = picture.getWebformatURL();
            if (url == null || url.isEmpty()) {
                image.setBackground(null);
            } else {
                image.setImageURI(url);
            }
        }
    }
}
