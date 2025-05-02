package com.javaweb.hospital.models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.Set;
import java.util.TreeSet;
import java.util.UUID;

@Table(name = "doctors")
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Doctor extends BaseModel {

    @Id
    @JdbcTypeCode(SqlTypes.CHAR)
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false, length = 36, columnDefinition = "CHAR(36)")
    private UUID id;

    @Column(name = "first_name", length = 255, nullable = false)
    private String firstName;

    @Column(name = "last_name", length = 255, nullable = false)
    private String lastName;

    @Column(name = "specialization", length = 255)
    private String specialization;

    @Column(name = "contact_number", length = 20)
    private String contactNumber;

    @Column(name = "email", length = 255)
    private String email;

    @OneToMany(fetch = FetchType.LAZY,
      cascade = { CascadeType.DETACH, CascadeType.MERGE },
      mappedBy = "doctor")
    @Builder.Default
    private Set<Visit> patientVisits = new TreeSet<>();


}
