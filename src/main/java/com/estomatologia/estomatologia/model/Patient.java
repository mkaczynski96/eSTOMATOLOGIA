package com.estomatologia.estomatologia.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Patient")
public class Patient extends Person {

    @Id
    @GeneratedValue
    private long id;
}
