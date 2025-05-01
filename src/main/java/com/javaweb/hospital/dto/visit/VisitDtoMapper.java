package com.javaweb.hospital.dto.visit;

import com.javaweb.hospital.models.Visit;
import org.mapstruct.*;

@Mapper(
    componentModel = MappingConstants.ComponentModel.SPRING,
    typeConversionPolicy = ReportingPolicy.WARN,
    unmappedTargetPolicy = ReportingPolicy.WARN,
    collectionMappingStrategy = CollectionMappingStrategy.TARGET_IMMUTABLE,
    injectionStrategy = InjectionStrategy.CONSTRUCTOR
)
public abstract class VisitDtoMapper {
    
    public abstract Visit toEntity(VisitDto dto);
}
