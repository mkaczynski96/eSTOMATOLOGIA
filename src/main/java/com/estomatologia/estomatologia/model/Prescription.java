package com.estomatologia.estomatologia.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "Prescription")
@Getter
@Setter
public class Prescription {

    @Id
    @GeneratedValue
    private long id;

    private String clinicName;

    @Column(name = "branchOfNFZ")
    private int branchOfNFZ;

    @Column(name = "medicaments")
    private String medicaments;

    @ManyToOne
    @JoinColumn(name = "visit_id", nullable = false)
    private Visit visit;
}
