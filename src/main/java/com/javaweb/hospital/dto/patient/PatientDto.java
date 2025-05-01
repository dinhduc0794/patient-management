package com.javaweb.hospital.dto.patient;

import com.javaweb.hospital.models.enumurate.Gender;

import java.time.LocalDateTime;
import java.util.UUID;

public record PatientDto(
  UUID id,
  String firstName,
  String lastName,
  Gender gender,
  LocalDateTime dateOfBirth,
  String email,
  String contactNumber
) {
}
