package com.workvaluation.workvaluation.project.service;

import com.workvaluation.workvaluation.project.repository.ProjectRepository;
import com.workvaluation.workvaluation.project.domain.ProjectEntity;
import com.workvaluation.workvaluation.project.dto.ProjectDTO;
import com.workvaluation.workvaluation.project.mapper.ProjectMapper;
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
    public ProjectDTO findProject(Long id) {
        log.info("Find project: {}", id);
        return projectRepository.findById(id)
                .map(projectMapper::mapToProjectDTO)
                .orElseThrow(RuntimeException::new);
    }

    @Override
    public ProjectEntity addProject(ProjectDTO projectDTO) {
        log.info("Add new project to database: {}", projectDTO);
        return projectRepository.save(projectMapper.mapToProject(projectDTO));
    }

    @Override
    public ProjectEntity updateProject(ProjectDTO projectDTO, Long id) {
        log.info("Update project: {}", id);
        return null;
    }

    @Override
    public void deleteProject(Long id) {
        log.info("Delete project: {}", id);
        projectRepository.delete(projectRepository.findById(id).orElseThrow(RuntimeException::new));
    }
}
