package com.javaweb.hospital.services.patient;

import com.javaweb.hospital.models.Patient;
import com.javaweb.hospital.repositories.patient.PatientRepository;
import com.javaweb.hospital.services.dto.patient.PatientDto;
import com.javaweb.hospital.services.dto.patient.PatientDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor(onConstructor_ = { @Autowired })
public class PatientService implements IPatientService {

    private final PatientRepository patientRepo;

    private PatientDtoMapper patientMapper;

    @Autowired
    public void setPatientMapper(@Qualifier("patientDtoMapperImpl") PatientDtoMapper patientMapper) {
        this.patientMapper = patientMapper;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE, timeout = 2)
    public PatientDto createPatient(PatientDto dto) {
        Patient patient = this.patientMapper.toEntity(dto);
        Patient patientSaved = this.patientRepo.save(patient);
        return this.patientMapper.toDto(patientSaved);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, timeout = 2)
    public PatientDto updatePatient(PatientDto dto) {
        Patient patient = this.patientRepo.findById(dto.id()).get();
        patient.setEmail(dto.email());
        patient.setFirstName(dto.firstName());
        patient.setLastName(dto.lastName());
        patient.setGender(dto.gender());
        patient.setDateOfBirth(dto.dateOfBirth());
        Patient patientSaved = this.patientRepo.save(patient);
        return this.patientMapper.toDto(patientSaved);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE, timeout = 2)
    public void deletePatient(UUID id) {
        this.patientRepo.deleteById(id);
    }
}
