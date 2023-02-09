package com.workvaluation.workvaluation.project.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.workvaluation.workvaluation.exception.ResourceNotFoundException;
import com.workvaluation.workvaluation.project.domain.ProjectEntity;
import com.workvaluation.workvaluation.project.dto.ProjectDTO;
import com.workvaluation.workvaluation.project.service.ProjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectController {
    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping
    public ResponseEntity<List<ProjectDTO>> getAllProjects() {
        return new ResponseEntity<>(projectService.getAllProjects(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectDTO> findProjectById(@PathVariable Long id) throws ResourceNotFoundException {
        return new ResponseEntity<>(projectService.findProjectById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> addProject(@RequestBody ProjectDTO projectDTO) {
        projectService.addProject(projectDTO);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateProject(@RequestBody ProjectDTO projectDTO, Long projectId) throws ResourceNotFoundException {
        projectService.updateProject(projectDTO, projectId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PatchMapping(value = "/{id}", consumes = "application/json-patch+json")
    public ResponseEntity<Void> partialUpdateProject(@RequestBody JsonPatch jsonPatch, @PathVariable Long projectId) throws ResourceNotFoundException, JsonPatchException, JsonProcessingException {
        projectService.partialUpdateProject(jsonPatch, projectId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable Long id) {
        projectService.deleteProject(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
