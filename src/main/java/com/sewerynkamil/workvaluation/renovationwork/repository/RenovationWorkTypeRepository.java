package com.sewerynkamil.workvaluation.renovationwork.repository;

import com.sewerynkamil.workvaluation.renovationwork.domain.RenovationWorkType;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Transactional
@Repository
public interface RenovationWorkTypeRepository extends JpaRepository<RenovationWorkType, Long> {
}
