package com.javaweb.hospital.controllers.visit;

import com.javaweb.hospital.controllers.doctor.DoctorRestMapper;
import com.javaweb.hospital.controllers.patient.PatientRestMapper;
import com.javaweb.hospital.controllers.visit.request.VisitCreateReq;
import com.javaweb.hospital.controllers.visit.request.VisitUpdateReq;
import com.javaweb.hospital.controllers.visit.response.VisitRes;
import com.javaweb.hospital.dto.visit.VisitDto;
import org.mapstruct.*;

import java.util.UUID;

@Mapper(
    componentModel = MappingConstants.ComponentModel.SPRING,
    typeConversionPolicy = ReportingPolicy.WARN,
    unmappedTargetPolicy = ReportingPolicy.WARN,
    collectionMappingStrategy = CollectionMappingStrategy.TARGET_IMMUTABLE,
    injectionStrategy = InjectionStrategy.CONSTRUCTOR,
    uses = { PatientRestMapper.class, DoctorRestMapper.class }
)
public abstract class VisitRestMapper {

    @Mapping(source = "req.visitTime", target = "visitTime")
    @Mapping(source = "req.symptoms", target = "symptoms")
    @Mapping(source = "req.treatment", target = "treatment")
    @Mapping(source = "req.diagnosis", target = "diagnosis")
    @Mapping(source = "req.notes", target = "notes")
    @Mapping(source = "patientId", target = "patient.id")
    @Mapping(source = "req.doctorId", target = "doctor.id")
    @Mapping(target = "id", ignore = true)
    public abstract VisitDto toDto(VisitCreateReq req, UUID patientId);

    @Mapping(source = "req.visitTime", target = "visitTime")
    @Mapping(source = "req.symptoms", target = "symptoms")
    @Mapping(source = "req.treatment", target = "treatment")
    @Mapping(source = "req.diagnosis", target = "diagnosis")
    @Mapping(source = "req.notes", target = "notes")
    @Mapping(source = "visitId", target = "id")
    @Mapping(source = "patientId", target = "patient.id")
    @Mapping(target = "doctor", ignore = true)
    public abstract VisitDto toDto(VisitUpdateReq req, UUID patientId, Long visitId);

    @Mapping(source = "visitTime", target = "visitTime")
    @Mapping(source = "symptoms", target = "symptoms")
    @Mapping(source = "treatment", target = "treatment")
    @Mapping(source = "diagnosis", target = "diagnosis")
    @Mapping(source = "notes", target = "notes")
    @Mapping(source = "patient", target = "patient")
    @Mapping(source = "doctor", target = "doctor")
    public abstract VisitRes toRes(VisitDto dto);
}
