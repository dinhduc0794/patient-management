package com.javaweb.hospital.services.doctor;

import com.javaweb.hospital.services.dto.doctor.DoctorDto;

import java.util.UUID;

public interface IDoctorService {

    DoctorDto createDoctor(DoctorDto dto);
    DoctorDto updateDoctor(DoctorDto dto);
    void deleteDoctor(UUID id);
}
