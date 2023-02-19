package com.workvaluation.workvaluation.renovationproject.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.workvaluation.workvaluation.exception.ResourceNotFoundException;
import com.workvaluation.workvaluation.renovationproject.domain.RenovationProjectEntity;
import com.workvaluation.workvaluation.renovationproject.dto.RenovationProjectDTO;
import com.workvaluation.workvaluation.renovationproject.mapper.RenovationProjectDTOAssembler;
import com.workvaluation.workvaluation.renovationproject.mapper.RenovationProjectEntityAssembler;
import com.workvaluation.workvaluation.renovationproject.service.RenovationProjectService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.stream.Collectors.*;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/projects")
public class RenovationProjectController {
    private final RenovationProjectService projectService;
    private final RenovationProjectDTOAssembler renovationProjectDTOAssembler;
    private final RenovationProjectEntityAssembler renovationProjectEntityAssembler;

    public RenovationProjectController(
            final RenovationProjectService projectService,
            final RenovationProjectDTOAssembler renovationProjectDTOAssembler,
            final RenovationProjectEntityAssembler renovationProjectEntityAssembler) {
        this.projectService = projectService;
        this.renovationProjectDTOAssembler = renovationProjectDTOAssembler;
        this.renovationProjectEntityAssembler = renovationProjectEntityAssembler;
    }

    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<RenovationProjectDTO>>> getAllProjects() {
        List<EntityModel<RenovationProjectDTO>> projects = projectService.getAllRenovationProjects().stream()
                .map(renovationProjectDTOAssembler::toModel)
                .collect(toList());

        return ResponseEntity.ok(CollectionModel.of(projects, linkTo(methodOn(RenovationProjectController.class).getAllProjects()).withSelfRel()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<RenovationProjectDTO>> findProjectById(@PathVariable Long id) throws ResourceNotFoundException {
        return ResponseEntity.ok(renovationProjectDTOAssembler.toModel(projectService.findRenovationProjectById(id)));
    }

    @PostMapping
    public ResponseEntity<EntityModel<RenovationProjectEntity>> addProject(@RequestBody RenovationProjectDTO renovationProjectDTO) {
        EntityModel<RenovationProjectEntity> project = renovationProjectEntityAssembler.toModel(projectService.addRenovationProject(renovationProjectDTO));

        return ResponseEntity
                .created(project.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(project);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<RenovationProjectEntity>> updateProject(@RequestBody RenovationProjectDTO renovationProjectDTO, @PathVariable("id") Long projectId) throws ResourceNotFoundException {
        EntityModel<RenovationProjectEntity> project = renovationProjectEntityAssembler.toModel(projectService.updateRenovationProject(renovationProjectDTO, projectId));

        return ResponseEntity.
                created(project.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(project);
    }

    @PatchMapping(value = "/{id}", consumes = "application/json-patch+json")
    public ResponseEntity<EntityModel<RenovationProjectEntity>> partialUpdateProject(@RequestBody JsonPatch jsonPatch, @PathVariable("id") Long projectId) throws ResourceNotFoundException, JsonPatchException, JsonProcessingException {
        EntityModel<RenovationProjectEntity> project = renovationProjectEntityAssembler.toModel(projectService.partialUpdateRenovationProject(jsonPatch, projectId));

        return ResponseEntity
                .created(project.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(project);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable Long id) {
        projectService.deleteRenovationProject(id);

        return ResponseEntity.noContent().build();
    }
}
