package com.workvaluation.workvaluation.project.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.workvaluation.workvaluation.exception.ResourceNotFoundException;
import com.workvaluation.workvaluation.project.repository.ProjectRepository;
import com.workvaluation.workvaluation.project.domain.ProjectEntity;
import com.workvaluation.workvaluation.project.dto.ProjectDTO;
import com.workvaluation.workvaluation.project.mapper.ProjectMapper;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final ProjectMapper projectMapper;

    public ProjectServiceImpl(ProjectRepository projectRepository, ProjectMapper projectMapper) {
        this.projectRepository = projectRepository;
        this.projectMapper = projectMapper;
    }

    @Override
    public List<ProjectDTO> getAllProjects() {
        log.info("Get all projects.");
        return projectMapper.mapToProjectDTOList(projectRepository.findAll());
    }

    @Override
    public ProjectDTO findProjectById(final Long id) throws ResourceNotFoundException {
        log.info("Find project: {}", id);
        return projectRepository.findById(id)
                .map(projectMapper::mapToProjectDTO)
                .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    @Transactional
    public ProjectEntity addProject(final ProjectDTO projectDTO) {
        log.info("Add new project to database: {}", projectDTO);
        return projectRepository.save(projectMapper.mapToProject(projectDTO));
    }

    @Override
    @Transactional
    public ProjectEntity updateProject(final ProjectDTO projectDTO, final Long id) {
        log.info("Update project: {}", id);
        return projectRepository.findById(id)
                .map(project -> {
                    project.setName(projectDTO.getName());
                    project.setAddress(projectDTO.getAddress());
                    return projectRepository.save(project);
                }).orElseGet(() -> {
                    projectDTO.setId(id);
                    return projectRepository.save(projectMapper.mapToProject(projectDTO));
                });
    }

    @Override
    @Transactional
    public ProjectEntity partialUpdateProject(JsonPatch patch, Long id) throws ResourceNotFoundException, JsonPatchException, JsonProcessingException {
        log.info("Partial update project: {}", id);
        ProjectEntity oldProject = projectRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        ProjectDTO projectDTO = projectMapper.mapToProjectDTO(oldProject);

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode patchedProject = patch.apply(objectMapper.convertValue(projectDTO, JsonNode.class));
        projectDTO = objectMapper.treeToValue(patchedProject, ProjectDTO.class);

        return projectRepository.save(projectMapper.mapToProject(projectDTO));
    }

    @Override
    @Transactional
    public void deleteProject(final Long id) {
        log.info("Delete project: {}", id);
        projectRepository.deleteById(id);
    }
}
