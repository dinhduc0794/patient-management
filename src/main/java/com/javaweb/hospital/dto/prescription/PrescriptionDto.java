package com.javaweb.hospital.dto.prescription;

import com.javaweb.hospital.dto.medication.MedicationDto;
import com.javaweb.hospital.dto.visit.VisitDto;
import lombok.Data;

@Data
public class PrescriptionDto {
    private Long id;
    private Integer quantity;
    private String instructions;
    private Long duration;
    private VisitDto patientVisit;
    private MedicationDto medication;
}
