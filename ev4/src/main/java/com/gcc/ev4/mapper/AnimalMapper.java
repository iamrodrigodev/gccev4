package com.gcc.ev4.mapper;

import com.gcc.ev4.dto.AnimalRespuesta;
import com.gcc.ev4.dto.AnimalSolicitud;
import com.gcc.ev4.model.Animal;
import org.springframework.stereotype.Component;

@Component
public class AnimalMapper {

    public Animal aEntidad(AnimalSolicitud solicitud) {
        Animal animal = new Animal();
        actualizar(animal, solicitud);
        return animal;
    }

    public void actualizar(Animal animal, AnimalSolicitud solicitud) {
        animal.setNombre(solicitud.getNombre().trim());
        animal.setEdad(solicitud.getEdad());
    }

    public AnimalRespuesta aRespuesta(Animal animal) {
        AnimalRespuesta respuesta = new AnimalRespuesta();
        respuesta.setId(animal.getId());
        respuesta.setNombre(animal.getNombre());
        respuesta.setEdad(animal.getEdad());
        respuesta.setFechaRegistro(animal.getFechaRegistro());
        return respuesta;
    }
}
