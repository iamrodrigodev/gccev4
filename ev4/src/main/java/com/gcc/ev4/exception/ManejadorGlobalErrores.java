package com.gcc.ev4.exception;

import com.gcc.ev4.log.MensajesLog;
import com.gcc.ev4.response.RespuestaApi;
import com.gcc.ev4.util.Mensajes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.transaction.TransactionException;
import org.springframework.validation.FieldError;
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
        log.warn(MensajesLog.RECURSO_NO_ENCONTRADO, ex.getMessage());
        return responder(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(ReglaNegocioException.class)
    public ResponseEntity<RespuestaApi<Void>> reglaNegocio(ReglaNegocioException ex) {
        log.warn(MensajesLog.REGLA_NEGOCIO, ex.getMessage());
        return responder(HttpStatus.CONFLICT, ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<RespuestaApi<Void>> validacion(MethodArgumentNotValidException ex) {
        List<String> errores = ex.getBindingResult().getFieldErrors().stream()
                .map((FieldError error) -> error.getDefaultMessage()).toList();
        log.warn(MensajesLog.VALIDACION_FALLIDA, errores);
        return responder(HttpStatus.BAD_REQUEST, Mensajes.DATOS_INVALIDOS, errores);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<RespuestaApi<Void>> cuerpoInvalido(HttpMessageNotReadableException ex) {
        log.warn(MensajesLog.CUERPO_INVALIDO, ex.getMostSpecificCause().getMessage().lines().findFirst().orElse(""));
        return responder(HttpStatus.BAD_REQUEST, Mensajes.DATOS_INVALIDOS, List.of(Mensajes.EDAD_NO_ENTERA));
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<RespuestaApi<Void>> parametroInvalido() {
        return responder(HttpStatus.BAD_REQUEST, Mensajes.SOLICITUD_INVALIDA);
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<RespuestaApi<Void>> rutaNoExiste() {
        return responder(HttpStatus.NOT_FOUND, Mensajes.RECURSO_NO_EXISTE);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<RespuestaApi<Void>> metodoNoPermitido() {
        return responder(HttpStatus.METHOD_NOT_ALLOWED, Mensajes.SOLICITUD_INVALIDA);
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseEntity<RespuestaApi<Void>> tipoContenidoNoSoportado() {
        return responder(HttpStatus.UNSUPPORTED_MEDIA_TYPE, Mensajes.SOLICITUD_INVALIDA);
    }

    @ExceptionHandler({DataAccessException.class, TransactionException.class})
    public ResponseEntity<RespuestaApi<Void>> baseDatosNoDisponible(Exception ex) {
        log.error(MensajesLog.ERROR_BASE_DATOS, ex);
        return responder(HttpStatus.SERVICE_UNAVAILABLE, Mensajes.SERVICIO_NO_DISPONIBLE);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<RespuestaApi<Void>> general(Exception ex) {
        log.error(MensajesLog.ERROR_NO_CONTROLADO, ex);
        return responder(HttpStatus.INTERNAL_SERVER_ERROR, Mensajes.ERROR_INTERNO);
    }

    private ResponseEntity<RespuestaApi<Void>> responder(HttpStatus status, String mensaje) {
        return ResponseEntity.status(status).body(RespuestaApi.of(mensaje));
    }

    private ResponseEntity<RespuestaApi<Void>> responder(HttpStatus status, String mensaje, List<String> errores) {
        return ResponseEntity.status(status).body(RespuestaApi.conErrores(mensaje, errores));
    }
}
