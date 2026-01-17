package com.desafiomoura.sistema_integrado_moura.service;

import com.desafiomoura.sistema_integrado_moura.entity.Employee;
import com.desafiomoura.sistema_integrado_moura.entity.WorkRecord;
import com.desafiomoura.sistema_integrado_moura.repository.EmployeeRepository;
import com.desafiomoura.sistema_integrado_moura.repository.WorkRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class WorkService {

    @Autowired
    private WorkRecordRepository workRecordRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    public WorkRecord checkIn(Long employeeId) {
        if (workRecordRepository.findFirstByEmployeeIdAndCheckoutTimeIsNull(employeeId).isPresent()) {
            throw new RuntimeException("Você já tem um ponto aberto!");
        }

        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Funcionário não encontrado"));

        WorkRecord record = new WorkRecord();
        record.setEmployee(employee);
        record.setCheckinTime(LocalDateTime.now());

        return workRecordRepository.save(record);
    }

    public WorkRecord checkOut(Long employeeId) {
        WorkRecord record = workRecordRepository.findFirstByEmployeeIdAndCheckoutTimeIsNull(employeeId)
                .orElseThrow(() -> new RuntimeException("Nenhum check-in aberto encontrado."));

        record.setCheckoutTime(LocalDateTime.now());

        long duration = Duration.between(record.getCheckinTime(), record.getCheckoutTime()).toMinutes();
        record.setDuration(duration);

        return workRecordRepository.save(record);
    }

    public List<WorkRecord> findAll() {
        return workRecordRepository.findAll();
    }

    public Page<WorkRecord> findAll(Pageable pageable, String name, String dateStr) {
        LocalDateTime startOfDay = null;
        LocalDateTime endOfDay = null;

        if (dateStr != null && !dateStr.isEmpty()) {
            try {
                LocalDate date = LocalDate.parse(dateStr);
                startOfDay = date.atStartOfDay();
                endOfDay = date.atTime(23, 59, 59);
            } catch (Exception e) {
            }
        }

        String nameFilter = "%";
        if (name != null && !name.trim().isEmpty()) {
            nameFilter = "%" + name.toLowerCase() + "%";
        }

        return workRecordRepository.buscarPontosComFiltro(nameFilter, startOfDay, endOfDay, pageable);
    }
}