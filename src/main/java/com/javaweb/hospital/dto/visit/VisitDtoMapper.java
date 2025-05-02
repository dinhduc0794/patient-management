package com.javaweb.hospital.dto.visit;

import com.javaweb.hospital.dto.patient.PatientDtoMapper;
import com.javaweb.hospital.models.Visit;
import org.mapstruct.*;

@Mapper(
    componentModel = MappingConstants.ComponentModel.SPRING,
    typeConversionPolicy = ReportingPolicy.WARN,
    unmappedTargetPolicy = ReportingPolicy.WARN,
    collectionMappingStrategy = CollectionMappingStrategy.TARGET_IMMUTABLE,
    injectionStrategy = InjectionStrategy.CONSTRUCTOR,
    uses = { PatientDtoMapper.class, VisitDtoMapper.class }
)
public abstract class VisitDtoMapper {

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "patient", ignore = true)
    @Mapping(target = "doctor", ignore = true)
    public abstract Visit toEntity(VisitDto dto);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "visitTime", target = "visitTime")
    @Mapping(source = "symptoms", target = "symptoms")
    @Mapping(source = "diagnosis", target = "diagnosis")
    @Mapping(source = "treatment", target = "treatment")
    @Mapping(source = "notes", target = "notes")
    @Mapping(source = "patient", target = "patient")
    @Mapping(source = "doctor", target = "doctor")
    public abstract VisitDto toDto(Visit entity);
}
