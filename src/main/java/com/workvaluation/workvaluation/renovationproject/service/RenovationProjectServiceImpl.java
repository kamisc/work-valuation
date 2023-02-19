package com.workvaluation.workvaluation.renovationproject.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.workvaluation.workvaluation.exception.ResourceNotFoundException;
import com.workvaluation.workvaluation.renovationproject.repository.RenovationProjectRepository;
import com.workvaluation.workvaluation.renovationproject.domain.RenovationProjectEntity;
import com.workvaluation.workvaluation.renovationproject.dto.RenovationProjectDTO;
import com.workvaluation.workvaluation.renovationproject.mapper.RenovationProjectMapper;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class RenovationProjectServiceImpl implements RenovationProjectService {

    private final RenovationProjectRepository renovationProjectRepository;
    private final RenovationProjectMapper renovationProjectMapper;

    public RenovationProjectServiceImpl(RenovationProjectRepository renovationProjectRepository, RenovationProjectMapper renovationProjectMapper) {
        this.renovationProjectRepository = renovationProjectRepository;
        this.renovationProjectMapper = renovationProjectMapper;
    }

    @Override
    public List<RenovationProjectDTO> getAllRenovationProjects() {
        log.info("Get all projects.");
        return renovationProjectMapper.mapToProjectDTOList(renovationProjectRepository.findAll());
    }

    @Override
    public RenovationProjectDTO findRenovationProjectById(final Long id) throws ResourceNotFoundException {
        log.info("Find project: {}", id);
        return renovationProjectRepository.findById(id)
                .map(renovationProjectMapper::mapToProjectDTO)
                .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    @Transactional
    public RenovationProjectEntity addRenovationProject(final RenovationProjectDTO renovationProject) {
        log.info("Add new project to database: {}", renovationProject);
        return renovationProjectRepository.save(renovationProjectMapper.mapToProject(renovationProject));
    }

    @Override
    @Transactional
    public RenovationProjectEntity updateRenovationProject(final RenovationProjectDTO renovationProject, final Long id) {
        log.info("Update project: {}", id);
        return renovationProjectRepository.findById(id)
                .map(project -> {
                    project.setName(renovationProject.getName());
                    project.setAddress(renovationProject.getAddress());
                    return renovationProjectRepository.save(project);
                }).orElseGet(() -> {
                    renovationProject.setId(id);
                    return renovationProjectRepository.save(renovationProjectMapper.mapToProject(renovationProject));
                });
    }

    @Override
    @Transactional
    public RenovationProjectEntity partialUpdateRenovationProject(JsonPatch patch, Long id) throws ResourceNotFoundException, JsonPatchException, JsonProcessingException {
        log.info("Partial update project: {}", id);
        RenovationProjectEntity oldProject = renovationProjectRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        RenovationProjectDTO renovationProjectDTO = renovationProjectMapper.mapToProjectDTO(oldProject);

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode patchedProject = patch.apply(objectMapper.convertValue(renovationProjectDTO, JsonNode.class));
        renovationProjectDTO = objectMapper.treeToValue(patchedProject, RenovationProjectDTO.class);

        return renovationProjectRepository.save(renovationProjectMapper.mapToProject(renovationProjectDTO));
    }

    @Override
    @Transactional
    public void deleteRenovationProject(final Long id) {
        log.info("Delete project: {}", id);
        renovationProjectRepository.deleteById(id);
    }
}
