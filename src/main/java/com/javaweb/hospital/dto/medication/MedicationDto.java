package com.javaweb.hospital.dto.medication;

public record MedicationDto(
    Integer id,
    String name,
    String description,
    String dosage) {
}
