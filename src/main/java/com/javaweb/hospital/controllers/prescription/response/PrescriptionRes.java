package com.javaweb.hospital.controllers.prescription.response;

import com.javaweb.hospital.controllers.medication.response.MedicationRes;
import com.javaweb.hospital.controllers.visit.response.VisitRes;

import java.io.Serializable;

public record PrescriptionRes(
  Long id,
  Integer quantity,
  String instructions,
  Long duration,
  VisitRes patientVisit,
  MedicationRes medication
) implements Serializable {

}
