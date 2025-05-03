package com.javaweb.hospital.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Table(name = "prescriptions")
@Entity
@Setter
@Getter
public class Prescription extends BaseModel implements Comparable<Prescription> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "instructions", nullable = false)
    private String instructions;

    @Column(name = "duration", nullable = false)
    private Long duration;

    @ManyToOne(fetch = FetchType.EAGER,
      cascade = { CascadeType.DETACH, CascadeType.REFRESH },
      optional = true)
    @JoinColumn(name = "visit_id", referencedColumnName = "id", nullable = true)
    private Visit patientVisit;

    @ManyToOne(fetch = FetchType.EAGER,
        cascade = { CascadeType.DETACH, CascadeType.REFRESH },
        optional = true)
    @JoinColumn(name = "medication_id", referencedColumnName = "id", nullable = true)
    private Medication medication;

    @Override
    public int compareTo(Prescription o) {
        return o.createdAt.compareTo(this.createdAt);
    }
}
