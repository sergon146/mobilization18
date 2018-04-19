package com.sergon146.mobilization18.ui.fragments.picture.picturedetail.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.drawable.ProgressBarDrawable;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.sergon146.mobilization18.R;
import com.sergon146.mobilization18.core.api.entities.Picture;
import com.sergon146.mobilization18.ui.view.components.RecycledPageAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Sergon146 (sergon146@gmail.com).
 * @since 19.04.2018
 */

public class PicturePageAdapter extends RecycledPageAdapter<PicturePageAdapter.ViewHolder> {
    List<Picture> pictures;

    public PicturePageAdapter(List<Picture> pictures) {
        this.pictures = pictures;
    }

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

    @Override
    public int getCount() {
        return pictures == null ? 0 : pictures.size();
    }

    public static class ViewHolder extends RecycledPageAdapter.ViewHolder {
        @BindView(R.id.image)
        SimpleDraweeView image;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(Picture picture) {

            if (picture.getLargeImageURL() != null || !picture.getLargeImageURL().isEmpty()) {
                image.setImageURI(picture.getLargeImageURL());
                image.getHierarchy().setProgressBarImage(new ProgressBarDrawable());
            }
        }
    }
}
