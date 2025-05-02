package com.javaweb.hospital.controllers.medication.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

public record MedicationUpdateReq(
    @NotBlank @Size(min = 1, max = 255) String name,

    String description,

    String dosage
) implements Serializable {

}
