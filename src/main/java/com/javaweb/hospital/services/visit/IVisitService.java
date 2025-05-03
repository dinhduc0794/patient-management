package com.javaweb.hospital.services.visit;

import com.javaweb.hospital.dto.visit.VisitDto;

import java.util.List;
import java.util.UUID;

public interface IVisitService {

    VisitDto createVisit(VisitDto dto);
    VisitDto updateVisit(VisitDto dto);
    void deleteVisit(Long id);
    void deleteVisit(UUID patientId, Long id);

    List<VisitDto> getVisits(UUID patientId, Integer page, Integer limit);
}
