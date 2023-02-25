package com.sewerynkamil.workvaluation.renovationproject;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.sewerynkamil.workvaluation.exception.ResourceNotFoundException;
import com.sewerynkamil.workvaluation.renovationproject.domain.RenovationProjectEntity;
import com.sewerynkamil.workvaluation.renovationproject.dto.RenovationProjectDTO;
import com.sewerynkamil.workvaluation.renovationproject.mapper.RenovationProjectMapper;
import com.sewerynkamil.workvaluation.renovationproject.repository.RenovationProjectRepository;
import com.sewerynkamil.workvaluation.renovationproject.service.RenovationProjectServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ProjectRenovationServiceTests {
    private RenovationProjectRepository renovationProjectRepository;
    private RenovationProjectMapper renovationProjectMapper;
    private RenovationProjectServiceImpl renovationProjectService;

    RenovationProjectEntity renovationProjectEntity1, renovationProjectEntity2, renovationProjectEntity3;
    RenovationProjectDTO renovationProjectDTO1, renovationProjectDTO2, renovationProjectDTO3;

    @BeforeEach
    void setUp() {
        renovationProjectRepository = Mockito.mock(RenovationProjectRepository.class);
        renovationProjectMapper = Mockito.mock(RenovationProjectMapper.class);
        renovationProjectService = new RenovationProjectServiceImpl(renovationProjectRepository, renovationProjectMapper);
    }

    @BeforeAll
    void setUpData() {
        renovationProjectEntity1 = new RenovationProjectEntity("Project 1", "Address 1", 1L);
        renovationProjectEntity2 = new RenovationProjectEntity("Project 2", "Address 2", 2L);
        renovationProjectEntity3 = new RenovationProjectEntity("Project 3", "Address 3", 3L);

        renovationProjectDTO1 = new RenovationProjectDTO(1L, "Project 1", "Address 1", new Date());
        renovationProjectDTO2 = new RenovationProjectDTO(2L, "Project 2", "Address 2", new Date());
        renovationProjectDTO3 = new RenovationProjectDTO(3L, "Project 3", "Address 3", new Date());
    }

    @Test
    void testGetAllRenovationProjectsWithResults() {
        // Given
        final List<RenovationProjectEntity> renovationProjectList = Arrays.asList(renovationProjectEntity1, renovationProjectEntity2);
        final List<RenovationProjectDTO> renovationProjectDTOList = Arrays.asList(renovationProjectDTO1, renovationProjectDTO2);

        // When
        when(renovationProjectRepository.findAll()).thenReturn(renovationProjectList);
        when(renovationProjectMapper.mapToProjectDTOList(renovationProjectList)).thenReturn(renovationProjectDTOList);

        final List<RenovationProjectDTO> expectedRenovationProjectDTOList = renovationProjectService.getAllRenovationProjects();

        // Then
        assertThat(expectedRenovationProjectDTOList)
                .hasSize(2)
                .contains(renovationProjectDTO1, renovationProjectDTO2)
                .doesNotContain(renovationProjectDTO3);
    }

    @Test
    void testGetAllRenovationProjectsWithoutResults() {
        // Given
        final List<RenovationProjectEntity> renovationProjectList = new ArrayList<>();
        final List<RenovationProjectDTO> renovationProjectDTOList = new ArrayList<>();

        when(renovationProjectRepository.findAll()).thenReturn(renovationProjectList);
        when(renovationProjectMapper.mapToProjectDTOList(renovationProjectList)).thenReturn(renovationProjectDTOList);

        // When
        final List<RenovationProjectDTO> expectedRenovationProjectDTOList = renovationProjectService.getAllRenovationProjects();

        // Then
        assertThat(expectedRenovationProjectDTOList).isEmpty();
    }

    @Test
    void testGetRenovationProjectById() {
        // Given
        final Long projectId = 3L;

        when(renovationProjectRepository.findById(projectId)).thenReturn(Optional.ofNullable(renovationProjectEntity3));
        when(renovationProjectMapper.mapToProjectDTO(Optional.of(renovationProjectEntity3).get())).thenReturn(renovationProjectDTO3);

        // When
        final RenovationProjectDTO expectedRenovationProjectDTO = renovationProjectService.findRenovationProjectById(projectId);

        // Then
        assertThat(expectedRenovationProjectDTO.getId()).isEqualTo(projectId);
    }

    @Test
    void testGetRenovationProjectByIdNotFound() {
        // Given
        final Long projectId = 99L;
        when(renovationProjectRepository.findById(projectId)).thenThrow(ResourceNotFoundException.class);

        // When & Then
        assertThrows(ResourceNotFoundException.class,
                () -> renovationProjectService.findRenovationProjectById(projectId));
    }

    @Test
    void testAddRenovationProject() {
        // Given
        final RenovationProjectDTO renovationProjectDTO = new RenovationProjectDTO("Project 4", "Address 4");
        final RenovationProjectEntity renovationProjectEntity = new RenovationProjectEntity("Project 4", "Address 4", 4L);

        when(renovationProjectMapper.mapToProject(renovationProjectDTO)).thenReturn(renovationProjectEntity);
        when(renovationProjectRepository.save(renovationProjectEntity)).thenReturn(renovationProjectEntity);

        // When
        final RenovationProjectEntity expectedRenovationProject = renovationProjectService.addRenovationProject(renovationProjectDTO);

        // Then
       assertThat(expectedRenovationProject).isEqualTo(renovationProjectEntity);
    }

    @Test
    void testUpdateRenovationProjectExist() {
        // Given
        final Long projectId = 1L;
        final RenovationProjectDTO renovationProjectDTO = new RenovationProjectDTO("Project 5", "Address 5");
        final RenovationProjectEntity renovationProjectUpdated = new RenovationProjectEntity("Project 5", "Address 5", 5L);

        when(renovationProjectRepository.findById(projectId)).thenReturn(Optional.of(renovationProjectEntity1));
        when(renovationProjectRepository.save(renovationProjectEntity1)).thenReturn(renovationProjectUpdated);

        // When
        final RenovationProjectEntity expectedRenovationProject = renovationProjectService.updateRenovationProject(renovationProjectDTO, projectId);

        // Then
        assertThat(expectedRenovationProject).isEqualTo(renovationProjectUpdated);
    }

    @Test
    void testUpdateRenovationProjectNotExist() {
        // Given
        final Long projectId = 6L;
        final RenovationProjectDTO renovationProjectDTO = new RenovationProjectDTO("Project 6", "Address 6");
        final RenovationProjectEntity renovationProjectUpdated = new RenovationProjectEntity("Project 6", "Address 6", 6L);

        when(renovationProjectMapper.mapToProject(renovationProjectDTO)).thenReturn(renovationProjectUpdated);
        when(renovationProjectRepository.save(renovationProjectUpdated)).thenReturn(renovationProjectUpdated);

        // When
        final RenovationProjectEntity expectedRenovationProject = renovationProjectService.updateRenovationProject(renovationProjectDTO, projectId);

        // Then
        assertThat(expectedRenovationProject).isEqualTo(renovationProjectUpdated);
    }

    @Test
    void testPartialUpdateRenovationProject() throws IOException, JsonPatchException {
        // Given
        final ObjectMapper objectMapper = new ObjectMapper();
        final Long projectId = 1L;
        final RenovationProjectEntity oldProject = new RenovationProjectEntity("Project 7", "Address 7", 7L);
        final RenovationProjectEntity updatedProject = new RenovationProjectEntity("Project 8", "Address 7", 7L);
        RenovationProjectDTO updatedProjectDTO = new RenovationProjectDTO(7L, "Project 7", "Address 7", oldProject.getCreateDate());
        JsonPatch patch = JsonPatch.fromJson(objectMapper.readTree("[{\"op\":\"replace\",\"path\":\"/name\",\"value\":\"Project 8\"}]"));
        JsonNode patchedProject = patch.apply(objectMapper.convertValue(updatedProjectDTO, JsonNode.class));
        updatedProjectDTO = objectMapper.treeToValue(patchedProject, RenovationProjectDTO.class);

        Mockito.when(renovationProjectRepository.findById(projectId)).thenReturn(Optional.of(oldProject));
        Mockito.when(renovationProjectMapper.mapToProjectDTO(oldProject)).thenReturn(updatedProjectDTO);
        Mockito.when(renovationProjectMapper.mapToProject(updatedProjectDTO)).thenReturn(updatedProject);
        Mockito.when(renovationProjectRepository.save(updatedProject)).thenReturn(updatedProject);

        // When
        RenovationProjectEntity expectedRenovationProject = renovationProjectService.partialUpdateRenovationProject(patch, projectId);

        // Then
        assertThat(expectedRenovationProject.getName()).isEqualTo("Project 8");
        assertThat(expectedRenovationProject.getId()).isEqualTo(7L);
    }

    @Test
    void testDeleteRenovationProject() {
        // Given
        final Long projectId = 9L;
        final RenovationProjectEntity deletedProject = new RenovationProjectEntity("Project 9", "Address 9", 9L);

        // When
        renovationProjectService.deleteRenovationProject(projectId);

        // Then
        verify(renovationProjectRepository).deleteById(projectId);
    }
}

