package com.gcc.ev4.response;

import lombok.Data;
import java.util.List;

@Data
public class RespuestaApi<T> {

    private boolean exito;
    private String mensaje;
    private T datos;
    private List<String> errores;

    public static <T> RespuestaApi<T> ok(String mensaje, T datos) {
        RespuestaApi<T> respuesta = new RespuestaApi<>();
        respuesta.setExito(true);
        respuesta.setMensaje(mensaje);
        respuesta.setDatos(datos);
        return respuesta;
    }

    public static <T> RespuestaApi<T> error(String mensaje, List<String> errores) {
        RespuestaApi<T> respuesta = new RespuestaApi<>();
        respuesta.setExito(false);
        respuesta.setMensaje(mensaje);
        respuesta.setErrores(errores);
        return respuesta;
    }
}
