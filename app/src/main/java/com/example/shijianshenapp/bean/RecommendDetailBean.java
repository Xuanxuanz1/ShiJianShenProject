package com.example.shijianshenapp.bean;

import org.litepal.crud.LitePalSupport;

public class RecommendDetailBean extends LitePalSupport {
    private String title;
    private String message;

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
}
