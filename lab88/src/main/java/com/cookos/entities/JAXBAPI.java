package com.cookos.entities;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import org.hibernate.Session;

import com.google.gson.GsonBuilder;

public class JAXBAPI {

    public static PersonEntity executeXML(String filename, Session session) throws IOException, JAXBException {
        var personXML = getQuery(filename);
        switch (personXML.getQtype()) {
            case CREATE:
                create(personXML, session);
                break;
            case UPDATE:
                update(personXML, session);
                break;
            case READ:
                return read(personXML, session);
            case DELETE:
                delete(personXML, session);
                break;
        }
        return null;
    }

    public static PersonEntity getQuery(String filename) throws IOException, JAXBException {
        var strbldr = new StringBuilder();
        var lines = (ArrayList<String>) Files.readAllLines(Paths.get(filename), StandardCharsets.UTF_8);
        for (String line : lines) {
            strbldr.append(line);
        }
        var jaxbContext = JAXBContext.newInstance(PersonEntity.class);
        var jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        return (PersonEntity) jaxbUnmarshaller.unmarshal(new StringReader(strbldr.toString()));
    }

    public static void create(PersonEntity entity, Session session) {
        session.beginTransaction();
        session.persist(entity);
        session.getTransaction().commit();
    }

    public static void update(PersonEntity entity, Session session) {
        session.beginTransaction();
        var mod = session.get(PersonEntity.class, entity.getId());
        if (entity.getSurname() != null) {
            mod.setSurname(entity.getSurname());
        }
        if (entity.getAddress() != null) {
            mod.setAddress(entity.getAddress());
        }
        mod.setDutyBound(entity.getDutyBound());
        session.update(mod);
        session.getTransaction().commit();
    }

    public static PersonEntity read(PersonEntity entity, Session session) {
        return session.get(PersonEntity.class, entity.getId());
    }

    public static void delete(PersonEntity entity, Session session) {
        session.beginTransaction();
        var myObject = session.load(PersonEntity.class, entity.getId());
        session.delete(myObject);
        session.getTransaction().commit();
    }

    public static void addFromJson(@SuppressWarnings("SameParameterValue") String filename, Session session) throws IOException {
        var gson = new GsonBuilder().setPrettyPrinting().create();
        try (var reader = new FileReader(filename)) {
            PersonEntity personEntity = gson.fromJson(reader, PersonEntity.class);
            create(personEntity, session);
        }
    }

    public static void dumpToJson(@SuppressWarnings("SameParameterValue") String filename, Session session, @SuppressWarnings("SameParameterValue") String ID) throws IOException {
        var gson = new GsonBuilder().setPrettyPrinting().create();
        PersonEntity personEntity = session.find(PersonEntity.class, ID);
        try (var writer = new FileWriter(filename)) {
            gson.toJson(personEntity, writer);
        }
    }
}

