package com.javaweb.hospital.dto.prescription;

import com.javaweb.hospital.dto.patient.PatientDtoMapper;
import com.javaweb.hospital.dto.visit.VisitDtoMapper;
import com.javaweb.hospital.models.Prescription;
import org.mapstruct.*;

@Mapper(
    componentModel = MappingConstants.ComponentModel.SPRING,
    typeConversionPolicy = ReportingPolicy.WARN,
    unmappedTargetPolicy = ReportingPolicy.WARN,
    collectionMappingStrategy = CollectionMappingStrategy.TARGET_IMMUTABLE,
    injectionStrategy = InjectionStrategy.CONSTRUCTOR,
    uses = { PatientDtoMapper.class, VisitDtoMapper.class }
)
public abstract class PrescriptionDtoMapper {

    @Mapping(source = "quantity", target = "quantity")
    @Mapping(source = "instructions", target = "instructions")
    @Mapping(source = "duration", target = "duration")
    @Mapping(target = "patientVisit", ignore = true)
    @Mapping(target = "medication", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    public abstract Prescription toEntity(PrescriptionDto dto);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "quantity", target = "quantity")
    @Mapping(source = "instructions", target = "instructions")
    @Mapping(source = "duration", target = "duration")
    @Mapping(source = "patientVisit", target = "patientVisit")
    @Mapping(source = "medication", target = "medication")
    public abstract PrescriptionDto toDto(Prescription entity);

}
