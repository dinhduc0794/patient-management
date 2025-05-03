package com.javaweb.hospital.services.prescription;

import com.javaweb.hospital.dto.prescription.PrescriptionDto;
import com.javaweb.hospital.dto.prescription.PrescriptionDtoMapper;
import com.javaweb.hospital.exception.ModelNotFoundException;
import com.javaweb.hospital.models.Medication;
import com.javaweb.hospital.models.Patient;
import com.javaweb.hospital.models.Prescription;
import com.javaweb.hospital.models.Visit;
import com.javaweb.hospital.repositories.medication.MedicationRepository;
import com.javaweb.hospital.repositories.patient.PatientRepository;
import com.javaweb.hospital.repositories.prescription.PrescriptionRepository;
import com.javaweb.hospital.repositories.visit.VisitRepository;
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
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor_ = { @Autowired })
public class PrescriptionService implements IPrescriptionService{

    private final PatientRepository patientRepo;
    private final PrescriptionRepository prescriptionRepo;
    private final VisitRepository visitRepo;
    private final MedicationRepository medicationRepo;

    private PrescriptionDtoMapper prescriptionMapper;

    @Autowired
    public void setPrescriptionMapper(@Qualifier("prescriptionDtoMapperImpl") PrescriptionDtoMapper prescriptionMapper) {
        this.prescriptionMapper = prescriptionMapper;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, timeout = 2)
    public PrescriptionDto createPrescription(PrescriptionDto dto) {
        Prescription prescription = this.prescriptionMapper.toEntity(dto);
        Visit visit = this.visitRepo.findById(dto.getPatientVisit().id())
            .orElseThrow(() -> ModelNotFoundException.of("Visit id", Visit.class.getSimpleName()));
        Medication medication = this.medicationRepo.findById(dto.getMedication().id())
            .orElseThrow(() -> ModelNotFoundException.of("Medication id", Medication.class.getSimpleName()));
        prescription.setMedication(medication);
        prescription.setPatientVisit(visit);
        Prescription prescriptionSaved = this.prescriptionRepo.save(prescription);
        return prescriptionMapper.toDto(prescriptionSaved);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, timeout = 2)
    public PrescriptionDto updatePrescription(PrescriptionDto dto) {
        Prescription prescription = this.prescriptionRepo.findById(dto.getId())
            .orElseThrow(() -> ModelNotFoundException.of("Prescription id", Prescription.class.getSimpleName()));
        prescription.setDuration(dto.getDuration());
        prescription.setQuantity(dto.getQuantity());
        prescription.setInstructions(dto.getInstructions());
        Prescription prescriptionSaved = this.prescriptionRepo.save(prescription);
        return this.prescriptionMapper.toDto(prescriptionSaved);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, timeout = 2)
    public void deletePrescription(Long id) {
        Prescription prescription = this.prescriptionRepo.findById(id)
            .orElseThrow(() -> ModelNotFoundException.of("Prescription id", Prescription.class.getSimpleName()));
        this.prescriptionRepo.delete(prescription);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, timeout = 2)
    public void deletePrescriptions(UUID patientId, Long visitId, Long prescriptionId) {
        Patient patient = this.patientRepo.findById(patientId)
            .orElseThrow(() -> ModelNotFoundException.of("Patient id", Patient.class.getSimpleName()));
        Visit visit = this.visitRepo.findById(visitId)
            .orElseThrow(() -> ModelNotFoundException.of("Visit id", Visit.class.getSimpleName()));
        Prescription prescription = this.prescriptionRepo.findById(prescriptionId)
            .orElseThrow(() -> ModelNotFoundException.of("Prescription id", Prescription.class.getSimpleName()));
        this.prescriptionRepo.delete(prescription);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, timeout = 2)
    public List<PrescriptionDto> getPrescriptions(UUID patientId, Long visitId, Integer page, Integer limit) {
        Patient patient = this.patientRepo.findById(patientId)
            .orElseThrow(() -> ModelNotFoundException.of("Patient id", Patient.class.getSimpleName()));
        Visit visit = this.visitRepo.findById(visitId)
            .orElseThrow(() -> ModelNotFoundException.of("Visit id", Visit.class.getSimpleName()));
        Page<Prescription> prescriptions = this.prescriptionRepo.getAllByPatientVisit(visit, PageRequest.of(page - 1, limit, Sort.by("createdAt").ascending()));
        return prescriptions.stream().map(prescriptionMapper::toDto).collect(Collectors.toList());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, timeout = 2)
    public List<PrescriptionDto> getPrescriptions(UUID patientId, Integer page, Integer limit) {
        Patient patient = this.patientRepo.findById(patientId)
            .orElseThrow(() -> ModelNotFoundException.of("Patient id", Patient.class.getSimpleName()));
        Page<Prescription> prescriptions = this.prescriptionRepo.findAllByPatient(patient, PageRequest.of(page, limit, Sort.by("createdAt").descending()));
        return prescriptions.stream().map(prescriptionMapper::toDto).collect(Collectors.toList());
    }
}
