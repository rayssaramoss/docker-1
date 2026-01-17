package com.desafiomoura.sistema_integrado_moura.controller;

import com.desafiomoura.sistema_integrado_moura.dto.LoginRequest;
import com.desafiomoura.sistema_integrado_moura.dto.LoginResponse;
import com.desafiomoura.sistema_integrado_moura.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }
}