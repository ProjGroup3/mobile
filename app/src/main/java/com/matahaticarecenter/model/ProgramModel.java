package com.matahaticarecenter.model;

import android.os.Parcel;
import android.os.Parcelable;

public class ProgramModel {
    private Integer img;
    private String title;
    private String desctiption;
    private String content;
    private String time;

    public ProgramModel() {
    }

    public ProgramModel(Integer img, String title, String desctiption, String content, String time) {
        this.img = img;
        this.title = title;
        this.desctiption = desctiption;
        this.content = content;
        this.time = time;
    }

    public Integer getImg() {
        return img;
    }

    public void setImg(Integer img) {
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesctiption() {
        return desctiption;
    }

    public void setDesctiption(String desctiption) {
        this.desctiption = desctiption;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

}