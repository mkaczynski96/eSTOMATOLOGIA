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


    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("doctor_id")
    @JoinColumn(name = "doctor_id", nullable = false, insertable = false, updatable = false)
    Doctor doctor;

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("specialization_id")
    @JoinColumn(name = "specialization_id", nullable = false, insertable = false, updatable = false)
    Specialization specialization;

    @Column(name = "license")
    private String license;
}
