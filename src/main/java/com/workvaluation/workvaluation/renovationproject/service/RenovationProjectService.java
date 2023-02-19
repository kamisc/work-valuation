package com.workvaluation.workvaluation.renovationproject.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.workvaluation.workvaluation.exception.ResourceNotFoundException;
import com.workvaluation.workvaluation.renovationproject.dto.RenovationProjectDTO;
import com.workvaluation.workvaluation.renovationproject.domain.RenovationProjectEntity;

import java.util.List;

public interface RenovationProjectService {
    List<RenovationProjectDTO> getAllRenovationProjects();

    RenovationProjectDTO findRenovationProjectById(Long id) throws ResourceNotFoundException;

    RenovationProjectEntity addRenovationProject(RenovationProjectDTO renovationProject);

    RenovationProjectEntity updateRenovationProject(RenovationProjectDTO renovationProject, Long id) throws ResourceNotFoundException;

    RenovationProjectEntity partialUpdateRenovationProject(JsonPatch patch, Long id) throws ResourceNotFoundException, JsonPatchException, JsonProcessingException;

    void deleteRenovationProject(Long id);
}
