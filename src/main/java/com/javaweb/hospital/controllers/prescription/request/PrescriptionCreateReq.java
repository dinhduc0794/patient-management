package com.javaweb.hospital.controllers.prescription.request;

import java.io.Serializable;

public record PrescriptionCreateReq(
  Integer quantity,
  String instructions,
  Long duration,
  Integer medicationId
) implements Serializable {
}
