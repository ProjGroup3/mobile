package com.matahaticarecenter.model;

public class MessageModel {

    private String id;
    private String user_id;
    private String title;
    private String message;

    public MessageModel() {
    }

    public MessageModel(String id, String user_id, String title, String message) {
        this.id = id;
        this.user_id = user_id;
        this.title = title;
        this.message = message;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

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
