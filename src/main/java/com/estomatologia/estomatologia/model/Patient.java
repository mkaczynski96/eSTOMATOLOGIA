package com.estomatologia.estomatologia.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Patient")
@Getter
@Setter
public class Patient extends Person {

    @Id
    @Column(name = "id")
    private long id;

    @Column(name = "medicamentsTakenPermamently")
    private String medicamentsTakenPermamently;
    @Column(name = "chronicDiseases")
    private String chronicDiseases;

    @OneToMany(mappedBy = "patient")
    private Set<ProposedVisit> proposedVisits;

    @OneToMany(mappedBy = "patient")
    private Set<Visit> visits;

    @OneToOne
    @MapsId
    private User userPatient;
}
