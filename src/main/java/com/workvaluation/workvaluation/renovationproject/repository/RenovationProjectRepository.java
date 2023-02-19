package com.workvaluation.workvaluation.renovationproject.repository;

import com.workvaluation.workvaluation.renovationproject.domain.RenovationProjectEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Transactional
@Repository
public interface RenovationProjectRepository extends JpaRepository<RenovationProjectEntity, Long> {
}
