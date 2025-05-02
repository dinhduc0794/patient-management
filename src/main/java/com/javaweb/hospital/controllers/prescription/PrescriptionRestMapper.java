package com.javaweb.hospital.controllers.prescription;

import com.javaweb.hospital.controllers.doctor.DoctorRestMapper;
import com.javaweb.hospital.controllers.medication.MedicationRestMapper;
import com.javaweb.hospital.controllers.patient.PatientRestMapper;
import com.javaweb.hospital.controllers.prescription.request.PrescriptionCreateReq;
import com.javaweb.hospital.controllers.prescription.response.PrescriptionRes;
import com.javaweb.hospital.dto.prescription.PrescriptionDto;
import com.javaweb.hospital.dto.visit.VisitDto;
import org.mapstruct.*;

import java.util.UUID;

@Mapper(
    componentModel = MappingConstants.ComponentModel.SPRING,
    typeConversionPolicy = ReportingPolicy.WARN,
    unmappedTargetPolicy = ReportingPolicy.WARN,
    collectionMappingStrategy = CollectionMappingStrategy.TARGET_IMMUTABLE,
    injectionStrategy = InjectionStrategy.CONSTRUCTOR,
    uses = { PatientRestMapper.class, MedicationRestMapper.class, DoctorRestMapper.class }
)
public abstract class PrescriptionRestMapper {

    @Mapping(source = "req.quantity", target = "quantity")
    @Mapping(source = "req.instructions", target = "instructions")
    @Mapping(source = "req.duration", target = "duration")
    @Mapping(source = "req.medicationId", target = "medication.id")
    @Mapping(target = "patientVisit", ignore = true)
    @Mapping(target = "id", ignore = true)
    public abstract PrescriptionDto toDto(PrescriptionCreateReq req, UUID patientId, Long visitId);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "quantity", target = "quantity")
    @Mapping(source = "instructions", target = "instructions")
    @Mapping(source = "duration", target = "duration")
    @Mapping(source = "patientVisit", target = "patientVisit")
    @Mapping(source = "medication", target = "medication")
    public abstract PrescriptionRes toRes(PrescriptionDto dto);

    @AfterMapping
    protected void setPatientVisit(@MappingTarget PrescriptionDto dto, UUID patientId, Long visitId) {
        dto.setPatientVisit(new VisitDto(visitId, patientId));
    }
}
