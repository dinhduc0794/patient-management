package com.javaweb.hospital.services.visit;

import com.javaweb.hospital.dto.visit.VisitDto;
import com.javaweb.hospital.dto.visit.VisitDtoMapper;
import com.javaweb.hospital.exception.ModelNotFoundException;
import com.javaweb.hospital.models.Doctor;
import com.javaweb.hospital.models.Patient;
import com.javaweb.hospital.models.Visit;
import com.javaweb.hospital.repositories.doctor.DoctorRepository;
import com.javaweb.hospital.repositories.patient.PatientRepository;
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

@Service
@RequiredArgsConstructor(onConstructor_ = { @Autowired })
public class VisitService implements IVisitService {

    private final VisitRepository visitRepo;
    private final PatientRepository patientRepo;
    private final DoctorRepository doctorRepo;

    private VisitDtoMapper visitMapper;

    @Autowired
    public void setVisitMapper(@Qualifier("visitDtoMapperImpl") VisitDtoMapper visitMapper) {
        this.visitMapper = visitMapper;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, timeout = 2)
    public VisitDto createVisit(VisitDto dto) {
        Patient patient = this.patientRepo.findById(dto.patient().id())
            .orElseThrow(() -> ModelNotFoundException.of("Patient id", Patient.class.getSimpleName()));
        Doctor doctor = this.doctorRepo.findById(dto.doctor().id())
            .orElseThrow(() -> ModelNotFoundException.of("Doctor id", Doctor.class.getSimpleName()));
        Visit visit = this.visitMapper.toEntity(dto);
        visit.setPatient(patient);
        visit.setDoctor(doctor);
        Visit visitSaved = this.visitRepo.save(visit);
        return this.visitMapper.toDto(visitSaved);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, timeout = 2)
    public VisitDto updateVisit(VisitDto dto) {
        Visit visit = this.visitRepo.findById(dto.id())
            .orElseThrow(() -> ModelNotFoundException.of("Visit id", Visit.class.getSimpleName()));
        visit.setDiagnosis(dto.diagnosis());
        visit.setVisitTime(dto.visitTime());
        visit.setSymptoms(dto.symptoms());
        visit.setNotes(dto.notes());
        return this.visitMapper.toDto(visitRepo.save(visit));
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, timeout = 2)
    public void deleteVisit(Long id) {
        Visit visit = this.visitRepo.findById(id)
            .orElseThrow(() -> ModelNotFoundException.of("Visit id", Visit.class.getSimpleName()));
        this.visitRepo.delete(visit);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, timeout = 2)
    public void deleteVisit(UUID patientId, Long id) {
        Patient patient = this.patientRepo.findById(patientId)
            .orElseThrow(() -> ModelNotFoundException.of("Patient id", Patient.class.getSimpleName()));
        Visit visit = this.visitRepo.findById(id)
            .orElseThrow(() -> ModelNotFoundException.of("Visit id", Visit.class.getSimpleName()));
        this.visitRepo.delete(visit);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, timeout = 2)
    public List<VisitDto> getVisits(UUID patientId, Integer page, Integer limit) {
        Patient patient = this.patientRepo.findById(patientId)
            .orElseThrow(() -> ModelNotFoundException.of("Patient id", Patient.class.getSimpleName()));
        Page<Visit> visits = this.visitRepo.findAllByPatient(patient, PageRequest.of(page, limit, Sort.by("visitTime").descending()));
        return visits.stream().map(this.visitMapper::toDto).toList();
    }
}
