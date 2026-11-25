package com.gcc.ev4.service.impl;

import com.gcc.ev4.dto.AnimalRespuesta;
import com.gcc.ev4.dto.AnimalSolicitud;
import com.gcc.ev4.exception.RecursoNoEncontradoException;
import com.gcc.ev4.exception.ReglaNegocioException;
import com.gcc.ev4.log.MensajesLog;
import com.gcc.ev4.mapper.AnimalMapper;
import com.gcc.ev4.model.Animal;
import com.gcc.ev4.repository.AnimalRepository;
import com.gcc.ev4.service.IAnimalService;
import com.gcc.ev4.util.Mensajes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class AnimalServiceImpl implements IAnimalService {

    private static final Logger log = LoggerFactory.getLogger(AnimalServiceImpl.class);

    private final AnimalRepository animalRepository;
    private final AnimalMapper animalMapper;

    public AnimalServiceImpl(AnimalRepository animalRepository, AnimalMapper animalMapper) {
        this.animalRepository = animalRepository;
        this.animalMapper = animalMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<AnimalRespuesta> listar(boolean activo) {
        List<AnimalRespuesta> animales = animalRepository.findByActivoOrderByIdAsc(activo).stream()
                .map(animalMapper::aRespuesta).toList();
        log.info(MensajesLog.ANIMALES_LISTADOS, activo, animales.size());
        return animales;
    }

    @Override
    @Transactional(readOnly = true)
    public AnimalRespuesta obtenerDetalle(Long id) {
        AnimalRespuesta animal = animalMapper.aRespuesta(buscarActivo(id));
        log.info(MensajesLog.ANIMAL_CONSULTADO, id);
        return animal;
    }

    @Override
    @Transactional
    public AnimalRespuesta registrar(AnimalSolicitud solicitud) {
        Animal animal = animalRepository.save(animalMapper.aEntidad(solicitud));
        log.info(MensajesLog.ANIMAL_REGISTRADO, animal.getId(), animal.getNombre(), animal.getEdad());
        return animalMapper.aRespuesta(animal);
    }

    @Override
    @Transactional
    public AnimalRespuesta actualizar(Long id, AnimalSolicitud solicitud) {
        Animal animal = buscarActivo(id);
        String nombreAnterior = animal.getNombre();
        Integer edadAnterior = animal.getEdad();
        animalMapper.actualizar(animal, solicitud);
        log.info(MensajesLog.ANIMAL_ACTUALIZADO, id, nombreAnterior, animal.getNombre(), edadAnterior, animal.getEdad());
        return animalMapper.aRespuesta(animal);
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        Animal animal = buscarActivo(id);
        animal.setActivo(false);
        log.info(MensajesLog.ANIMAL_ELIMINADO, id, animal.getNombre());
    }

    @Override
    @Transactional
    public AnimalRespuesta restaurar(Long id) {
        Animal animal = animalRepository.findById(id).orElseThrow(this::noEncontrado);
        if (animal.isActivo()) {
            throw new ReglaNegocioException(Mensajes.ANIMAL_NO_ELIMINADO);
        }
        animal.setActivo(true);
        log.info(MensajesLog.ANIMAL_RESTAURADO, id, animal.getNombre());
        return animalMapper.aRespuesta(animal);
    }

    private Animal buscarActivo(Long id) {
        return animalRepository.findByIdAndActivoTrue(id).orElseThrow(this::noEncontrado);
    }

    private RecursoNoEncontradoException noEncontrado() {
        return new RecursoNoEncontradoException(Mensajes.ANIMAL_NO_EXISTE);
    }
}
