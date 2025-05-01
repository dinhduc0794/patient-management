package com.javaweb.hospital.controllers.patient.request;

import com.javaweb.hospital.enums.Gender;
import jakarta.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDateTime;

public record PatientUpdateReq(
    @NotBlank @Size(max = 255) String firstName,
    @NotBlank @Size(max = 255) String lastName,
    @NotNull Gender gender,
    @Past LocalDateTime dateOfBirth,
    @Email String email,
    String contactNumber
) implements Serializable {
}
