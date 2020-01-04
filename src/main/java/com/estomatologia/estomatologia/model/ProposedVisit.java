package com.estomatologia.estomatologia.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "ProposedVisit")
@Getter
@Setter
public class ProposedVisit {

    @Id
    @GeneratedValue
    private long id;

    @ManyToOne
    @JoinColumn(name="patient_id", nullable=false)
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctor doctor;

    @Column(name = "date")
    private String date;

    @Column(name = "hour")
    private String hour;
}
