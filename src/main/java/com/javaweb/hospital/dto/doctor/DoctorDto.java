package com.javaweb.hospital.dto.doctor;

import java.io.Serializable;
import java.util.UUID;

public record DoctorDto(
    UUID id,
    String firstName,
    String lastName,
    String specialization,
    String contactNumber,
    String email
) implements Serializable {
}
