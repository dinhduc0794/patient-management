package com.javaweb.hospital.controllers.patient.response;

import com.javaweb.hospital.models.enumurate.Gender;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

public record PatientRes(
  UUID id,
  String firstName,
  String lastName,
  Gender gender,
  LocalDateTime dateOfBirth,
  String email,
  String contactNumber
) implements Serializable {

}
