package com.javaweb.hospital.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import java.util.TreeSet;

@Entity
@Table(name = "medications")
@Getter
@Setter
public class Medication extends BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", unique = true)
    private Integer id;

    @Column(name = "name", length = 255, nullable = false)
    private String name;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "dosage", length = 255)
    private String dosage;

    @OneToMany(fetch = FetchType.LAZY,
      cascade = { CascadeType.DETACH, CascadeType.REFRESH },
      mappedBy = "medication")
    private Set<Prescription> prescriptions = new TreeSet<>();
}
