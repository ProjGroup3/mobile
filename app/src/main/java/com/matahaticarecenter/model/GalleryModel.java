package com.matahaticarecenter.model;

public class GalleryModel {

    private String id;
    private String url;
    private String caption;

    public GalleryModel() {
    }

    public GalleryModel(String id, String url, String caption) {
        this.id = id;
        this.url = url;
        this.caption = caption;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }
}
