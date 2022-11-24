package com.cookos;

import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.FileWriter;
import java.util.Objects;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.*;

import com.cookos.entities.*;

public @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TestingClasses {

    private static SessionFactory sessionFactory = null;

    @BeforeAll
    static void configure() {
        try {
            var configuration = new Configuration();
            configuration.configure();
            sessionFactory = configuration.buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    private static Session getSession() throws HibernateException {
        return sessionFactory.openSession();
    }

    @Test
    @Order(1)
    void creation() {
        try {
            Session session = getSession();
            JAXBAPI.executeXML("files/1.xml", session);
            var personEntity = JAXBAPI.executeXML("files/2.xml", session);
            var personXML = JAXBAPI.getQuery("files/1.xml");
            assertEquals(personEntity, personXML);
        } catch (IOException | JAXBException e) {
            fail();
        }
    }

    @Test
    @Order(2)
    void update() {
        try {
            Session session = getSession();
            int prev = Objects.requireNonNull(JAXBAPI.executeXML("files/2.xml", session)).getDutyBound();
            JAXBAPI.executeXML("files/3.xml", session);
            int post = Objects.requireNonNull(JAXBAPI.executeXML("files/2.xml", session)).getDutyBound();
            assertNotEquals(prev, post);
        } catch (IOException | JAXBException | NullPointerException e) {
            fail();
        }
    }

    @Test
    @Order(3)
    void delete() {
        try {
            Session session = getSession();
            JAXBAPI.executeXML("files/4.xml", session);
            PersonEntity p = JAXBAPI.executeXML("files/2.xml", session);
            assertNull(p);
        } catch (IOException | JAXBException e) {
            fail();
        }
    }


   @Test
  @Order(4)
    void toxml() {
        PersonEntity personEntity = getSession().find(PersonEntity.class, "900");
        try {
            var context = JAXBContext.newInstance(PersonEntity.class);
            var marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            var writer = new BufferedWriter(new FileWriter("files/out.xml"));
            marshaller.marshal(personEntity, writer);
            writer.close();
        } catch (JAXBException | IOException e) {
            fail();
        }
    }

    @Test
    @Order(5)
    void jsontest(){
        try {
            JAXBAPI.addFromJson("files/in.json", getSession());
            JAXBAPI.dumpToJson("files/out.json", getSession(), "400");
            JAXBAPI.executeXML("files/5.xml", getSession());
        } catch (IOException | JAXBException e) {
            fail();
        }
    }

}


