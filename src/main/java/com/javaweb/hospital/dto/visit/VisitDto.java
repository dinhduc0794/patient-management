package com.javaweb.hospital.dto.visit;

import com.javaweb.hospital.dto.doctor.DoctorDto;
import com.javaweb.hospital.dto.patient.PatientDto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

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

    public VisitDto(Long id) {
        this(id, null, null, null, null, null, null, null);
    }

    public VisitDto(Long id, UUID patientId) {
        this(id, null, null, null, null, null, new PatientDto(patientId), null);
    }
}
