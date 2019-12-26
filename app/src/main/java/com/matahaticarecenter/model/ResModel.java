package com.matahaticarecenter.model;

public class ResModel {
    private String id;
    private String name;
    private String img;
    private String description;
    private String type;

    public ResModel() {
    }

    public ResModel(String id, String name, String img) {
        this.id = id;
        this.name = name;
        this.img = img;
    }

    public ResModel(String id, String name, String img, String description, String type) {
        this.id = id;
        this.name = name;
        this.img = img;
        this.description = description;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
