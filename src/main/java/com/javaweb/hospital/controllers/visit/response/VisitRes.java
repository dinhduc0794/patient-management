package com.javaweb.hospital.controllers.visit.response;

import com.javaweb.hospital.controllers.doctor.response.DoctorRes;
import com.javaweb.hospital.controllers.patient.response.PatientRes;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.time.LocalDateTime;

public record VisitRes(
    Long id,
    LocalDateTime visitTime,
    String symptoms,
    String treatment,
    String diagnosis,
    String notes,
    PatientRes patient,
    DoctorRes doctor
) implements Serializable {
}
