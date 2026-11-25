package com.gcc.ev4.log;

public final class MensajesLog {

    private MensajesLog() {
    }

    public static final String PETICION = "{} {} -> {} ({} ms)";

    public static final String ANIMALES_LISTADOS = "Listado de animales: activo={}, total={}";
    public static final String ANIMAL_CONSULTADO = "Animal consultado: id={}";
    public static final String ANIMAL_REGISTRADO = "Animal registrado: id={}, nombre='{}', edad={}";
    public static final String ANIMAL_ACTUALIZADO = "Animal actualizado: id={}, nombre='{}' -> '{}', edad={} -> {}";
    public static final String ANIMAL_ELIMINADO = "Animal eliminado (logico): id={}, nombre='{}'";
    public static final String ANIMAL_RESTAURADO = "Animal restaurado: id={}, nombre='{}'";

    public static final String RECURSO_NO_ENCONTRADO = "Recurso no encontrado: {}";
    public static final String REGLA_NEGOCIO = "Regla de negocio incumplida: {}";
    public static final String VALIDACION_FALLIDA = "Validacion fallida: {}";
    public static final String CUERPO_INVALIDO = "Cuerpo de la peticion invalido: {}";
    public static final String ERROR_BASE_DATOS = "Error de base de datos";
    public static final String ERROR_NO_CONTROLADO = "Error no controlado";
}
