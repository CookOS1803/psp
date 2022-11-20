package com.cookos.model;

import lombok.*;

@Data
public class User {

    private int id;
    private String login;
    private byte[] passw;

    public User(String name, byte[] password) {
        this.login = name;
        this.passw = password;
    }    
}

