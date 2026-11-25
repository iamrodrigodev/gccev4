package com.gcc.ev4.exception;

import java.io.Serial;

public class RecursoNoEncontradoException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public RecursoNoEncontradoException(String mensaje) {
        super(mensaje);
    }
}
