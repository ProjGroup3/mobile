package com.matahaticarecenter.model;

public class ProgramModel {
    private String img;
    private String title;
    private String desctiption;
    private String content;
    private String time;

    public ProgramModel() {
    }

    public ProgramModel(String img, String title, String desctiption) {
        this.img = img;
        this.title = title;
        this.desctiption = desctiption;
    }


    public String getImg() {
        return img;
    }

    public void setImg(String img) {
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

}