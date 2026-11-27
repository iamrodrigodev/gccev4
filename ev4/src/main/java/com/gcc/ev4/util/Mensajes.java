package com.gcc.ev4.util;

import com.gcc.ev4.dto.AnimalSolicitud;

public final class Mensajes {

    private Mensajes() {
    }

    public static final String ANIMALES_LISTADOS = "Animales cargados correctamente";
    public static final String ANIMAL_ENCONTRADO = "Animal encontrado";
    public static final String ANIMAL_REGISTRADO = "Animal registrado correctamente";
    public static final String ANIMAL_ACTUALIZADO = "Cambios guardados correctamente";
    public static final String ANIMAL_ELIMINADO = "Animal eliminado. Puedes restaurarlo desde Eliminados";
    public static final String ANIMAL_RESTAURADO = "Animal restaurado correctamente";

    public static final String ANIMAL_NO_EXISTE = "No encontramos el animal solicitado";
    public static final String ANIMAL_NO_ELIMINADO = "Este animal ya está activo";

    public static final String NOMBRE_OBLIGATORIO = "Ingresa el nombre del animal";
    public static final String NOMBRE_MUY_LARGO = "El nombre puede tener como máximo 100 caracteres";
    public static final String ESPECIE_OBLIGATORIA = "Ingresa la especie del animal";
    public static final String ESPECIE_MUY_LARGA = "La especie puede tener como máximo 50 caracteres";
    public static final String EDAD_OBLIGATORIA = "Ingresa la edad del animal";
    public static final String EDAD_NEGATIVA = "La edad no puede ser negativa";
    public static final String EDAD_SUPERA_MAXIMO = "La edad debe ser de " + AnimalSolicitud.EDAD_MAXIMA + " años como máximo";

    public static final String DATOS_INVALIDOS = "Revisa los datos ingresados";
    public static final String EDAD_NO_ENTERA = "La edad debe ser un número entero, sin decimales";
    public static final String SOLICITUD_INVALIDA = "No pudimos procesar la solicitud";
    public static final String RECURSO_NO_EXISTE = "Lo que buscas no existe";
    public static final String SERVICIO_NO_DISPONIBLE = "El servicio no está disponible en este momento. Inténtalo de nuevo en unos minutos";
    public static final String ERROR_INTERNO = "Ocurrió un problema inesperado. Inténtalo de nuevo";
}
