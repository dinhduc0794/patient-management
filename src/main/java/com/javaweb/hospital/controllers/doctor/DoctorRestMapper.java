package com.javaweb.hospital.controllers.doctor;

import com.javaweb.hospital.controllers.doctor.request.DoctorCreateReq;
import com.javaweb.hospital.controllers.doctor.request.DoctorUpdateReq;
import com.javaweb.hospital.controllers.doctor.response.DoctorRes;
import com.javaweb.hospital.dto.doctor.DoctorDto;
import org.mapstruct.*;

import java.util.UUID;

@Mapper(
    componentModel = MappingConstants.ComponentModel.SPRING,
    typeConversionPolicy = ReportingPolicy.WARN,
    unmappedTargetPolicy = ReportingPolicy.WARN,
    collectionMappingStrategy = CollectionMappingStrategy.TARGET_IMMUTABLE,
    injectionStrategy = InjectionStrategy.CONSTRUCTOR
)
public abstract class DoctorRestMapper {

    @Mapping(target = "id", ignore = true)
    public abstract DoctorDto toDto(DoctorCreateReq req);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "req.firstName", target = "firstName")
    @Mapping(source = "req.lastName", target = "lastName")
    @Mapping(source = "req.email", target = "email")
    @Mapping(source = "req.contactNumber", target = "contactNumber")
    @Mapping(source = "req.specialization", target = "specialization")
    public abstract DoctorDto toDto(DoctorUpdateReq req, UUID id);

    public abstract DoctorRes toRes(DoctorDto dto);
}
