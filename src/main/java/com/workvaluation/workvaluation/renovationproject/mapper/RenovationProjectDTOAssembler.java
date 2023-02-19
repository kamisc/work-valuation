package com.workvaluation.workvaluation.renovationproject.mapper;

import com.workvaluation.workvaluation.renovationproject.controller.RenovationProjectController;
import com.workvaluation.workvaluation.renovationproject.dto.RenovationProjectDTO;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class RenovationProjectDTOAssembler implements RepresentationModelAssembler<RenovationProjectDTO, EntityModel<RenovationProjectDTO>> {
    @Override
    public EntityModel<RenovationProjectDTO> toModel(RenovationProjectDTO renovationProject) {
        return EntityModel.of(renovationProject,
                linkTo(methodOn(RenovationProjectController.class).findProjectById(renovationProject.getId())).withSelfRel(),
                linkTo(methodOn(RenovationProjectController.class).getAllProjects()).withRel("renovation_projects"));
    }
}
