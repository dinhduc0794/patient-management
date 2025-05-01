package com.javaweb.hospital.services.visit;

import com.javaweb.hospital.dto.visit.VisitDto;

public interface IVisitService {

    VisitDto createVisit(VisitDto dto);
    VisitDto updateVisit(VisitDto dto);
    void deleteVisit(Long id);
}
