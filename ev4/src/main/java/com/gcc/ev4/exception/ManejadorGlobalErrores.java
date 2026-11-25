package com.gcc.ev4.exception;

import com.gcc.ev4.response.RespuestaApi;
import com.gcc.ev4.util.Mensajes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.resource.NoResourceFoundException;
import java.util.List;

@RestControllerAdvice
public class ManejadorGlobalErrores {

    private static final Logger log = LoggerFactory.getLogger(ManejadorGlobalErrores.class);

    @ExceptionHandler(RecursoNoEncontradoException.class)
    public ResponseEntity<RespuestaApi<Void>> noEncontrado(RecursoNoEncontradoException ex) {
        return responder(HttpStatus.NOT_FOUND, ex.getMessage(), null);
    }

    @ExceptionHandler(ReglaNegocioException.class)
    public ResponseEntity<RespuestaApi<Void>> reglaNegocio(ReglaNegocioException ex) {
        return responder(HttpStatus.CONFLICT, ex.getMessage(), null);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<RespuestaApi<Void>> validacion(MethodArgumentNotValidException ex) {
        List<String> errores = ex.getBindingResult().getFieldErrors().stream()
                .map(e -> e.getField() + ": " + e.getDefaultMessage()).toList();
        return responder(HttpStatus.BAD_REQUEST, Mensajes.DATOS_INVALIDOS, errores);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<RespuestaApi<Void>> cuerpoInvalido() {
        return responder(HttpStatus.BAD_REQUEST, Mensajes.CUERPO_INVALIDO,
                List.of(Mensajes.DETALLE_CUERPO_INVALIDO));
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<RespuestaApi<Void>> parametroInvalido(MethodArgumentTypeMismatchException ex) {
        return responder(HttpStatus.BAD_REQUEST, Mensajes.PARAMETRO_INVALIDO.formatted(ex.getName()), null);
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<RespuestaApi<Void>> rutaNoExiste() {
        return responder(HttpStatus.NOT_FOUND, Mensajes.RUTA_NO_EXISTE, null);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<RespuestaApi<Void>> metodoNoPermitido(HttpRequestMethodNotSupportedException ex) {
        return responder(HttpStatus.METHOD_NOT_ALLOWED, Mensajes.METODO_NO_PERMITIDO.formatted(ex.getMethod()), null);
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseEntity<RespuestaApi<Void>> tipoContenidoNoSoportado() {
        return responder(HttpStatus.UNSUPPORTED_MEDIA_TYPE, Mensajes.TIPO_CONTENIDO_NO_SOPORTADO, null);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<RespuestaApi<Void>> general(Exception ex) {
        log.error("Error no controlado", ex);
        return responder(HttpStatus.INTERNAL_SERVER_ERROR, Mensajes.ERROR_INTERNO, null);
    }

    private ResponseEntity<RespuestaApi<Void>> responder(HttpStatus status, String mensaje, List<String> errores) {
        return ResponseEntity.status(status).body(RespuestaApi.error(mensaje, errores));
    }
}
