package com.estomatologia.estomatologia.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "Administrator")
@Getter
@Setter
public class Administrator extends Person {

    @Id
    @Column(name = "id")
    private long id;

    @OneToOne
    @MapsId
    private User userAdministrator;
}
