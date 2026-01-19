package com.desafiomoura.sistema_integrado_moura.controller;

import com.desafiomoura.sistema_integrado_moura.entity.WorkRecord;
import com.desafiomoura.sistema_integrado_moura.service.WorkService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/work")
@CrossOrigin(origins = "*")
@Tag(name = "Ponto Eletrônico", description = "Endpoints para registro de entrada/saída e consulta de pontos")
public class WorkController {

    @Autowired
    private WorkService workService;

    @PostMapping("/checkin")
    @Operation(summary = "Registrar entrada", description = "Registra o horário de entrada de um funcionário")
    public ResponseEntity<WorkRecord> checkIn(@RequestParam Long employeeId) {
        return ResponseEntity.ok(workService.checkIn(employeeId));
    }

    @PostMapping("/checkout")
    @Operation(summary = "Registrar saída", description = "Registra o horário de saída de um funcionário")
    public ResponseEntity<WorkRecord> checkOut(@RequestParam Long employeeId) {
        return ResponseEntity.ok(workService.checkOut(employeeId));
    }

    @GetMapping("/list")
    @Operation(summary = "Listar registros de ponto", description = "Retorna lista paginada")
    public ResponseEntity<Page<WorkRecord>> listAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam Map<String, String> allParams
    ) {
        System.out.println("🕵️ PARÂMETROS RECEBIDOS DO FRONT: " + allParams);

        String name = allParams.get("name");

        String dateStr = allParams.get("dateStr");
        if (dateStr == null) dateStr = allParams.get("date");     // Tenta 'date'
        if (dateStr == null) dateStr = allParams.get("data");     // Tenta 'data' (comum em front br)
        if (dateStr == null) dateStr = allParams.get("filterDate"); // Tenta 'filterDate'

        Pageable pageable = PageRequest.of(page, size, Sort.by("checkinTime").descending());

        return ResponseEntity.ok(workService.findAll(pageable, name, dateStr));
    }
}