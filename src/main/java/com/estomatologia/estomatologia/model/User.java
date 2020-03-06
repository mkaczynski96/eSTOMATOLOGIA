package com.estomatologia.estomatologia.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "User")
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(nullable = false, name = "username")
    private String username;

    @Column(nullable = false, name = "password")
    private String password;

    @Column(nullable = false, name = "enabled")
    private boolean enabled;

    @OneToOne(mappedBy = "userAdministrator", cascade = CascadeType.ALL)
    private Administrator administrator;

    @OneToOne(mappedBy = "userDoctor", cascade = CascadeType.ALL)
    private Doctor doctor;

    @OneToOne(mappedBy = "userPatient", cascade = CascadeType.ALL)
    private Patient patient;

    @OneToOne(mappedBy = "userReceptionist", cascade = CascadeType.ALL)
    private Receptionist receptionist;


}
