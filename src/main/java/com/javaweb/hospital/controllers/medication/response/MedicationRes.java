package com.javaweb.hospital.controllers.medication.response;

import java.io.Serializable;

public record MedicationRes(
    Integer id,
    String name,
    String description,
    String dosage
) implements Serializable {
}
