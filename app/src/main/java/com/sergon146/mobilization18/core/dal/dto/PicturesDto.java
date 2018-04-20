package com.sergon146.mobilization18.core.dal.dto;

import com.sergon146.mobilization18.core.api.entities.Picture;

import org.parceler.Parcel;
import org.parceler.ParcelConstructor;

import java.util.List;

/**
 * @author Sergon146 (sergon146@gmail.com).
 * @since 18.04.2018
 */

@Parcel
public class PicturesDto {
    List<Picture> pictures;
    int position;

    @ParcelConstructor
    public PicturesDto(List<Picture> pictures, int position) {
        this.pictures = pictures;
        this.position = position;
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
