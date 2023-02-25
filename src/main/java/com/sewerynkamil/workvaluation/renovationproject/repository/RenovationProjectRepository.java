package com.sewerynkamil.workvaluation.renovationproject.repository;

import com.sewerynkamil.workvaluation.renovationproject.domain.RenovationProjectEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Transactional
@Repository
public interface RenovationProjectRepository extends JpaRepository<RenovationProjectEntity, Long> {
}
