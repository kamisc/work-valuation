package com.workvaluation.workvaluation.project.service;

import com.github.fge.jsonpatch.JsonPatch;
import com.workvaluation.workvaluation.project.dto.ProjectDTO;
import com.workvaluation.workvaluation.project.domain.ProjectEntity;

import java.util.List;

public interface ProjectService {
    List<ProjectDTO> getAllProjects();

    ProjectDTO findProject(Long id);

    ProjectEntity addProject(ProjectDTO projectDTO);

    ProjectEntity updateProject(ProjectDTO projectDTO, Long id);

    void partialUpdateProject(JsonPatch patch, Long id);

    void deleteProject(Long id);
}
