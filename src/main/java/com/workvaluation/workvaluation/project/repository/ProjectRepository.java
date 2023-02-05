package com.workvaluation.workvaluation.project.repository;

import com.workvaluation.workvaluation.project.domain.ProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<ProjectEntity, Long> {
}
