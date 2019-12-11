package com.matahaticarecenter.model;

import java.util.List;

public class ResponseLogin {
    private Integer status_code;
    private List<UserModel> result;

    public ResponseLogin() {
    }

    public ResponseLogin(Integer status_code, List<UserModel> result) {
        this.status_code = status_code;
        this.result = result;
    }

    public Integer getStatus_code() {
        return status_code;
    }

    public void setStatus_code(Integer status_code) {
        this.status_code = status_code;
    }

    public List<UserModel> getResult() {
        return result;
    }

    public void setResult(List<UserModel> result) {
        this.result = result;
    }
}
