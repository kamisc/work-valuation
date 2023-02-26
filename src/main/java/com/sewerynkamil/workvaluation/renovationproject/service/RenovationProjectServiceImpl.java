package com.sewerynkamil.workvaluation.renovationproject.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.sewerynkamil.workvaluation.exception.ResourceNotFoundException;
import com.sewerynkamil.workvaluation.renovationproject.repository.RenovationProjectRepository;
import com.sewerynkamil.workvaluation.renovationproject.domain.RenovationProject;
import com.sewerynkamil.workvaluation.renovationproject.dto.RenovationProjectDTO;
import com.sewerynkamil.workvaluation.renovationproject.mapper.RenovationProjectMapper;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class RenovationProjectServiceImpl implements RenovationProjectService {

    private final RenovationProjectRepository renovationProjectRepository;
    private final RenovationProjectMapper renovationProjectMapper;

    public RenovationProjectServiceImpl(
            final RenovationProjectRepository renovationProjectRepository,
            final RenovationProjectMapper renovationProjectMapper) {
        this.renovationProjectRepository = renovationProjectRepository;
        this.renovationProjectMapper = renovationProjectMapper;
    }

    @Override
    public List<RenovationProjectDTO> getAllRenovationProjects() {
        log.info("Get all renovation projects.");
        return renovationProjectMapper.mapToRenovationProjectDTOList(renovationProjectRepository.findAll());
    }

    @Override
    public RenovationProjectDTO findRenovationProjectById(final Long id) throws ResourceNotFoundException {
        log.info("Try to find renovation project: {}", id);
        return renovationProjectRepository.findById(id)
                .map(renovationProjectMapper::mapToRenovationProjectDTO)
                .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    @Transactional
    public RenovationProject addRenovationProject(final RenovationProjectDTO renovationProject) {
        log.info("Add new renovation project to the database: {}", renovationProject);
        return renovationProjectRepository.save(renovationProjectMapper.mapToRenovationProject(renovationProject));
    }

    @Override
    @Transactional
    public RenovationProject updateRenovationProject(final RenovationProjectDTO renovationProjectDTO, final Long id) {
        log.info("Update renovation project: {}", id);
        return renovationProjectRepository.findById(id)
                .map(renovationProject -> {
                    renovationProject.setName(renovationProject.getName());
                    renovationProject.setAddress(renovationProject.getAddress());
                    return renovationProjectRepository.save(renovationProject);
                }).orElseGet(() -> {
                    renovationProjectDTO.setId(id);
                    return renovationProjectRepository.save(renovationProjectMapper.mapToRenovationProject(renovationProjectDTO));
                });
    }

    @Override
    @Transactional
    public RenovationProject partialUpdateRenovationProject(final JsonPatch patch, final Long id)
            throws ResourceNotFoundException, JsonPatchException, JsonProcessingException {
        log.info("Partial update renovation project: {}", id);
        RenovationProject oldProject = renovationProjectRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        RenovationProjectDTO renovationProjectDTO = renovationProjectMapper.mapToRenovationProjectDTO(oldProject);

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode patchedProject = patch.apply(objectMapper.convertValue(renovationProjectDTO, JsonNode.class));
        renovationProjectDTO = objectMapper.treeToValue(patchedProject, RenovationProjectDTO.class);

        return renovationProjectRepository.save(renovationProjectMapper.mapToRenovationProject(renovationProjectDTO));
    }

    @Override
    @Transactional
    public void deleteRenovationProject(final Long id) {
        log.info("Delete project: {}", id);
        renovationProjectRepository.deleteById(id);
    }
}
