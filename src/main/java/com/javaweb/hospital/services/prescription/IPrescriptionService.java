package com.javaweb.hospital.services.prescription;

import com.javaweb.hospital.dto.prescription.PrescriptionDto;

public interface IPrescriptionService {

    PrescriptionDto createPrescription(PrescriptionDto dto);
    PrescriptionDto updatePrescription(PrescriptionDto dto);
    void deletePrescription(Long id);
}
