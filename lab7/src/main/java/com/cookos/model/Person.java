package com.cookos.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Person {
    private String name;
    private String phone;
    private String email;

    public Person(Person other) {
        this(other.name, other.phone, other.email);
    }
}
