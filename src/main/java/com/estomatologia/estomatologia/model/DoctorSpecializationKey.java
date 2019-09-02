package com.estomatologia.estomatologia.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class DoctorSpecializationKey implements Serializable {

    @Column(name = "doctor_id")
    private Long doctorId;

    @Column(name = "specialization_id")
    private Long specialization_id;
}
