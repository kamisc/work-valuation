package com.workvaluation.workvaluation.project.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.workvaluation.workvaluation.exception.ResourceNotFoundException;
import com.workvaluation.workvaluation.project.domain.ProjectEntity;
import com.workvaluation.workvaluation.project.dto.ProjectDTO;
import com.workvaluation.workvaluation.project.mapper.ProjectDTOAssembler;
import com.workvaluation.workvaluation.project.mapper.ProjectEntityAssembler;
import com.workvaluation.workvaluation.project.service.ProjectService;
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
public class ProjectController {
    private final ProjectService projectService;
    private final ProjectDTOAssembler projectDTOAssembler;
    private final ProjectEntityAssembler projectEntityAssembler;

    public ProjectController(
            final ProjectService projectService,
            final ProjectDTOAssembler projectDTOAssembler,
            final ProjectEntityAssembler projectEntityAssembler) {
        this.projectService = projectService;
        this.projectDTOAssembler = projectDTOAssembler;
        this.projectEntityAssembler = projectEntityAssembler;
    }

    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<ProjectDTO>>> getAllProjects() {
        List<EntityModel<ProjectDTO>> projects = projectService.getAllProjects().stream()
                .map(projectDTOAssembler::toModel)
                .collect(toList());

        return ResponseEntity.ok(CollectionModel.of(projects, linkTo(methodOn(ProjectController.class).getAllProjects()).withSelfRel()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<ProjectDTO>> findProjectById(@PathVariable Long id) throws ResourceNotFoundException {
        return ResponseEntity.ok(projectDTOAssembler.toModel(projectService.findProjectById(id)));
    }

    @PostMapping
    public ResponseEntity<EntityModel<ProjectEntity>> addProject(@RequestBody ProjectDTO projectDTO) {
        EntityModel<ProjectEntity> project = projectEntityAssembler.toModel(projectService.addProject(projectDTO));

        return ResponseEntity
                .created(project.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(project);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<ProjectEntity>> updateProject(@RequestBody ProjectDTO projectDTO, @PathVariable("id") Long projectId) throws ResourceNotFoundException {
        EntityModel<ProjectEntity> project = projectEntityAssembler.toModel(projectService.updateProject(projectDTO, projectId));

        return ResponseEntity.
                created(project.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(project);
    }

    @PatchMapping(value = "/{id}", consumes = "application/json-patch+json")
    public ResponseEntity<EntityModel<ProjectEntity>> partialUpdateProject(@RequestBody JsonPatch jsonPatch, @PathVariable("id") Long projectId) throws ResourceNotFoundException, JsonPatchException, JsonProcessingException {
        EntityModel<ProjectEntity> project = projectEntityAssembler.toModel(projectService.partialUpdateProject(jsonPatch, projectId));

        return ResponseEntity
                .created(project.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(project);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable Long id) {
        projectService.deleteProject(id);

        return ResponseEntity.noContent().build();
    }
}
