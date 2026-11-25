package com.gcc.ev4.service;

import com.gcc.ev4.dto.AnimalRespuesta;
import com.gcc.ev4.dto.AnimalSolicitud;
import java.util.List;

public interface IAnimalService {

    List<AnimalRespuesta> listar(boolean activo);

    AnimalRespuesta obtenerDetalle(Long id);

    AnimalRespuesta registrar(AnimalSolicitud solicitud);

    AnimalRespuesta actualizar(Long id, AnimalSolicitud solicitud);

    void eliminar(Long id);

    AnimalRespuesta restaurar(Long id);
}
