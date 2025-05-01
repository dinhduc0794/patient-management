package com.javaweb.hospital.services.dto.visit;

import com.javaweb.hospital.services.dto.doctor.DoctorDto;
import com.javaweb.hospital.services.dto.patient.PatientDto;

import java.io.Serializable;
import java.time.LocalDateTime;

public record VisitDto(
    Long id,
    LocalDateTime visitTime,
    String symptoms,
    String treatment,
    String diagnosis,
    String notes,
    PatientDto patient,
    DoctorDto doctor
) implements Serializable {
}
