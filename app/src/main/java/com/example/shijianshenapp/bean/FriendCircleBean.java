package com.example.shijianshenapp.bean;

import java.util.List;

public class FriendCircleBean {
    private String userName;
    private int head;
    private String message;
    private int likeNumber;
    private String time;
    private boolean isLike = false;

    public boolean isLike() {
        return isLike;
    }

    public void setLike(boolean like) {
        isLike = like;
    }

    private List<CommentBean> commentBeans;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getHead() {
        return head;
    }

    public void setHead(int head) {
        this.head = head;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getLikeNumber() {
        return likeNumber;
    }

    public void setLikeNumber(int likeNumber) {
        this.likeNumber = likeNumber;
    }

    public List<CommentBean> getCommentBeans() {
        return commentBeans;
    }

    public void setCommentBeans(List<CommentBean> commentBeans) {
        this.commentBeans = commentBeans;
    }
}
