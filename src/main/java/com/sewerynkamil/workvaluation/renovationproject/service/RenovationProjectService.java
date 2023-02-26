package com.sewerynkamil.workvaluation.renovationproject.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.sewerynkamil.workvaluation.exception.ResourceNotFoundException;
import com.sewerynkamil.workvaluation.renovationproject.dto.RenovationProjectDTO;
import com.sewerynkamil.workvaluation.renovationproject.domain.RenovationProject;

import java.util.List;

public interface RenovationProjectService {
    List<RenovationProjectDTO> getAllRenovationProjects();

    RenovationProjectDTO findRenovationProjectById(Long id) throws ResourceNotFoundException;

    RenovationProject addRenovationProject(RenovationProjectDTO renovationProject);

    RenovationProject updateRenovationProject(RenovationProjectDTO renovationProject, Long id) throws ResourceNotFoundException;

    RenovationProject partialUpdateRenovationProject(JsonPatch patch, Long id) throws ResourceNotFoundException, JsonPatchException, JsonProcessingException;

    void deleteRenovationProject(Long id);
}
