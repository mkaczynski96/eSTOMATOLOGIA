package com.estomatologia.estomatologia.model;

import javax.persistence.Column;

public class Person {

    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
