package com.javaweb.hospital.services;

import com.javaweb.hospital.services.dto.patient.PatientDto;

import java.util.UUID;

public interface IPatientService {

    PatientDto createPatient(PatientDto patientDto);
    PatientDto updatePatient(PatientDto patientDto);
    void deletePatient(UUID id);


}
