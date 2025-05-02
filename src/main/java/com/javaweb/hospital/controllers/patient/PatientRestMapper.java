package com.javaweb.hospital.controllers.patient;

import com.javaweb.hospital.controllers.patient.request.PatientCreateReq;
import com.javaweb.hospital.controllers.patient.request.PatientUpdateReq;
import com.javaweb.hospital.controllers.patient.response.PatientRes;
import com.javaweb.hospital.dto.patient.PatientDto;
import org.mapstruct.*;

import java.util.UUID;

@Mapper(
    componentModel = MappingConstants.ComponentModel.SPRING,
    typeConversionPolicy = ReportingPolicy.WARN,
    unmappedTargetPolicy = ReportingPolicy.WARN,
    collectionMappingStrategy = CollectionMappingStrategy.TARGET_IMMUTABLE,
    injectionStrategy = InjectionStrategy.CONSTRUCTOR
)
public abstract class PatientRestMapper {

    @Mapping(target = "id", ignore = true)
    public abstract PatientDto toDto(PatientCreateReq req);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "req.firstName", target = "firstName")
    @Mapping(source = "req.lastName", target = "lastName")
    @Mapping(source = "req.email", target = "email")
    @Mapping(source = "req.gender", target = "gender")
    @Mapping(source = "req.contactNumber", target = "contactNumber")
    @Mapping(source = "req.address", target = "address")
    public abstract PatientDto toDto(PatientUpdateReq req, UUID id);

    @Named("uuidToPatientDto")
    @Mapping(source = "id", target = "id")
    @Mapping(target = "firstName", ignore = true)
    @Mapping(target = "lastName", ignore = true)
    @Mapping(target = "address", ignore = true)
    @Mapping(target = "contactNumber", ignore = true)
    @Mapping(target = "dateOfBirth", ignore = true)
    @Mapping(target = "email", ignore = true)
    @Mapping(target = "gender", ignore = true)
    public abstract PatientDto toDto(UUID id);

    public abstract PatientRes toRes(PatientDto dto);

}
