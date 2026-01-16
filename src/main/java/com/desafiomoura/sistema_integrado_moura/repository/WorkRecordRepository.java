package com.desafiomoura.sistema_integrado_moura.repository;

import com.desafiomoura.sistema_integrado_moura.entity.WorkRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.List;

@Repository
public interface WorkRecordRepository extends JpaRepository<WorkRecord, Long> {
    Optional<WorkRecord> findFirstByEmployeeIdAndCheckoutTimeIsNull(Long employeeId);
    List<WorkRecord> findByEmployeeId(Long employeeId);
}