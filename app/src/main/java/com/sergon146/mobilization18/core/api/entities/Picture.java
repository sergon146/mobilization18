package com.sergon146.mobilization18.core.api.entities;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Picture implements Parcelable {
    public static final Creator<Picture> CREATOR = new Creator<Picture>() {
        @Override
        public Picture createFromParcel(Parcel in) {
            return new Picture(in);
        }

        @Override
        public Picture[] newArray(int size) {
            return new Picture[size];
        }
    };

    @SerializedName("largeImageURL")
    @Expose
    private String largeImageURL;
    @SerializedName("webformatHeight")
    @Expose
    private int webformatHeight;
    @SerializedName("webformatWidth")
    @Expose
    private int webformatWidth;
    @SerializedName("imageWidth")
    @Expose
    private int imageWidth;
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("imageHeight")
    @Expose
    private int imageHeight;
    @SerializedName("webformatURL")
    @Expose
    private String webformatURL;
    @SerializedName("previewHeight")
    @Expose
    private int previewHeight;
    @SerializedName("imageSize")
    @Expose
    private int imageSize;
    @SerializedName("previewWidth")
    @Expose
    private int previewWidth;
    @SerializedName("userImageURL")
    @Expose
    private String userImageURL;
    @SerializedName("previewURL")
    @Expose
    private String previewURL;

    //ignore fields
    //@SerializedName("likes")
    //private int likes;
    //@SerializedName("user_id")
    //private int userId;
    //@SerializedName("views")
    //private int views;
    //@SerializedName("comments")
    //private int comments;
    //@SerializedName("pageURL")
    //private String pageURL;
    //@SerializedName("type")
    //private String type;
    //@SerializedName("tags")
    //private String tags;
    //@SerializedName("downloads")
    //private int downloads;
    //@SerializedName("user")
    //private String user;
    //@SerializedName("favorites")
    //private int favorites;

    protected Picture(Parcel in) {
        largeImageURL = in.readString();
        webformatHeight = in.readInt();
        webformatWidth = in.readInt();
        imageWidth = in.readInt();
        id = in.readInt();
        imageHeight = in.readInt();
        webformatURL = in.readString();
        previewHeight = in.readInt();
        imageSize = in.readInt();
        previewWidth = in.readInt();
        userImageURL = in.readString();
        previewURL = in.readString();
    }

    public String getLargeImageURL() {
        return largeImageURL;
    }

    public void setLargeImageURL(String largeImageURL) {
        this.largeImageURL = largeImageURL;
    }

    public int getWebformatHeight() {
        return webformatHeight;
    }

    public void setWebformatHeight(int webformatHeight) {
        this.webformatHeight = webformatHeight;
    }

    public int getWebformatWidth() {
        return webformatWidth;
    }

    public void setWebformatWidth(int webformatWidth) {
        this.webformatWidth = webformatWidth;
    }

    public int getImageWidth() {
        return imageWidth;
    }

    public void setImageWidth(int imageWidth) {
        this.imageWidth = imageWidth;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getImageHeight() {
        return imageHeight;
    }

    public void setImageHeight(int imageHeight) {
        this.imageHeight = imageHeight;
    }

    public String getWebformatURL() {
        return webformatURL;
    }

    public void setWebformatURL(String webformatURL) {
        this.webformatURL = webformatURL;
    }

    public int getPreviewHeight() {
        return previewHeight;
    }

    public void setPreviewHeight(int previewHeight) {
        this.previewHeight = previewHeight;
    }

    public int getImageSize() {
        return imageSize;
    }

    public void setImageSize(int imageSize) {
        this.imageSize = imageSize;
    }

    public int getPreviewWidth() {
        return previewWidth;
    }

    public void setPreviewWidth(int previewWidth) {
        this.previewWidth = previewWidth;
    }

    public String getUserImageURL() {
        return userImageURL;
    }

    public void setUserImageURL(String userImageURL) {
        this.userImageURL = userImageURL;
    }

    public String getPreviewURL() {
        return previewURL;
    }

    public void setPreviewURL(String previewURL) {
        this.previewURL = previewURL;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(largeImageURL);
        parcel.writeInt(webformatHeight);
        parcel.writeInt(webformatWidth);
        parcel.writeInt(imageWidth);
        parcel.writeInt(id);
        parcel.writeInt(imageHeight);
        parcel.writeString(webformatURL);
        parcel.writeInt(previewHeight);
        parcel.writeInt(imageSize);
        parcel.writeInt(previewWidth);
        parcel.writeString(userImageURL);
        parcel.writeString(previewURL);
    }
}
