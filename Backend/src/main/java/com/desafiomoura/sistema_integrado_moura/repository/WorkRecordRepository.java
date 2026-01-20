package com.desafiomoura.sistema_integrado_moura.repository;

import com.desafiomoura.sistema_integrado_moura.entity.WorkRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface WorkRecordRepository extends JpaRepository<WorkRecord, Long> {

    Optional<WorkRecord> findFirstByEmployeeIdAndCheckoutTimeIsNull(Long employeeId);

    @Query("SELECT w FROM WorkRecord w " +
            "WHERE (w.checkinTime >= :startDate) " +
            "AND (w.checkinTime <= :endDate) " +
            "ORDER BY w.checkinTime DESC")
    Page<WorkRecord> buscarPontosComFiltro(
            @Param("name") String name,
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate,
            Pageable pageable
    );
}