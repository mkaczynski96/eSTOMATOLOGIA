package com.estomatologia.estomatologia.model;

import javax.persistence.Column;

public class Person {

    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;
    @Column(name = "PESEL")
    private String pesel;
    @Column(name = "phoneNumber")
    private String phoneNumber;


}
