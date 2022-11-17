package com.cookos;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private final int id;

    @Column (name = "first_name")
    private String firstName;

    @Column (name = "last_name")
    private String lastName;

    @Column (name = "position")
    private String position;

    @Column (name = "salary")
    private float salary;

    @Column (name = "experience")    
    private int experience;

    public Employee() {
        this(0);
    }
    public Employee(int id)
    {
        this.id = id;
        this.firstName = "";
        this.lastName = "";
        this.position = "";
        this.salary = 0f;
        this.experience = 0;
    }
    
}
