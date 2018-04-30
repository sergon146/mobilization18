package com.sergon146.mobilization18.ui.fragments.picture.picturedetail.adapter;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.drawable.ProgressBarDrawable;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.core.ImagePipeline;
import com.sergon146.business.model.picture.Picture;
import com.sergon146.mobilization18.R;
import com.sergon146.mobilization18.ui.view.components.RecycledPageAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Sergon146 (sergon146@gmail.com).
 * @since 19.04.2018
 */

public class PicturePageAdapter extends RecycledPageAdapter<PicturePageAdapter.ViewHolder> {
    List<Picture> pictures = new ArrayList<>();

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.page_item_picture, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        viewHolder.bind(pictures.get(position));
    }

    public void setPictures(List<Picture> pictures) {
        this.pictures.clear();
        this.pictures.addAll(pictures);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return pictures == null ? 0 : pictures.size();
    }

    public void addPictures(List<Picture> pictures) {
        this.pictures.addAll(pictures);
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecycledPageAdapter.ViewHolder {
        @BindView(R.id.image)
        SimpleDraweeView image;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(Picture picture) {
            String uri = picture.getLargeImageURL();
            if (uri != null && !uri.isEmpty()) {
                image.setImageURI(uri);
                image.getHierarchy().setProgressBarImage(new ProgressBarDrawable());
                image.setController(
                    Fresco.newDraweeControllerBuilder()
                        .setTapToRetryEnabled(true)
                        .setUri(uri)
                        .build());
            }
        }
    }
}
