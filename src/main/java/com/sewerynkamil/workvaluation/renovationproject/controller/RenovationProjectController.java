package com.sewerynkamil.workvaluation.renovationproject.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.sewerynkamil.workvaluation.exception.ResourceNotFoundException;
import com.sewerynkamil.workvaluation.renovationproject.domain.RenovationProject;
import com.sewerynkamil.workvaluation.renovationproject.dto.RenovationProjectDTO;
import com.sewerynkamil.workvaluation.renovationproject.mapper.RenovationProjectDTOAssembler;
import com.sewerynkamil.workvaluation.renovationproject.mapper.RenovationProjectEntityAssembler;
import com.sewerynkamil.workvaluation.renovationproject.service.RenovationProjectService;
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
    private final RenovationProjectService renovationProjectService;
    private final RenovationProjectDTOAssembler renovationProjectDTOAssembler;
    private final RenovationProjectEntityAssembler renovationProjectEntityAssembler;

    public RenovationProjectController(
            final RenovationProjectService renovationProjectService,
            final RenovationProjectDTOAssembler renovationProjectDTOAssembler,
            final RenovationProjectEntityAssembler renovationProjectEntityAssembler) {
        this.renovationProjectService = renovationProjectService;
        this.renovationProjectDTOAssembler = renovationProjectDTOAssembler;
        this.renovationProjectEntityAssembler = renovationProjectEntityAssembler;
    }

    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<RenovationProjectDTO>>> getAllRenovationProjects() {
        List<EntityModel<RenovationProjectDTO>> renovationProjects = renovationProjectService.getAllRenovationProjects().stream()
                .map(renovationProjectDTOAssembler::toModel)
                .collect(toList());

        return ResponseEntity.ok(CollectionModel.of(renovationProjects, linkTo(methodOn(RenovationProjectController.class).getAllRenovationProjects()).withSelfRel()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<RenovationProjectDTO>> findRenovationProjectById(@PathVariable Long id) throws ResourceNotFoundException {
        return ResponseEntity.ok(renovationProjectDTOAssembler.toModel(renovationProjectService.findRenovationProjectById(id)));
    }

    @PostMapping
    public ResponseEntity<EntityModel<RenovationProject>> addRenovationProject(@RequestBody RenovationProjectDTO renovationProjectDTO) {
        EntityModel<RenovationProject> renovationProject = renovationProjectEntityAssembler.toModel(
                renovationProjectService.addRenovationProject(renovationProjectDTO));

        return ResponseEntity
                .created(renovationProject.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(renovationProject);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<RenovationProject>> updateProject(
            @RequestBody RenovationProjectDTO renovationProjectDTO, @PathVariable("id") Long projectId) throws ResourceNotFoundException {
        final EntityModel<RenovationProject> renovationProject = renovationProjectEntityAssembler.toModel(
                renovationProjectService.updateRenovationProject(renovationProjectDTO, projectId));

        return ResponseEntity.
                created(renovationProject.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(renovationProject);
    }

    @PatchMapping(value = "/{id}", consumes = "application/json-patch+json")
    public ResponseEntity<EntityModel<RenovationProject>> partialUpdateRenovationProject(
            @RequestBody JsonPatch jsonPatch, @PathVariable("id") Long projectId) throws ResourceNotFoundException, JsonPatchException, JsonProcessingException {
        final EntityModel<RenovationProject> renovationProject = renovationProjectEntityAssembler.toModel(
                renovationProjectService.partialUpdateRenovationProject(jsonPatch, projectId));

        return ResponseEntity
                .created(renovationProject.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(renovationProject);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRenovationProject(@PathVariable Long id) {
        renovationProjectService.deleteRenovationProject(id);

        return ResponseEntity.noContent().build();
    }
}
