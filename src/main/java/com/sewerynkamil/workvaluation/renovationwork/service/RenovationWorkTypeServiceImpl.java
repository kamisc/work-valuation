package com.sewerynkamil.workvaluation.renovationwork.service;

import com.sewerynkamil.workvaluation.renovationwork.domain.RenovationWorkType;
import com.sewerynkamil.workvaluation.renovationwork.dto.RenovationWorkTypeDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class RenovationWorkTypeServiceImpl implements RenovationWorkTypeService {
    @Override
    public List<RenovationWorkTypeDTO> getAllRenovationWorkTypes() {
        return null;
    }

    @Override
    public RenovationWorkTypeDTO getRenovationWorkTypeById(Long id) {
        return null;
    }

    @Override
    public RenovationWorkType addRenovationWorkType(RenovationWorkTypeDTO renovationWorkTypeDTO) {
        return null;
    }

    @Override
    public RenovationWorkType updateRenovationWorkType(RenovationWorkTypeDTO renovationWorkTypeDTO, Long id) {
        return null;
    }

    @Override
    public void deleteRenovationWorkType(Long id) {

    }
}
