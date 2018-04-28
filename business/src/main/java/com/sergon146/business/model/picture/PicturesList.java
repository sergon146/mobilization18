package com.sergon146.business.model.picture;

import com.sergon146.business.utils.Const;

import org.parceler.Parcel;

import java.util.List;

/**
 * @author Sergon146 (sergon146@gmail.com).
 * @since 18.04.2018
 */

@Parcel
public class PicturesList {
    private List<Picture> pictures;
    private int position = Const.NONE;
    private int totalCounts;
    private String keyword;

    public PicturesList() {
    }

    public PicturesList(List<Picture> pictures, int position, int totalCounts, String keyword) {
        this.pictures = pictures;
        this.position = position;
        this.totalCounts = totalCounts;
        this.keyword = keyword;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public int getTotalCounts() {
        return totalCounts;
    }

    public void setTotalCounts(int totalCounts) {
        this.totalCounts = totalCounts;
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
