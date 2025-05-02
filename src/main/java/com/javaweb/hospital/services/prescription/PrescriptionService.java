package com.javaweb.hospital.services.prescription;

import com.javaweb.hospital.dto.prescription.PrescriptionDto;
import com.javaweb.hospital.dto.prescription.PrescriptionDtoMapper;
import com.javaweb.hospital.exception.ModelNotFoundException;
import com.javaweb.hospital.models.Medication;
import com.javaweb.hospital.models.Prescription;
import com.javaweb.hospital.models.Visit;
import com.javaweb.hospital.repositories.medication.MedicationRepository;
import com.javaweb.hospital.repositories.prescription.PrescriptionRepository;
import com.javaweb.hospital.repositories.visit.VisitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor(onConstructor_ = { @Autowired })
public class PrescriptionService implements IPrescriptionService{

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
}
