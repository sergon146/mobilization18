package com.sergon146.mobilization18.core.api.entities;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

@Parcel
public class Picture {
    @SerializedName("largeImageURL")
    @Expose
    String largeImageURL;
    @SerializedName("webformatHeight")
    @Expose
    int webformatHeight;
    @SerializedName("webformatWidth")
    @Expose
    int webformatWidth;
    @SerializedName("imageWidth")
    @Expose
    int imageWidth;
    @SerializedName("id")
    @Expose
    int id;
    @SerializedName("imageHeight")
    @Expose
    int imageHeight;
    @SerializedName("webformatURL")
    @Expose
    String webformatURL;
    @SerializedName("previewHeight")
    @Expose
    int previewHeight;
    @SerializedName("imageSize")
    @Expose
    int imageSize;
    @SerializedName("previewWidth")
    @Expose
    int previewWidth;
    @SerializedName("userImageURL")
    @Expose
    String userImageURL;
    @SerializedName("previewURL")
    @Expose
    String previewURL;

    //ignore fields

    //@SerializedName("likes")
    //int likes;
    //@SerializedName("user_id")
    //int userId;
    //@SerializedName("views")
    //int views;
    //@SerializedName("comments")
    //int comments;
    //@SerializedName("pageURL")
    //String pageURL;
    //@SerializedName("type")
    //String type;
    //@SerializedName("tags")
    //String tags;
    //@SerializedName("downloads")
    //int downloads;
    //@SerializedName("user")
    //String user;
    //@SerializedName("favorites")
    //int favorites;

    public Picture() {
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
}
