package com.javaweb.hospital.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Table(name = "visits")
@Entity
@Setter
@Getter
public class Visit extends BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "visit_time", nullable = false)
    private LocalDateTime visitTime;

    @Column(name = "symptoms", length = 1022)
    private String symptoms;

    @Column(name = "diagnosis", length = 1022)
    private String diagnosis;

    @Column(name = "treatment", length = 1022)
    private String treatment;

    @Column(name = "notes", length = 1022)
    private String notes;

    @ManyToOne(fetch = FetchType.EAGER,
      cascade = { CascadeType.DETACH, CascadeType.REFRESH })
    @JoinColumn(name = "patient_id", referencedColumnName = "id")
    private Patient patient;

    @ManyToOne(fetch = FetchType.EAGER,
      cascade = { CascadeType.DETACH, CascadeType.REFRESH })
    @JoinColumn(name = "doctor_id", referencedColumnName = "id")
    private Doctor doctor;
}
