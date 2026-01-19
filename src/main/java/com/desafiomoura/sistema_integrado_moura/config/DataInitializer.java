package com.desafiomoura.sistema_integrado_moura.config;

import com.desafiomoura.sistema_integrado_moura.entity.Employee;
import com.desafiomoura.sistema_integrado_moura.entity.UserRole;
import com.desafiomoura.sistema_integrado_moura.repository.EmployeeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner seedUsers(EmployeeRepository repository) {
        return args -> {
            if (repository.findByEmail("admin@moura.com").isEmpty()) {
                Employee admin = new Employee();
                admin.setName("Gestor Moura");
                admin.setEmail("admin@moura.com");
                admin.setPassword("123");
                admin.setRole(UserRole.ADMIN);

                repository.save(admin);
            }

            if (repository.findByEmail("funcionario@moura.com").isEmpty()) {
                Employee user = new Employee();
                user.setName("João Silva");
                user.setEmail("funcionario@moura.com");
                user.setPassword("123");
                user.setRole(UserRole.USER);

                repository.save(user);
            }
        };
    }
}