package com.javaweb.hospital.controllers.medication;

import com.javaweb.hospital.controllers.medication.request.MedicationCreateReq;
import com.javaweb.hospital.controllers.medication.request.MedicationUpdateReq;
import com.javaweb.hospital.controllers.medication.response.MedicationRes;
import com.javaweb.hospital.dto.medication.MedicationDto;
import com.javaweb.hospital.services.medication.IMedicationService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "medications",
  produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE },
  consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
@RequiredArgsConstructor(onConstructor_ = { @Autowired })
public class MedicationController {

    private final IMedicationService medicationService;

    private MedicationRestMapper medicationMapper;

    @Autowired
    public void setMedicationMapper(@Qualifier("medicationRestMapperImpl") MedicationRestMapper medicationMapper) {
        this.medicationMapper = medicationMapper;
    }

    @PostMapping
    public ResponseEntity<MedicationRes> createMedication(@RequestBody @Valid @NotNull MedicationCreateReq req) {
        MedicationDto dto = this.medicationService.createMedication(this.medicationMapper.toDto(req));
        return ResponseEntity.ok(medicationMapper.toRes(dto));
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<MedicationRes> updateMedication(@PathVariable("id") Integer id, @RequestBody @Valid @NotNull MedicationUpdateReq req) {
        MedicationDto dto = this.medicationService.updateMedication(this.medicationMapper.toDto(req, id));
        return ResponseEntity.ok(medicationMapper.toRes(dto));
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<Void> deleteMedication(@PathVariable("id") @NotNull @Min(1) Integer id) {
        this.medicationService.deleteMedication(id);
        return ResponseEntity.noContent().build();
    }

}

