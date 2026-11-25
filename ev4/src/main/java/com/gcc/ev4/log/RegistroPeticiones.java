package com.gcc.ev4.log;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jspecify.annotations.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;

@Component
public class RegistroPeticiones extends OncePerRequestFilter {

    private static final Logger log = LoggerFactory.getLogger(RegistroPeticiones.class);

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest peticion, @NonNull HttpServletResponse respuesta,
                                    @NonNull FilterChain cadena) throws ServletException, IOException {
        long inicio = System.currentTimeMillis();
        try {
            cadena.doFilter(peticion, respuesta);
        } finally {
            String ruta = peticion.getQueryString() == null
                    ? peticion.getRequestURI()
                    : peticion.getRequestURI() + "?" + peticion.getQueryString();
            log.info(MensajesLog.PETICION, peticion.getMethod(), ruta, respuesta.getStatus(),
                    System.currentTimeMillis() - inicio);
        }
    }
}
