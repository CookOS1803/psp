package com.cookos;

import java.util.List;

import org.hibernate.*;
import org.hibernate.boot.*;
import org.hibernate.boot.registry.*;
import org.hibernate.cfg.*;

import jakarta.persistence.NoResultException;

public class EmployeeDao implements AutoCloseable {

    private final StandardServiceRegistry serviceRegistry;
    private final SessionFactory sessionFactory;

    public EmployeeDao() {
        this.serviceRegistry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();

        var meta = new MetadataSources(serviceRegistry).getMetadataBuilder().build();  
        sessionFactory = meta.getSessionFactoryBuilder().build();

        var configuration = new Configuration();
        configuration.addAnnotatedClass(Employee.class);
    }

    @Override
    public void close() throws Exception {
        sessionFactory.close();
        serviceRegistry.close();        
    }
    
    public List<Employee> selectAll() {
        try (var session = sessionFactory.openSession()) {
            
            var query = session.getCriteriaBuilder().createQuery(Employee.class);
            var root = query.from(Employee.class);

            query.select(root);

            var employees = session.createQuery(query).list();

            return employees;
        }
    }

    public Employee findById(int id) {
        try (var session = sessionFactory.openSession()) {            
            var cb = session.getCriteriaBuilder();
            var query = cb.createQuery(Employee.class);
            var root = query.from(Employee.class);

            query.select(root).where(cb.equal(root.get("id"), id));

            try {
                var employee = session.createQuery(query).getSingleResult();
                return employee;
            } catch (NoResultException e) {
                return null;
            }
        }
    }

    public void updateEmployee(Employee employee) {
        try (var session = sessionFactory.openSession()) {
            var transaction = session.beginTransaction();

            session.merge(employee);
            
            transaction.commit();            
        }
    }

    public int addEmployee(Employee employee) {
        try (var session = sessionFactory.openSession()) {
            var transaction = session.beginTransaction();

            session.persist(employee);
            var id = (Integer)session.getIdentifier(employee);
            
            transaction.commit();

            return id;
        }
    }

    public void removeEmployee(int id) throws Exception {
        try (var session = sessionFactory.openSession()) {
            var transaction = session.beginTransaction();

            var employee = new Employee(id);
            session.remove(employee);
            
            transaction.commit();         
        }
    }
}
