package com.workvaluation.workvaluation.project.mapper;

import com.workvaluation.workvaluation.project.controller.ProjectController;
import com.workvaluation.workvaluation.project.domain.ProjectEntity;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ProjectEntityAssembler implements RepresentationModelAssembler<ProjectEntity, EntityModel<ProjectEntity>> {
    @Override
    public EntityModel<ProjectEntity> toModel(ProjectEntity project) {
        return EntityModel.of(project,
                linkTo(methodOn(ProjectController.class).findProjectById(project.getId())).withSelfRel(),
                linkTo(methodOn(ProjectController.class).getAllProjects()).withRel("projects"));
    }
}
