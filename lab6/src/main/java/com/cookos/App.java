package com.cookos;

import org.hibernate.boot.*;
import org.hibernate.boot.registry.*;

public class App 
{
    public static void main( String[] args )
    {
        var ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();            
        var meta = new MetadataSources(ssr).getMetadataBuilder().build();         
        var factory = meta.getSessionFactoryBuilder().build();  
        var session = factory.openSession();  
        var transaction = session.beginTransaction();   
                 
        var e1 = new Employee();
        e1.setId(101);    
        e1.setFirstName("Gaurav");    
        e1.setLastName("Chawla");    
            
        session.save(e1);  
        transaction.commit();
        factory.close();  
        session.close();   
    }
}
