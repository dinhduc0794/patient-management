package com.javaweb.hospital.services.doctor;

import com.javaweb.hospital.dto.doctor.DoctorDto;

import java.util.List;
import java.util.UUID;

public interface IDoctorService {

    DoctorDto createDoctor(DoctorDto dto);
    DoctorDto updateDoctor(DoctorDto dto);
    void deleteDoctor(UUID id);
    List<DoctorDto> getDoctors(Integer page, Integer limit);
    DoctorDto getDoctor(UUID id);
}
