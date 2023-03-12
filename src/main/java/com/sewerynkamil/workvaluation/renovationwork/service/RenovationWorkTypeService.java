package com.sewerynkamil.workvaluation.renovationwork.service;

import com.sewerynkamil.workvaluation.renovationwork.domain.RenovationWorkType;
import com.sewerynkamil.workvaluation.renovationwork.dto.RenovationWorkTypeDTO;

import java.util.List;

public interface RenovationWorkTypeService {
    List<RenovationWorkTypeDTO> getAllRenovationWorkTypes();

    RenovationWorkTypeDTO getRenovationWorkTypeById(Long id);

    RenovationWorkType addRenovationWorkType(RenovationWorkTypeDTO renovationWorkTypeDTO);

    RenovationWorkType updateRenovationWorkType(RenovationWorkTypeDTO renovationWorkTypeDTO, Long id);

    void deleteRenovationWorkType(Long id);
}
