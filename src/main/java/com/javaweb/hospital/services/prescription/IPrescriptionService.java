package com.javaweb.hospital.services.prescription;

import com.javaweb.hospital.dto.prescription.PrescriptionDto;

import java.util.List;
import java.util.UUID;

public interface IPrescriptionService {

    PrescriptionDto createPrescription(PrescriptionDto dto);
    PrescriptionDto updatePrescription(PrescriptionDto dto);
    void deletePrescription(Long id);
    void deletePrescriptions(UUID patientId, Long visitId, Long prescriptionId);

    List<PrescriptionDto> getPrescriptions(UUID patientId, Long visitId, Integer page, Integer limit);
    List<PrescriptionDto> getPrescriptions(UUID patientId, Integer page, Integer limit);
}
