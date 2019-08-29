package com.estomatologia.estomatologia.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Doctor")
@Getter
@Setter
public class Doctor extends Person {

    @Id
    @GeneratedValue
    private long id;

}
