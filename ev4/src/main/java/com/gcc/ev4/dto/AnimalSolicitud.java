package com.gcc.ev4.dto;

import com.gcc.ev4.util.Mensajes;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AnimalSolicitud {

    public static final int EDAD_MAXIMA = 50;

    @NotBlank(message = Mensajes.NOMBRE_OBLIGATORIO)
    @Size(max = 100, message = Mensajes.NOMBRE_MUY_LARGO)
    private String nombre;

    @NotBlank(message = Mensajes.ESPECIE_OBLIGATORIA)
    @Size(max = 50, message = Mensajes.ESPECIE_MUY_LARGA)
    private String especie;

    @NotNull(message = Mensajes.EDAD_OBLIGATORIA)
    @Min(value = 0, message = Mensajes.EDAD_NEGATIVA)
    @Max(value = EDAD_MAXIMA, message = Mensajes.EDAD_SUPERA_MAXIMO)
    private Integer edad;
}
