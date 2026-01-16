package com.desafiomoura.sistema_integrado_moura.dto;

import java.time.LocalDateTime;

public record WorkRecordDTO(
        Long id,
        String employeeName,
        LocalDateTime checkinTime,
        LocalDateTime checkoutTime,
        Long duration
) {}