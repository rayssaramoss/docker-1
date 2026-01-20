package com.desafiomoura.sistema_integrado_moura.config;

import com.desafiomoura.sistema_integrado_moura.entity.Employee;
import com.desafiomoura.sistema_integrado_moura.repository.EmployeeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner initDatabase(EmployeeRepository repository) {
        return args -> {
            if (repository.count() == 0) {
                Employee admin = new Employee();
                admin.setName("Abner Barretto");
                admin.setEmail("abner@moura.com");
                admin.setPassword("123456"); // Na vida real, criptografaríamos isso!

                repository.save(admin);
                System.out.println("✅ Usuário ADMIN criado com sucesso! (ID: " + admin.getId() + ")");
            }
        };
    }
}