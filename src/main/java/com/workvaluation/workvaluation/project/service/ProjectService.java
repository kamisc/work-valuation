package com.workvaluation.workvaluation.project.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.workvaluation.workvaluation.exception.ResourceNotFoundException;
import com.workvaluation.workvaluation.project.dto.ProjectDTO;
import com.workvaluation.workvaluation.project.domain.ProjectEntity;

import java.util.List;

public interface ProjectService {
    List<ProjectDTO> getAllProjects();

    ProjectDTO findProjectById(Long id) throws ResourceNotFoundException;

    ProjectEntity addProject(ProjectDTO projectDTO);

    ProjectEntity updateProject(ProjectDTO projectDTO, Long id) throws ResourceNotFoundException;

    ProjectEntity partialUpdateProject(JsonPatch patch, Long id) throws ResourceNotFoundException, JsonPatchException, JsonProcessingException;

    void deleteProject(Long id);
}
