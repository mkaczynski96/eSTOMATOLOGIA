package com.estomatologia.estomatologia.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import java.io.Serializable;


@Getter
@Setter
@NoArgsConstructor
@Embeddable
@AllArgsConstructor
public class DoctorSpecializationKey implements Serializable {

    @Column(name = "doctor_id")
    private Long doctorId;

    @Column(name = "specialization_id")
    private Long specializationId;
}
