package com.estomatologia.estomatologia.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "Specialization")
@Getter
@Setter
public class Specialization {

    @Id
    @GeneratedValue
    private long id;

    @Column(name = "name")
    private String name;
}
