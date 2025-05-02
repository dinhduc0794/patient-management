package com.javaweb.hospital.models;

import com.javaweb.hospital.enums.Gender;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;
import java.util.UUID;

@Entity
@Table(name = "patients")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Patient extends BaseModel {

    @Id
    @JdbcTypeCode(SqlTypes.CHAR)
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", unique = true, nullable = false, length = 36, columnDefinition = "CHAR(36)")
    private UUID id;

    @Column(name = "first_name", length = 255, nullable = false)
    private String firstName;

    @Column(name = "last_name", length = 255, nullable = false)
    private String lastName;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "date_of_birth", nullable = false)
    private LocalDateTime dateOfBirth;

    @Column(name = "email", length = 255)
    private String email;

    @Column(name = "contact_number", length = 20, columnDefinition = "VARCHAR(20)")
    private String contactNumber;

    @Column(name = "address", length = 255)
    private String address;

    @OneToMany(fetch = FetchType.LAZY,
      cascade = { CascadeType.DETACH, CascadeType.REFRESH},
      mappedBy = "patient")
    private Set<Visit> visits = new TreeSet<>();
}
