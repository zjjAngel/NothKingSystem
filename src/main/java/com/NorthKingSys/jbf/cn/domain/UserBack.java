package com.NorthKingSys.jbf.cn.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
public class UserBack implements Serializable {
    private static final long serialVersionUID = 1L;
    private String username;

    private String password;

    public String getUsername() {
        return username;
    }

    public UserBack setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserBack setPassword(String password) {
        this.password = password;
        return this;
    }

    @Override
    public String toString() {
        return "UserBack{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
    public static UserBack build(String username,String password){
       return new UserBack().setUsername(username).setPassword(password);
    }
}
