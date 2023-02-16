package com.workvaluation.workvaluation.project.mapper;

import com.workvaluation.workvaluation.project.controller.ProjectController;
import com.workvaluation.workvaluation.project.dto.ProjectDTO;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ProjectDTOAssembler implements RepresentationModelAssembler<ProjectDTO, EntityModel<ProjectDTO>> {
    @Override
    public EntityModel<ProjectDTO> toModel(ProjectDTO project) {
        return EntityModel.of(project,
                linkTo(methodOn(ProjectController.class).findProjectById(project.getId())).withSelfRel(),
                linkTo(methodOn(ProjectController.class).getAllProjects()).withRel("projects"));
    }
}
