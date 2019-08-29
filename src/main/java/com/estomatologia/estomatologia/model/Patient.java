package com.estomatologia.estomatologia.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "Patient")
@Getter
@Setter
public class Patient extends Person {

    @Id
    @GeneratedValue
    private long id;

    @Column(name = "medicamentsTakenPermamently")
    private String medicamentsTakenPermamently;
    @Column(name = "chronicDiseases")
    private String chronicDiseases;
}
