package com.javaweb.hospital.dto.prescription;

import com.javaweb.hospital.dto.medication.MedicationDto;
import com.javaweb.hospital.dto.patient.PatientDto;
import com.javaweb.hospital.dto.visit.VisitDto;

public record PrescriptionDto(
    Long id,
    Integer quantity,
    String instructions,
    Long duration,
    VisitDto patientVisit,
    MedicationDto medication) {
}
