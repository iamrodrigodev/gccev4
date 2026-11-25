package com.gcc.ev4.exception;

import java.io.Serial;

public class ReglaNegocioException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public ReglaNegocioException(String mensaje) {
        super(mensaje);
    }
}
