package com.sewerynkamil.workvaluation.renovationproject.repository;

import com.sewerynkamil.workvaluation.renovationproject.domain.RenovationProject;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Transactional
@Repository
public interface RenovationProjectRepository extends JpaRepository<RenovationProject, Long> {
}
