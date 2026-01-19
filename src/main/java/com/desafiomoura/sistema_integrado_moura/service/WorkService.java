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
import java.time.LocalTime;
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
        LocalDateTime start;
        LocalDateTime end;

        if (dateStr != null && !dateStr.trim().isEmpty()) {
            try {
                LocalDate date = LocalDate.parse(dateStr);

                start = date.atStartOfDay();
                end = date.atTime(LocalTime.MAX);
            } catch (Exception e) {
                LocalDate today = LocalDate.now();
                start = today.atStartOfDay();
                end = today.atTime(LocalTime.MAX);
            }
        }
        else {
            LocalDate today = LocalDate.now();
            start = today.atStartOfDay();
            end = today.atTime(LocalTime.MAX);
        }

        Page<WorkRecord> resultado = workRecordRepository.buscarPontosComFiltro(name, start, end, pageable);

        return resultado;
    }
}