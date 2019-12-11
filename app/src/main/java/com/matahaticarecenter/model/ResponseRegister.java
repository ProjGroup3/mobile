package com.matahaticarecenter.model;

import java.util.List;

public class ResponseRegister {
    private Integer status_code;
    private Boolean result;

    public ResponseRegister() {
    }

    public ResponseRegister(Integer status_code, Boolean result) {
        this.status_code = status_code;
        this.result = result;
    }

    public Integer getStatus_code() {
        return status_code;
    }

    public void setStatus_code(Integer status_code) {
        this.status_code = status_code;
    }

    public Boolean getResult() {
        return result;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }
}
