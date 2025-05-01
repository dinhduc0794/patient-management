package com.javaweb.hospital.services.visit;

import com.javaweb.hospital.exception.ModelNotFoundException;
import com.javaweb.hospital.models.Doctor;
import com.javaweb.hospital.models.Patient;
import com.javaweb.hospital.models.Visit;
import com.javaweb.hospital.repositories.doctor.DoctorRepository;
import com.javaweb.hospital.repositories.patient.PatientRepository;
import com.javaweb.hospital.repositories.visit.VisitRepository;
import com.javaweb.hospital.services.dto.visit.VisitDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor(onConstructor_ = { @Autowired })
public class VisitService implements IVisitService {

    private final VisitRepository visitRepo;
    private final PatientRepository patientRepo;
    private final DoctorRepository doctorRepo;



    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, timeout = 2)
    public VisitDto createVisit(VisitDto dto) {
        Patient patient = this.patientRepo.findById(dto.patient().id())
            .orElseThrow(() -> ModelNotFoundException.of("Patient id", Patient.class.getSimpleName()));
        Doctor doctor = this.doctorRepo.findById(dto.doctor().id())
            .orElseThrow(() -> ModelNotFoundException.of("Doctor id", Doctor.class.getSimpleName()));
        Visit visit = this.
    }

    @Override
    public VisitDto updateVisit(VisitDto dto) {
        return null;
    }

    @Override
    public void deleteVisit(Long id) {

    }
}
