package com.workvaluation.workvaluation.project.mapper;

import com.workvaluation.workvaluation.project.domain.ProjectEntity;
import com.workvaluation.workvaluation.project.dto.ProjectDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProjectMapper {
    public List<ProjectDTO> mapToProjectDTOList(final List<ProjectEntity> projectList) {
        return projectList.stream()
                .map(project -> new ProjectDTO(
                        project.getId(),
                        project.getName(),
                        project.getAddress(),
                        project.getCreateDate()))
                .collect(Collectors.toList());
    }

    public ProjectDTO mapToProjectDTO(final ProjectEntity project) {
        return new ProjectDTO(project.getId(), project.getName(), project.getAddress(), project.getCreateDate());
    }

    public ProjectEntity mapToProject(final ProjectDTO projectDTO) {
        return new ProjectEntity(projectDTO.getName(), projectDTO.getAddress());
    }
}
