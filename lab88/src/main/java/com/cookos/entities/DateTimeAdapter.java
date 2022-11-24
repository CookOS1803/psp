package com.cookos.entities;

import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class DateTimeAdapter extends XmlAdapter<String, Date>{

    @Override
    public String marshal(Date date) throws Exception {
        return new SimpleDateFormat("yyyy-MM-dd").format(date);
    }

    @Override
    public Date unmarshal(String xml) throws Exception {
        return Date.valueOf(xml);
    }
    
}
