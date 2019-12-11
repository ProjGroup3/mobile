package com.matahaticarecenter.model;

import java.util.List;

public class ResponseGallery {
    private Integer status_code;
    private List<GalleryModel> result;

    public ResponseGallery() {
    }

    public ResponseGallery(Integer status_code, List<GalleryModel> result) {
        this.status_code = status_code;
        this.result = result;
    }

    public Integer getStatus_code() {
        return status_code;
    }

    public void setStatus_code(Integer status_code) {
        this.status_code = status_code;
    }

    public List<GalleryModel> getResult() {
        return result;
    }

    public void setResult(List<GalleryModel> result) {
        this.result = result;
    }
}
