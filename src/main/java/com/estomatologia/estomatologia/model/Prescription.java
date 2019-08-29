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

    private long visitId;

    private String clinicName;

    @Column(name = "branchOfNFZ")
    private int branchOfNFZ;

    @Column(name = "medicaments")
    private String medicaments;

}
