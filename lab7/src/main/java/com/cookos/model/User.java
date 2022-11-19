package com.cookos.model;

import lombok.*;

@Data
public class User {

    private int id;
    private String login;
    private String passw;

    public User(String name, String password) {
        this.login = name;
        this.passw = password;
    }    
}

