package com.gcc.ev4.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@JsonPropertyOrder({"id", "nombre", "especie", "edad", "fechaRegistro"})
public class AnimalRespuesta {

    private Long id;
    private String nombre;
    private String especie;
    private Integer edad;
    private LocalDateTime fechaRegistro;
}
