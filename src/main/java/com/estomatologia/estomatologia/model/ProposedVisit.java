package com.estomatologia.estomatologia.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "ProposedVisit")
@Getter
@Setter
public class ProposedVisit {

    @Id
    @GeneratedValue
    private long id;

    private long patientId;

    private long doctorId;

    @Column(name = "date")
    private String date;
}
