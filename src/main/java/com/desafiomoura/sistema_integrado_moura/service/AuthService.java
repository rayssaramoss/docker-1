package com.desafiomoura.sistema_integrado_moura.service;

import com.desafiomoura.sistema_integrado_moura.dto.LoginRequest;
import com.desafiomoura.sistema_integrado_moura.dto.LoginResponse;
import com.desafiomoura.sistema_integrado_moura.entity.Employee;
import com.desafiomoura.sistema_integrado_moura.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public LoginResponse login(LoginRequest request) {
        Optional<Employee> userOptional = employeeRepository.findByEmail(request.email());

        if (userOptional.isEmpty()) {
            throw new RuntimeException("Usuário ou senha inválidos");
        }

        Employee employee = userOptional.get();

        if (!employee.getPassword().trim().equals(request.password().trim())) {
            throw new RuntimeException("Usuário ou senha inválidos");
        }

        String role = employee.getRole() != null ? employee.getRole().toString() : "USER";

        return new LoginResponse(
                employee.getId(),
                employee.getName(),
                "token-fake-123",
                role
        );
    }
}