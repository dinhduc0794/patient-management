package com.javaweb.hospital.repositories.prescription;

import com.javaweb.hospital.models.Patient;
import com.javaweb.hospital.models.Prescription;
import com.javaweb.hospital.models.Visit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PrescriptionRepository extends JpaRepository<Prescription, Long> {
    Page<Prescription> getAllByPatientVisit(Visit patientVisit, Pageable pageable);

    @Query(value = "SELECT pr FROM Prescription pr WHERE pr.patientVisit.patient = :patient")
    Page<Prescription> findAllByPatient(Patient patient, Pageable pageable);
}
