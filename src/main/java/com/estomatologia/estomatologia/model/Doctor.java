package com.estomatologia.estomatologia.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Doctor")
public class Doctor extends Person {

    @Id
    @GeneratedValue
    private long id;

}
