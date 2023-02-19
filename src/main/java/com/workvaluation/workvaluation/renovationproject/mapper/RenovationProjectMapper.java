package com.workvaluation.workvaluation.renovationproject.mapper;

import com.workvaluation.workvaluation.renovationproject.domain.RenovationProjectEntity;
import com.workvaluation.workvaluation.renovationproject.dto.RenovationProjectDTO;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Component
public class RenovationProjectMapper {
    public List<RenovationProjectDTO> mapToProjectDTOList(final List<RenovationProjectEntity> renovationProjectList) {
        return renovationProjectList.stream()
                .map(project -> new RenovationProjectDTO(
                        project.getId(),
                        project.getName(),
                        project.getAddress(),
                        project.getCreateDate()))
                .collect(toList());
    }

    public RenovationProjectDTO mapToProjectDTO(final RenovationProjectEntity renovationProject) {
        return new RenovationProjectDTO(
                renovationProject.getId(), renovationProject.getName(), renovationProject.getAddress(), renovationProject.getCreateDate());
    }

    public RenovationProjectEntity mapToProject(final RenovationProjectDTO renovationProject) {
        return new RenovationProjectEntity(renovationProject.getName(), renovationProject.getAddress(), renovationProject.getId());
    }
}
