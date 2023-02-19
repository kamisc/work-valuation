package com.workvaluation.workvaluation.renovationproject.mapper;

import com.workvaluation.workvaluation.renovationproject.controller.RenovationProjectController;
import com.workvaluation.workvaluation.renovationproject.domain.RenovationProjectEntity;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class RenovationProjectEntityAssembler implements RepresentationModelAssembler<RenovationProjectEntity, EntityModel<RenovationProjectEntity>> {
    @Override
    public EntityModel<RenovationProjectEntity> toModel(RenovationProjectEntity renovationProject) {
        return EntityModel.of(renovationProject,
                linkTo(methodOn(RenovationProjectController.class).findProjectById(renovationProject.getId())).withSelfRel(),
                linkTo(methodOn(RenovationProjectController.class).getAllProjects()).withRel("renovation_projects"));
    }
}
