package com.sewerynkamil.workvaluation.renovationproject.mapper;

import com.sewerynkamil.workvaluation.renovationproject.domain.RenovationProject;
import com.sewerynkamil.workvaluation.renovationproject.dto.RenovationProjectDTO;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Component
public class RenovationProjectMapper {
    public List<RenovationProjectDTO> mapToRenovationProjectDTOList(final List<RenovationProject> renovationProjectList) {
        return renovationProjectList.stream()
                .map(project -> new RenovationProjectDTO(
                        project.getId(),
                        project.getName(),
                        project.getAddress(),
                        project.getCreateDate()))
                .collect(toList());
    }

    public RenovationProjectDTO mapToRenovationProjectDTO(final RenovationProject renovationProject) {
        return new RenovationProjectDTO(
                renovationProject.getId(), renovationProject.getName(), renovationProject.getAddress(), renovationProject.getCreateDate());
    }

    public RenovationProject mapToRenovationProject(final RenovationProjectDTO renovationProject) {
        return new RenovationProject(renovationProject.getName(), renovationProject.getAddress(), renovationProject.getId());
    }
}
