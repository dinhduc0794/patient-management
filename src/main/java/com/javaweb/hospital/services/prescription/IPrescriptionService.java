package com.javaweb.hospital.services.prescription;

import com.javaweb.hospital.dto.prescription.PrescriptionDto;

import java.util.UUID;

public interface IPrescriptionService {

    PrescriptionDto createPrescription(PrescriptionDto dto);
    PrescriptionDto updatePrescription(PrescriptionDto dto);
    void deletePrescription(Long id);
    void deletePrescriptions(UUID patientId, Long visitId, Long prescriptionId);
}
