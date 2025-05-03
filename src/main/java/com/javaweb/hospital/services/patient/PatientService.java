package com.javaweb.hospital.services.patient;

import com.javaweb.hospital.exception.ModelNotFoundException;
import com.javaweb.hospital.models.Patient;
import com.javaweb.hospital.repositories.patient.PatientRepository;
import com.javaweb.hospital.dto.patient.PatientDto;
import com.javaweb.hospital.dto.patient.PatientDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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
        Patient patient = this.patientRepo.findById(dto.id())
            .orElseThrow(() -> ModelNotFoundException.of("Patient id", Patient.class.getSimpleName()));
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
        Patient patient = this.patientRepo.findById(id)
            .orElseThrow(() -> ModelNotFoundException.of("Patient id", Patient.class.getSimpleName()));
        this.patientRepo.delete(patient);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, timeout = 2)
    public List<PatientDto> getPatients(Integer page, Integer limit) {
        Page<Patient> patients = this.patientRepo.findAllBy(PageRequest.of(page - 1, limit, Sort.by("firstName", "lastName").ascending()));
        return patients.stream().map(patientMapper::toDto).toList();
    }
}
