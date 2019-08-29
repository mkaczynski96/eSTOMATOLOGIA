package com.estomatologia.estomatologia.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "Visit")
@Getter
@Setter
public class Visit {

    @Id
    @GeneratedValue
    private long id;

    private long doctorId;

    private long patientId;

    @Column(name = "date")
    private String date;
    @Column(name = "diagnosis")
    private String diagnosis;
    @Column(name = "recommendations")
    private String recommendations;
}
