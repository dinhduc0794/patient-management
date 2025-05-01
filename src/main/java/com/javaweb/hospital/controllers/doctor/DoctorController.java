package com.javaweb.hospital.controllers.doctor;

import com.javaweb.hospital.controllers.doctor.request.DoctorCreateReq;
import com.javaweb.hospital.controllers.doctor.request.DoctorUpdateReq;
import com.javaweb.hospital.controllers.doctor.response.DoctorRes;
import com.javaweb.hospital.services.doctor.IDoctorService;
import com.javaweb.hospital.dto.doctor.DoctorDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(path = "doctors",
  produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE },
  consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DoctorController {

    private final IDoctorService doctorService;

    private DoctorRestMapper doctorMapper;

    @Autowired
    public void setDoctorMapper(@Qualifier("doctorRestMapperImpl") DoctorRestMapper doctorMapper) {
        this.doctorMapper = doctorMapper;
    }

    @PostMapping
    public ResponseEntity<DoctorRes> createDoctor(@RequestBody @Valid DoctorCreateReq req) {
        DoctorDto dto = this.doctorService.createDoctor(this.doctorMapper.toDto(req));
        return ResponseEntity.ok(this.doctorMapper.toRes(dto));
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<DoctorRes> updateDoctor(@PathVariable("id") UUID id, @RequestBody @Valid DoctorUpdateReq req) {
        DoctorDto dto = this.doctorService.updateDoctor(this.doctorMapper.toDto(req, id));
        return ResponseEntity.ok(this.doctorMapper.toRes(dto));
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<Void> deleteDoctor(@PathVariable("id") UUID id) {
        this.doctorService.deleteDoctor(id);
        return ResponseEntity.noContent().build();
    }
}
