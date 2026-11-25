package com.gcc.ev4.util;

import com.gcc.ev4.dto.AnimalSolicitud;

public final class Mensajes {

    private Mensajes() {
    }

    public static final String ANIMALES_LISTADOS = "Animales listados";
    public static final String ANIMAL_ENCONTRADO = "Animal encontrado";
    public static final String ANIMAL_REGISTRADO = "Animal registrado";
    public static final String ANIMAL_ACTUALIZADO = "Animal actualizado";
    public static final String ANIMAL_ELIMINADO = "Animal eliminado";
    public static final String ANIMAL_RESTAURADO = "Animal restaurado";

    public static final String ANIMAL_NO_EXISTE = "No existe el animal con id %d";
    public static final String ANIMAL_NO_ELIMINADO = "El animal con id %d no está eliminado";

    public static final String NOMBRE_OBLIGATORIO = "El nombre es obligatorio";
    public static final String NOMBRE_MUY_LARGO = "El nombre no puede superar 100 caracteres";
    public static final String EDAD_OBLIGATORIA = "La edad es obligatoria";
    public static final String EDAD_NEGATIVA = "La edad no puede ser negativa";
    public static final String EDAD_SUPERA_MAXIMO = "La edad no puede ser mayor a " + AnimalSolicitud.EDAD_MAXIMA + " años";

    public static final String DATOS_INVALIDOS = "Datos inválidos";
    public static final String CUERPO_INVALIDO = "Cuerpo de la petición inválido";
    public static final String DETALLE_CUERPO_INVALIDO = "Verifique el formato JSON; la edad debe ser un número entero";
    public static final String PARAMETRO_INVALIDO = "Parámetro inválido: %s";
    public static final String METODO_NO_PERMITIDO = "Método %s no permitido en esta ruta";
    public static final String TIPO_CONTENIDO_NO_SOPORTADO = "El cuerpo debe enviarse como application/json";
    public static final String RUTA_NO_EXISTE = "La ruta solicitada no existe";
    public static final String ERROR_INTERNO = "Error interno del servidor";
}
