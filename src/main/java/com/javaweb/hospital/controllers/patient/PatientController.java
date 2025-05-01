package com.javaweb.hospital.controllers.patient;

import com.javaweb.hospital.controllers.patient.request.PatientCreateReq;
import com.javaweb.hospital.controllers.patient.request.PatientUpdateReq;
import com.javaweb.hospital.controllers.patient.response.PatientRes;
import com.javaweb.hospital.services.patient.IPatientService;
import com.javaweb.hospital.dto.patient.PatientDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(path = "patients",
  produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE },
  consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
@RequiredArgsConstructor(onConstructor_ = { @Autowired })
public class PatientController {

    private final IPatientService patientService;

    private PatientRestMapper patientMapper;

    @Autowired
    public void setPatientMapper(@Qualifier("patientRestMapperImpl") PatientRestMapper patientMapper) {
        this.patientMapper = patientMapper;
    }

    @PostMapping
    public ResponseEntity<PatientRes> createPatient(@RequestBody @Valid PatientCreateReq req) {
        PatientDto dto = this.patientService.createPatient(this.patientMapper.toDto(req));
        return ResponseEntity.ok(patientMapper.toRes(dto));
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<PatientRes> updatePatient(@PathVariable("id") UUID id, @RequestBody @Valid PatientUpdateReq req) {
        PatientDto dto = this.patientService.updatePatient(this.patientMapper.toDto(req, id));
        return ResponseEntity.ok(patientMapper.toRes(dto));
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable("id") UUID id) {
        this.patientService.deletePatient(id);
        return ResponseEntity.noContent().build();
    }

}
