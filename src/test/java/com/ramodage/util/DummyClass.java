package com.ramodage.util;

import java.util.Date;

/**
 * User: rixonmathew
 * Date: 16/06/14
 * Time: 12:53 AM
 */
public class DummyClass {
    private String name;
    private int id;
    private Date dateOfBirth;


    String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}
