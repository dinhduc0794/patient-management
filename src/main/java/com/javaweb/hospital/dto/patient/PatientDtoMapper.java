package com.javaweb.hospital.dto.patient;

import com.javaweb.hospital.models.Patient;
import org.mapstruct.*;

@Mapper(
  componentModel = MappingConstants.ComponentModel.SPRING,
  typeConversionPolicy = ReportingPolicy.WARN,
  unmappedTargetPolicy = ReportingPolicy.WARN,
  collectionMappingStrategy = CollectionMappingStrategy.TARGET_IMMUTABLE,
  injectionStrategy = InjectionStrategy.CONSTRUCTOR
)
public abstract class PatientDtoMapper {

  @Mapping(source = "firstName", target = "firstName")
  @Mapping(source = "lastName", target = "lastName")
  @Mapping(source = "dateOfBirth", target = "dateOfBirth")
  @Mapping(source = "email", target = "email")
  @Mapping(source = "contactNumber", target = "contactNumber")
  @Mapping(source = "gender", target = "gender")
  @Mapping(source = "address", target = "address")
  @Mapping(target = "id", ignore = true)
  @Mapping(target = "visits", ignore = true)
  public abstract Patient toEntity(PatientDto dto);

  @Mapping(source = "id", target = "id")
  @Mapping(source = "firstName", target = "firstName")
  @Mapping(source = "lastName", target = "lastName")
  @Mapping(source = "dateOfBirth", target = "dateOfBirth")
  @Mapping(source = "email", target = "email")
  @Mapping(source = "contactNumber", target = "contactNumber")
  @Mapping(source = "gender", target = "gender")
  public abstract PatientDto toDto(Patient entity);
}
