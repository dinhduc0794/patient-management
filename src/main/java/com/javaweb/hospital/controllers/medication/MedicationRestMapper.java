package com.javaweb.hospital.controllers.medication;

import com.javaweb.hospital.controllers.medication.request.MedicationCreateReq;
import com.javaweb.hospital.controllers.medication.request.MedicationUpdateReq;
import com.javaweb.hospital.controllers.medication.response.MedicationRes;
import com.javaweb.hospital.dto.medication.MedicationDto;
import org.mapstruct.*;

@Mapper(
    componentModel = MappingConstants.ComponentModel.SPRING,
    typeConversionPolicy = ReportingPolicy.WARN,
    unmappedTargetPolicy = ReportingPolicy.WARN,
    collectionMappingStrategy = CollectionMappingStrategy.TARGET_IMMUTABLE,
    injectionStrategy = InjectionStrategy.CONSTRUCTOR
)
public abstract class MedicationRestMapper {

    @Mapping(target = "id", ignore = true)
    public abstract MedicationDto toDto(MedicationCreateReq req);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "req.name", target = "name")
    @Mapping(source = "req.dosage", target = "dosage")
    @Mapping(source = "req.description", target = "description")
    public abstract MedicationDto toDto(MedicationUpdateReq req, Integer id);


    public abstract MedicationRes toRes(MedicationDto dto);
}
