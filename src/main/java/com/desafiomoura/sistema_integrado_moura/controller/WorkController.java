package com.desafiomoura.sistema_integrado_moura.controller;

import com.desafiomoura.sistema_integrado_moura.entity.WorkRecord;
import com.desafiomoura.sistema_integrado_moura.service.WorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/work")
@CrossOrigin(origins = "*")
public class WorkController {

    @Autowired
    private WorkService workService;

    @PostMapping("/checkin")
    public ResponseEntity<WorkRecord> checkIn(@RequestParam Long employeeId) {
        return ResponseEntity.ok(workService.checkIn(employeeId));
    }

    @PostMapping("/checkout")
    public ResponseEntity<WorkRecord> checkOut(@RequestParam Long employeeId) {
        return ResponseEntity.ok(workService.checkOut(employeeId));
    }

    @GetMapping("/list")
    public ResponseEntity<List<WorkRecord>> listAll() {
        return ResponseEntity.ok(workService.findAll());
    }
}