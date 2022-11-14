package com.cookos;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    @Getter @Setter private int id;

    @Column (name = "first_name")
    @Getter @Setter private String firstName;

    @Column (name = "last_name")
    @Getter @Setter private String lastName;

    @Column (name = "position")
    @Getter @Setter private String position;

    @Column (name = "salary")
    @Getter @Setter private float salary;

    @Column (name = "experience")    
    @Getter @Setter private int experience;

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
    @Override
    public String toString() {
        return "Employee [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", position=" + position
                + ", salary=" + salary + ", experience=" + experience + "]";
    }
    
}
