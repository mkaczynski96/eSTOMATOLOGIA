package com.estomatologia.estomatologia.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "Receptionist")
@Getter
@Setter
public class Receptionist extends Person {

    @Id
    @Column(name = "id")
    private long id;

    @OneToOne
    @MapsId
    private User userReceptionist;
}
