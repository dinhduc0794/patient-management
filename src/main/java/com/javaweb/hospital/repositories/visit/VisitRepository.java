package com.javaweb.hospital.repositories.visit;

import com.javaweb.hospital.models.Patient;
import com.javaweb.hospital.models.Visit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VisitRepository extends JpaRepository<Visit, Long> {

    Page<Visit> findAllByPatient(Patient patient, Pageable pageable);
}
