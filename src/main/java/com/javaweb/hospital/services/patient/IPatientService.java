package com.javaweb.hospital.services.patient;

import com.javaweb.hospital.dto.patient.PatientDto;

import java.util.List;
import java.util.UUID;

public interface IPatientService {

    PatientDto createPatient(PatientDto patientDto);
    PatientDto updatePatient(PatientDto patientDto);
    void deletePatient(UUID id);

    List<PatientDto> getPatients(Integer page, Integer limit);
}
