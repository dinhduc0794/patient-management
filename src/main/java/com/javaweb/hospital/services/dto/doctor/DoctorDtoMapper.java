package com.javaweb.hospital.services.dto.doctor;

import com.javaweb.hospital.models.Doctor;
import org.mapstruct.*;

@Mapper(
    componentModel = MappingConstants.ComponentModel.SPRING,
    typeConversionPolicy = ReportingPolicy.WARN,
    unmappedTargetPolicy = ReportingPolicy.WARN,
    collectionMappingStrategy = CollectionMappingStrategy.TARGET_IMMUTABLE,
    injectionStrategy = InjectionStrategy.CONSTRUCTOR
)
public abstract class DoctorDtoMapper {

    @Mapping(source = "firstName", target = "firstName")
    @Mapping(source = "lastName", target = "lastName")
    @Mapping(source = "specialization", target = "specialization")
    @Mapping(source = "contactNumber", target = "contactNumber")
    @Mapping(source = "email", target = "email")
    @Mapping(target = "id", ignore = true)
    public abstract Doctor toEntity(DoctorDto dto);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "firstName", target = "firstName")
    @Mapping(source = "lastName", target = "lastName")
    @Mapping(source = "specialization", target = "specialization")
    @Mapping(source = "contactNumber", target = "contactNumber")
    @Mapping(source = "email", target = "email")
    public abstract DoctorDto toDto(Doctor doctor);
}
