package com.sergon146.business.model;

import com.sergon146.business.utils.Const;

import org.parceler.Parcel;

import java.util.List;

/**
 * @author Sergon146 (sergon146@gmail.com).
 * @since 18.04.2018
 */

@Parcel
public class PicturesList {
    List<Picture> pictures;
    int position = Const.NONE;
    private int totalHits;

    public PicturesList() {
    }

    public PicturesList(List<Picture> pictures, int position) {
        this.pictures = pictures;
        this.position = position;
    }

    public int getTotalHits() {
        return totalHits;
    }

    public void setTotalHits(int totalHits) {
        this.totalHits = totalHits;
    }

    public List<Picture> getPictures() {
        return pictures;
    }

    public void setPictures(List<Picture> pictures) {
        this.pictures = pictures;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
