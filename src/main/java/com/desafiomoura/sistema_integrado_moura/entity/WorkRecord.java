package com.desafiomoura.sistema_integrado_moura.entity;

import java.time.LocalDateTime;

public class WorkRecord  {
    private Long id;
    private LocalDateTime checkinTime;
    private LocalDateTime checkoutTime;
    private Long duration;

    private Employee employee;

}
