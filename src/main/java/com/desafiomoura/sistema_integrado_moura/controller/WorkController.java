package com.desafiomoura.sistema_integrado_moura.controller;

import com.desafiomoura.sistema_integrado_moura.entity.WorkRecord;
import com.desafiomoura.sistema_integrado_moura.service.WorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Page<WorkRecord>> listAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String date
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("checkinTime").descending());

        return ResponseEntity.ok(workService.findAll(pageable, name, date));
    }
}