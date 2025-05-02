package com.javaweb.hospital.controllers.visit.request;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

public record VisitCreateReq(
    @NotNull @FutureOrPresent LocalDateTime visitTime,
    @NotBlank @Size(max = 1022) String symptoms,
    @Size(max = 1022) String treatment,
    @Size(max = 1022) String diagnosis,
    @Size(max = 1022) String notes,
    @NotNull UUID doctorId
) implements Serializable {
}
