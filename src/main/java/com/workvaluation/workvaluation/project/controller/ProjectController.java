package com.workvaluation.workvaluation.project.controller;

import com.workvaluation.workvaluation.exception.ResourceNotFoundException;
import com.workvaluation.workvaluation.project.domain.ProjectEntity;
import com.workvaluation.workvaluation.project.dto.ProjectDTO;
import com.workvaluation.workvaluation.project.service.ProjectService;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<ProjectDTO> findProjectById(@PathVariable Long id) {
        return new ResponseEntity<>(projectService.findProject(id), HttpStatus.OK);
    }

    @PostMapping
    public void addProject(@RequestBody ProjectDTO projectDTO) {
        projectService.addProject(projectDTO);
    }

    @PutMapping("/{id}")
    public void updateProject(@RequestBody ProjectDTO projectDTO, Long projectId) {

    }

    @DeleteMapping("/{id}")
    public void deleteProject(@PathVariable Long id) {
        projectService.deleteProject(id);
    }
}
