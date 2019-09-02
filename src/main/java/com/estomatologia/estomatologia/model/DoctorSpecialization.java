package com.estomatologia.estomatologia.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "Doctor_Specialization")
@Getter
@Setter
public class DoctorSpecialization {

    @EmbeddedId
    DoctorSpecializationKey id;

    @ManyToOne
    @MapsId("doctor_id")
    @JoinColumn(name = "doctor_id")
    Doctor doctor;

    @ManyToOne
    @MapsId("specialization_id")
    @JoinColumn(name = "specialization_id")
    Specialization specialization;

    @Column(name = "license")
    private String license;
}
