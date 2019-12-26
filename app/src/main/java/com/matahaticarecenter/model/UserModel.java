package com.matahaticarecenter.model;

import java.util.Date;

public class UserModel {

    private String id;
    private String username;
    private String password;
    private String level;
    private String email;
    private String fullname;
    private String phone;
    private String avatar;
    private Boolean is_verified;
    private String join_date;
    private String created_at;

    public UserModel() {
    }

    public UserModel(String id, String username, String password, String level, String email, String fullname, String phone, String avatar, Boolean is_verified, String join_date, String created_at) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.level = level;
        this.email = email;
        this.fullname = fullname;
        this.phone = phone;
        this.avatar = avatar;
        this.is_verified = is_verified;
        this.join_date = join_date;
        this.created_at = created_at;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Boolean getIs_verified() {
        return is_verified;
    }

    public void setIs_verified(Boolean is_verified) {
        this.is_verified = is_verified;
    }

    public String getJoin_date() {
        return join_date;
    }

    public void setJoin_date(String join_date) {
        this.join_date = join_date;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }
}
