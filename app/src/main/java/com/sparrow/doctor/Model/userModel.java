package com.sparrow.doctor.Model;

public class userModel {
    String user_id;
    String username;
    Boolean success;
    String msg;


    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }


    @Override
    public String toString() {
        return "userModel{" +
                "user_id='" + user_id + '\'' +
                ", username='" + username + '\'' +
                ", success=" + success +
                ", msg='" + msg + '\'' +
                '}';
    }
}
