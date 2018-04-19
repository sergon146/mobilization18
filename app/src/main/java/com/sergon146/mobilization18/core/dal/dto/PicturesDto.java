package com.sergon146.mobilization18.core.dal.dto;

import android.os.Parcel;
import android.os.Parcelable;

import com.sergon146.mobilization18.core.api.entities.Picture;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Sergon146 (sergon146@gmail.com).
 * @since 18.04.2018
 */

public class PicturesDto implements Parcelable {
    public static final Creator<PicturesDto> CREATOR = new Creator<PicturesDto>() {
        @Override
        public PicturesDto createFromParcel(Parcel in) {
            return new PicturesDto(in);
        }

        @Override
        public PicturesDto[] newArray(int size) {
            return new PicturesDto[size];
        }
    };
    private List<Picture> pictures;
    private int position;

    public PicturesDto(List<Picture> pictures, int position) {
        this.pictures = pictures;
        this.position = position;
    }

    protected PicturesDto(Parcel in) {
        position = in.readInt();
        pictures = new ArrayList<>();
        in.readList(pictures, Picture.class.getClassLoader());
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(position);
        parcel.writeList(pictures);
    }
}
