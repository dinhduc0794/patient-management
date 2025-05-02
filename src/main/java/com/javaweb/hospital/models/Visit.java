package com.javaweb.hospital.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.TreeSet;

@Table(name = "visits")
@Entity
@Setter
@Getter
public class Visit extends BaseModel implements Comparable<Visit> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @OneToMany(fetch = FetchType.LAZY,
      cascade = { CascadeType.DETACH, CascadeType.REFRESH },
      orphanRemoval = true,
      mappedBy = "patientVisit")
    private Set<Prescription> prescriptions = new TreeSet<>();

    @Override
    public int compareTo(Visit o) {
        return o.visitTime.compareTo(this.visitTime);
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
        if (doctor != null) {
            doctor.getPatientVisits().add(this);
        }
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
        if (patient != null) {
            patient.getVisits().add(this);
        }
    }
}
