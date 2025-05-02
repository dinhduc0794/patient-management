package com.javaweb.hospital.dto.patient;

import com.javaweb.hospital.enums.Gender;

import java.time.LocalDateTime;
import java.util.UUID;

public record PatientDto(
  UUID id,
  String firstName,
  String lastName,
  Gender gender,
  LocalDateTime dateOfBirth,
  String email,
  String contactNumber,
  String address
) {
    public PatientDto(UUID id) {
        this(id, null, null, null, null, null, null, null);
    }
}
