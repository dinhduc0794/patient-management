package com.javaweb.hospital.services.medication;

import com.javaweb.hospital.dto.medication.MedicationDto;
import com.javaweb.hospital.dto.medication.MedicationDtoMapper;
import com.javaweb.hospital.exception.ModelNotFoundException;
import com.javaweb.hospital.models.Medication;
import com.javaweb.hospital.repositories.medication.MedicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor(onConstructor_ = { @Autowired })
public class MedicationService implements IMedicationService {

    private final MedicationRepository medicationRepo;

    private MedicationDtoMapper medicationMapper;

    @Autowired
    public void setMedicationMapper(@Qualifier("medicationDtoMapperImpl") MedicationDtoMapper medicationMapper) {
        this.medicationMapper = medicationMapper;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE, timeout = 3)
    public MedicationDto createMedication(MedicationDto dto) {
        Medication medication = this.medicationMapper.toEntity(dto);
        Medication medicationSaved = this.medicationRepo.save(medication);
        return medicationMapper.toDto(medicationSaved);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE, timeout = 3)
    public MedicationDto updateMedication(MedicationDto dto) {
        Medication medication = this.medicationRepo.findById(dto.id())
            .orElseThrow(() -> ModelNotFoundException.of("Medication id", Medication.class.getSimpleName()));
        medication.setName(dto.name());
        medication.setDescription(dto.description());
        medication.setDosage(dto.dosage());
        Medication medicationSaved = this.medicationRepo.save(medication);
        return medicationMapper.toDto(medicationSaved);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, timeout = 2)
    public void deleteMedication(Integer id) {
        Medication medication = this.medicationRepo.findById(id)
            .orElseThrow(() -> ModelNotFoundException.of("Medication id", Medication.class.getSimpleName()));
        this.medicationRepo.delete(medication);
    }
}
