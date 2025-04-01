package com.example.shijianshenapp.bean;

import org.litepal.crud.LitePalSupport;

public class TrainBean extends LitePalSupport {
    private int headImg;
    private String title;
    private String message;
    private String time;
    private int likeNumber;
    private String watchNumber;
    private boolean isLike = false;

    public boolean isLike() {
        return isLike;
    }

    public void setLike(boolean like) {
        isLike = like;
    }

    public int getHeadImg() {
        return headImg;
    }

    public void setHeadImg(int headImg) {
        this.headImg = headImg;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getLikeNumber() {
        return likeNumber;
    }

    public void setLikeNumber(int likeNumber) {
        this.likeNumber = likeNumber;
    }

    public String getWatchNumber() {
        return watchNumber;
    }

    public void setWatchNumber(String watchNumber) {
        this.watchNumber = watchNumber;
    }
}
