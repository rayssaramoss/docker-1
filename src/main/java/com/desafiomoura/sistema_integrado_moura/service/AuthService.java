package com.desafiomoura.sistema_integrado_moura.service;

import com.desafiomoura.sistema_integrado_moura.dto.LoginRequest;
import com.desafiomoura.sistema_integrado_moura.dto.LoginResponse;
import com.desafiomoura.sistema_integrado_moura.entity.Employee;
import com.desafiomoura.sistema_integrado_moura.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public LoginResponse login(LoginRequest request) {
        Employee employee = employeeRepository.findByEmail(request.email())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        if (!employee.getPassword().equals(request.password())) {
            throw new RuntimeException("Senha incorreta");
        }

        return new LoginResponse(employee.getId(), employee.getName(), "token-fake-123");
    }
}