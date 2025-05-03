package com.javaweb.hospital.services.doctor;

import com.javaweb.hospital.exception.ModelNotFoundException;
import com.javaweb.hospital.models.Doctor;
import com.javaweb.hospital.repositories.doctor.DoctorRepository;
import com.javaweb.hospital.dto.doctor.DoctorDto;
import com.javaweb.hospital.dto.doctor.DoctorDtoMapper;
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
        Doctor doctor = this.doctorRepo.findById(id)
            .orElseThrow(() -> ModelNotFoundException.of("Doctor id", Doctor.class.getSimpleName()));
        this.doctorRepo.delete(doctor);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, timeout = 2)
    public DoctorDto getDoctor(UUID id) {
        Doctor doctor = this.doctorRepo.findById(id)
            .orElseThrow(() -> ModelNotFoundException.of("Doctor id", Doctor.class.getSimpleName()));
        return this.doctorMapper.toDto(doctor);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE, timeout = 3)
    public List<DoctorDto> getDoctors(Integer page, Integer limit) {
        Page<Doctor> doctors = this.doctorRepo.findAll(PageRequest.of(page, limit, Sort.by("firstName", "lastName").ascending()));
        return doctors.stream().map(doctorMapper::toDto).collect(Collectors.toList());
    }
}
