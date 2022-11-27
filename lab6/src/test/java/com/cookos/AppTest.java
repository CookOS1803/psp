package com.cookos;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.TestInstance.Lifecycle;

@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AppTest {
    
    private static final String FIRST_NAME = "Test";
    private static final String LAST_NAME = "Employee";
    private static final String POSITION = "TestPosition";
    private static final int SALARY = 322;
    private static final int EXPIRIENCE = 322;

    private static final String NEW_POSITION = "NewTestPosition";

    private EmployeeDao dao;
    private Employee employee;

    @BeforeAll
    public void configure() {
        dao = new EmployeeDao();
        
        employee = new Employee();
        employee.setFirstName(FIRST_NAME);
        employee.setLastName(LAST_NAME);
        employee.setPosition(POSITION);
        employee.setSalary(SALARY);
        employee.setExperience(EXPIRIENCE);
    }

    @Test
    @Order(1)
    public void testAdd() {
        int id = dao.addEmployee(employee);

        var persistedEmployee = dao.findById(id);

        assertEquals(employee, persistedEmployee, "Failed to add new employee");
    }

    @Test
    @Order(2)
    public void testUpdate() {
        employee.setPosition(NEW_POSITION);

        dao.updateEmployee(employee);

        var persistedEmployee = dao.findById(employee.getId());

        assertEquals(employee, persistedEmployee, "Failed to update employee");
    }

    @Test
    @Order(3)
    public void testRemove() {
        try {
            dao.removeEmployee(employee.getId());
        } catch (Exception e) {
            fail("Failded to remove employee");
        }
    }

    @AfterAll
    public void cleanup() {
        try {
            dao.close();
        } catch (Exception e) {
            fail();
        }
    }

}
