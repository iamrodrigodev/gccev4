package com.gcc.ev4.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"mensaje", "datos", "errores"})
public class RespuestaApi<T> {

    private String mensaje;
    private T datos;
    private List<String> errores;

    public static <T> RespuestaApi<T> of(String mensaje) {
        RespuestaApi<T> respuesta = new RespuestaApi<>();
        respuesta.setMensaje(mensaje);
        return respuesta;
    }

    public static <T> RespuestaApi<T> of(String mensaje, T datos) {
        RespuestaApi<T> respuesta = of(mensaje);
        respuesta.setDatos(datos);
        return respuesta;
    }

    public static <T> RespuestaApi<T> conErrores(String mensaje, List<String> errores) {
        RespuestaApi<T> respuesta = of(mensaje);
        respuesta.setErrores(errores);
        return respuesta;
    }
}
