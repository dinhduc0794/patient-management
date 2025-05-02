package com.javaweb.hospital.services.medication;

import com.javaweb.hospital.dto.medication.MedicationDto;

public interface IMedicationService {

    MedicationDto createMedication(MedicationDto dto);
    MedicationDto updateMedication(MedicationDto dto);
    void deleteMedication(Integer id);
}
