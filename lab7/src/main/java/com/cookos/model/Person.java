package com.cookos.model;

import lombok.Data;

@Data
public class Person {
    private String name;
    private String phone;
    private String email;

    public Person(String name, String phone, String email) {
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    public Person() {
        this("", "", "");
    }

    
    
}
