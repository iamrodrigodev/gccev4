package com.gcc.ev4.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class AnimalRespuesta {

    private Long id;
    private String nombre;
    private Integer edad;
    private LocalDateTime fechaRegistro;
}
