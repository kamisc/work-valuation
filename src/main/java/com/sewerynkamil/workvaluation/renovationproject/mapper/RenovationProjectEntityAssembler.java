package com.sewerynkamil.workvaluation.renovationproject.mapper;

import com.sewerynkamil.workvaluation.renovationproject.controller.RenovationProjectController;
import com.sewerynkamil.workvaluation.renovationproject.domain.RenovationProject;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class RenovationProjectEntityAssembler implements RepresentationModelAssembler<RenovationProject, EntityModel<RenovationProject>> {
    @Override
    public EntityModel<RenovationProject> toModel(RenovationProject renovationProject) {
        return EntityModel.of(renovationProject,
                linkTo(methodOn(RenovationProjectController.class).findRenovationProjectById(renovationProject.getId())).withSelfRel(),
                linkTo(methodOn(RenovationProjectController.class).getAllRenovationProjects()).withRel("renovation_projects"));
    }
}
