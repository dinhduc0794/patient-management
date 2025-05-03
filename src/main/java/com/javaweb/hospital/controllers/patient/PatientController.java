package com.javaweb.hospital.controllers.patient;

import com.javaweb.hospital.controllers.patient.request.PatientCreateReq;
import com.javaweb.hospital.controllers.patient.request.PatientUpdateReq;
import com.javaweb.hospital.controllers.patient.response.PatientRes;
import com.javaweb.hospital.controllers.prescription.PrescriptionRestMapper;
import com.javaweb.hospital.controllers.prescription.request.PrescriptionCreateReq;
import com.javaweb.hospital.controllers.prescription.request.PrescriptionUpdateReq;
import com.javaweb.hospital.controllers.prescription.response.PrescriptionRes;
import com.javaweb.hospital.controllers.visit.VisitRestMapper;
import com.javaweb.hospital.controllers.visit.request.VisitCreateReq;
import com.javaweb.hospital.controllers.visit.request.VisitUpdateReq;
import com.javaweb.hospital.controllers.visit.response.VisitRes;
import com.javaweb.hospital.dto.prescription.PrescriptionDto;
import com.javaweb.hospital.dto.visit.VisitDto;
import com.javaweb.hospital.services.patient.IPatientService;
import com.javaweb.hospital.dto.patient.PatientDto;
import com.javaweb.hospital.services.prescription.IPrescriptionService;
import com.javaweb.hospital.services.visit.IVisitService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "patients",
  produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE },
  consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
@RequiredArgsConstructor(onConstructor_ = { @Autowired })
public class PatientController {

    private final IPatientService patientService;
    private final IVisitService visitService;
    private final IPrescriptionService prescriptionService;


    private PatientRestMapper patientMapper;
    private VisitRestMapper visitMapper;
    private PrescriptionRestMapper prescriptionMapper;

    @Autowired
    public void setPatientMapper(@Qualifier("patientRestMapperImpl") PatientRestMapper patientMapper) {
        this.patientMapper = patientMapper;
    }

    @Autowired
    public void setVisitMapper(@Qualifier("visitRestMapperImpl") VisitRestMapper visitMapper) {
        this.visitMapper = visitMapper;
    }

    @Autowired
    public void setPrescriptionMapper(@Qualifier("prescriptionRestMapperImpl") PrescriptionRestMapper prescriptionMapper) {
        this.prescriptionMapper = prescriptionMapper;
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

    @PostMapping(path = "{id}/visits")
    public ResponseEntity<VisitRes> createVisit(@PathVariable("id") @NotNull UUID id, @RequestBody @Valid @NotNull VisitCreateReq req) {
        VisitDto dto = this.visitService.createVisit(this.visitMapper.toDto(req, id));
        return ResponseEntity.ok(this.visitMapper.toRes(dto));
    }

    @PutMapping(path = "{patient-id}/visits/{visit-id}")
    public ResponseEntity<VisitRes> updateVisit(@PathVariable("patient-id") @Valid @NotNull UUID patientId,
                                                @PathVariable("visit-id") @Valid @NotNull @Min(1) Long visitId,
                                                @RequestBody @Valid @NotNull VisitUpdateReq req) {
        VisitDto dto = this.visitService.updateVisit(this.visitMapper.toDto(req, patientId, visitId));
        return ResponseEntity.ok(this.visitMapper.toRes(dto));
    }

    @DeleteMapping(path = "{patient-id}/visits/{visit-id}")
    public ResponseEntity<Void> deleteVisit(@PathVariable("patient-id") @Valid @NotNull UUID patientId,
                                            @PathVariable("visit-id") @Valid @NotNull @Min(1) Long visitId) {
        this.visitService.deleteVisit(patientId, visitId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("{patient-id}/visits/{visit-id}/prescriptions")
    public ResponseEntity<PrescriptionRes> createPrescription(@PathVariable("patient-id") @Valid @NotNull UUID patientId,
                                                              @PathVariable("visit-id") @Valid @Min(1) Long visitId,
                                                              @RequestBody @Valid @NotNull PrescriptionCreateReq req) {
        PrescriptionDto dto = this.prescriptionService.createPrescription(this.prescriptionMapper.toDto(req, patientId, visitId));
        return ResponseEntity.ok(this.prescriptionMapper.toRes(dto));
    }

    @PutMapping("{patient-id}/visits/{visit-id}/prescriptions/{prescription-id}")
    public ResponseEntity<PrescriptionRes> updatePrescription(@PathVariable("patient-id") @Valid @NotNull UUID patientId,
                                                              @PathVariable("visit-id") @Valid @Min(1) Long visitId,
                                                              @PathVariable("prescription-id") @Valid @NotNull @Min(1) Long prescriptionId,
                                                              @RequestBody @Valid @NotNull PrescriptionUpdateReq req) {
        PrescriptionDto dto = this.prescriptionService.updatePrescription(this.prescriptionMapper.toDto(req, patientId, visitId, prescriptionId));
        return ResponseEntity.ok(this.prescriptionMapper.toRes(dto));
    }

    @DeleteMapping("{patient-id}/visits/{visit-id}/prescriptions/{prescription-id}")
    public ResponseEntity<Void> deletePrescription(@PathVariable("patient-id") @Valid @NotNull UUID patientId,
                                                   @PathVariable("visit-id") @Valid @Min(1) Long visitId,
                                                   @PathVariable("prescription-id") @Valid @NotNull @Min(1) Long prescriptionId) {
        this.prescriptionService.deletePrescriptions(patientId, visitId, prescriptionId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("page/{page}/limit/{limit}")
    public ResponseEntity<List<PatientRes>> getPatients(@PathVariable("page") @Valid @Min(1) @DefaultValue("1") Integer page,
                                                        @PathVariable("limit") @Valid @Min(25) @Max(100) @DefaultValue("25") Integer limit) {
        List<PatientDto> dtos = this.patientService.getPatients(page, limit);
        return ResponseEntity.ok(dtos.stream().map(this.patientMapper::toRes).toList());
    }

    @GetMapping(path = "{id}/visits/page/{page}/limit/{limit}")
    public ResponseEntity<List<VisitRes>> getVisits(@PathVariable("id") @Valid @NotNull UUID id,
                                                    @PathVariable("page") @Valid @Min(1) @DefaultValue("1") Integer page,
                                                    @PathVariable("limit") @Valid @Min(25) @Max(100) @DefaultValue("25") Integer limit) {
        List<VisitDto> dtos = this.visitService.getVisits(id, page, limit);
        return ResponseEntity.ok(dtos.stream().map(this.visitMapper::toRes).toList());
    }

    @GetMapping(path = "{patient-id}/visits/{visit-id}/page/{page}/limit/{limit}")
    public ResponseEntity<List<PrescriptionRes>> getPrescriptions(@PathVariable("patient-id") @Valid @NotNull UUID patientId,
                                                                  @PathVariable("visit-id") @Valid @Min(1) @NotNull Long visitId,
                                                                  @PathVariable("page") @Valid @Min(1) @DefaultValue("1") Integer page,
                                                                  @PathVariable("limit") @Valid @Min(25) @Max(100) @DefaultValue("25") Integer limit) {
        List<PrescriptionDto> dtos = this.prescriptionService.getPrescriptions(patientId, visitId, page, limit);
        return ResponseEntity.ok(dtos.stream().map(this.prescriptionMapper::toRes).toList());
    }

    @GetMapping(path = "{id}/page/{page}/limit/{limit}")
    public ResponseEntity<List<PrescriptionRes>> getPrescriptions(@PathVariable("id") @Valid @NotNull UUID id,
                                                                  @PathVariable("page") @Valid @Min(1) @DefaultValue("1") Integer page,
                                                                  @PathVariable("limit") @Valid @Min(25) @Max(100) @DefaultValue("25") Integer limit) {
        List<PrescriptionDto> dtos = this.prescriptionService.getPrescriptions(id, page, limit);
        return ResponseEntity.ok(dtos.stream().map(this.prescriptionMapper::toRes).toList());
    }
}
