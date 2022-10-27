package com.cookos;

public class Employee {
    private int id;
    private String firstName;
    private String lastName;
    private String position;
    private float salary;
    public Employee() {
        this.id = 0;
        this.firstName = "";
        this.lastName = "";
        this.position = "";
        this.salary = 0f;
    }
    public Employee(int id, String firstName, String lastName, String position, float salary) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
        this.salary = salary;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getPosition() {
        return position;
    }
    public void setPosition(String position) {
        this.position = position;
    }
    public float getSalary() {
        return salary;
    }
    public void setSalary(float salary) {
        this.salary = salary;
    }
    @Override
    public String toString() {
        return "Employee [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", position=" + position
                + ", salary=" + salary + "]";
    }
    
}
