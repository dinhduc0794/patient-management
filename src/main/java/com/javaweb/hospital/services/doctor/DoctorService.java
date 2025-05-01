package com.javaweb.hospital.services.doctor;

import com.javaweb.hospital.exception.ModelNotFoundException;
import com.javaweb.hospital.models.Doctor;
import com.javaweb.hospital.repositories.doctor.DoctorRepository;
import com.javaweb.hospital.dto.doctor.DoctorDto;
import com.javaweb.hospital.dto.doctor.DoctorDtoMapper;
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
public class DoctorService implements IDoctorService {

    private final DoctorRepository doctorRepo;

    private DoctorDtoMapper doctorMapper;

    @Autowired
    public void setDoctorMapper(@Qualifier("doctorDtoMapperImpl") DoctorDtoMapper doctorMapper) {
        this.doctorMapper = doctorMapper;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE, timeout = 3)
    public DoctorDto createDoctor(DoctorDto dto) {
        Doctor doctor = this.doctorMapper.toEntity(dto);
        Doctor doctorSaved = this.doctorRepo.save(doctor);
        return this.doctorMapper.toDto(doctorSaved);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, timeout = 2)
    public DoctorDto updateDoctor(DoctorDto dto) {
        Doctor doctor = this.doctorRepo.findById(dto.id())
            .orElseThrow(() -> ModelNotFoundException.of("Doctor id", Doctor.class.getSimpleName()));
        doctor.setEmail(dto.email());
        doctor.setSpecialization(dto.specialization());
        doctor.setContactNumber(dto.contactNumber());
        doctor.setFirstName(dto.firstName());
        doctor.setLastName(dto.lastName());
        Doctor doctorSaved = this.doctorRepo.save(doctor);
        return this.doctorMapper.toDto(doctorSaved);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE, timeout = 3)
    public void deleteDoctor(UUID id) {
        this.doctorRepo.deleteById(id);
    }
}
