package com.sergon146.mobilization18.ui.fragments.photo.photolist.adapter;

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
    private PictureClickListener listener;

    public PhotoAdapter(PictureClickListener listener) {
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_picture, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(position, pictures.get(position));
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


    public interface PictureClickListener {
        void onClick(int pos);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.image)
        SimpleDraweeView image;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(int pos, Picture picture) {
            itemView.setOnClickListener(v -> listener.onClick(pos));

            String url = picture.getWebformatURL();
            if (url == null || url.isEmpty()) {
                image.setBackground(null);
            } else {
                image.setImageURI(url);
            }
        }
    }
}
