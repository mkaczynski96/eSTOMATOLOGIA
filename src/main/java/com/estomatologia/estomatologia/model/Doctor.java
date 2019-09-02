package com.estomatologia.estomatologia.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Doctor")
@Getter
@Setter
public class Doctor extends Person {

    @Id
    @GeneratedValue
    private long id;

    @OneToMany(mappedBy = "doctor")
    private Set<ProposedVisit> proposedVisits;

    @OneToMany(mappedBy = "doctor")
    private Set<Visit> visits;
}
