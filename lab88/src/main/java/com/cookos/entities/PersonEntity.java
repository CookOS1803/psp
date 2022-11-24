package com.cookos.entities;

import java.io.Serializable;
import java.sql.Date;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "person", schema = "lb8")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@XmlType(name = "person")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class PersonEntity implements Serializable {
    @Id
    @Column(name = "id", nullable = false, length = 45)
    private String id;

    @Basic
    @Column(name = "surname", nullable = false, length = 45)
    private String surname;
    @Basic
    @Column(name = "forename", nullable = false, length = 45)
    private String forename;

    @Basic
    @Column(name = "patronymic", nullable = false, length = 45)
    private String patronymic;

    @Basic
    @Column(name = "DOB", nullable = false)
    @XmlJavaTypeAdapter(DateTimeAdapter.class)
    private Date dob;

    @Basic
    @Column(name = "sex", nullable = false)
    private byte sex;

    @Basic
    @Column(name = "passport_series", nullable = false, length = 45)
    private String passportSeries;

    @Basic
    @Column(name = "passport_number", nullable = false, length = 45)
    private String passportNumber;

    @Basic
    @Column(name = "city", nullable = false, length = 45)
    private String city;

    @Basic
    @Column(name = "address", nullable = false, length = 45)
    private String address;

    @Basic
    @Column(name = "phone_home", length = 45)
    private String phoneHome;

    @Basic
    @Column(name = "phone_mobile", length = 45)
    private String phoneMobile;

    @Basic
    @Column(name = "citizenship", nullable = false, length = 45)
    private String citizenship;

    @Basic
    @Column(name = "duty_bound", nullable = false)
    private byte dutyBound;

    private QueryType qtype;

}

