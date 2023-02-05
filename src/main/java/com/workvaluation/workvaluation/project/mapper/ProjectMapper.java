package com.workvaluation.workvaluation.project.mapper;

import com.workvaluation.workvaluation.project.domain.ProjectEntity;
import com.workvaluation.workvaluation.project.dto.ProjectDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProjectMapper {
    private final CustomerMapper customerMapper;

    public ProjectMapper(CustomerMapper customerMapper) {
        this.customerMapper = customerMapper;
    }

    public List<ProjectDTO> mapToProjectDTOList(List<ProjectEntity> projectList) {
        return projectList.stream()
                .map(project -> new ProjectDTO(
                        project.getId(),
                        project.getName(),
                        customerMapper.mapToCustomerDTO(project.getCustomer())))
                .collect(Collectors.toList());
    }

    public ProjectDTO mapToProjectDTO(ProjectEntity project) {
        return new ProjectDTO(project.getId(), project.getName(), customerMapper.mapToCustomerDTO(project.getCustomer()));
    }

    public ProjectEntity mapToProject(ProjectDTO projectDTO) {
        return new ProjectEntity(projectDTO.getName(), customerMapper.mapToCustomer(projectDTO.getCustomerDTO()));
    }
}
