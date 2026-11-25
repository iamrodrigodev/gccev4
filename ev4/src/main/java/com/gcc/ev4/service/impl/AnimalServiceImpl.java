package com.gcc.ev4.service.impl;

import com.gcc.ev4.dto.AnimalRespuesta;
import com.gcc.ev4.dto.AnimalSolicitud;
import com.gcc.ev4.exception.RecursoNoEncontradoException;
import com.gcc.ev4.exception.ReglaNegocioException;
import com.gcc.ev4.mapper.AnimalMapper;
import com.gcc.ev4.model.Animal;
import com.gcc.ev4.repository.AnimalRepository;
import com.gcc.ev4.service.IAnimalService;
import com.gcc.ev4.util.Mensajes;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class AnimalServiceImpl implements IAnimalService {

    private final AnimalRepository animalRepository;
    private final AnimalMapper animalMapper;

    public AnimalServiceImpl(AnimalRepository animalRepository, AnimalMapper animalMapper) {
        this.animalRepository = animalRepository;
        this.animalMapper = animalMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<AnimalRespuesta> listar() {
        return animalRepository.findByActivoTrueOrderByIdAsc().stream().map(animalMapper::aRespuesta).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public AnimalRespuesta obtenerDetalle(Long id) {
        return animalMapper.aRespuesta(buscarActivo(id));
    }

    @Override
    @Transactional
    public AnimalRespuesta registrar(AnimalSolicitud solicitud) {
        return animalMapper.aRespuesta(animalRepository.save(animalMapper.aEntidad(solicitud)));
    }

    @Override
    @Transactional
    public AnimalRespuesta actualizar(Long id, AnimalSolicitud solicitud) {
        Animal animal = buscarActivo(id);
        animalMapper.actualizar(animal, solicitud);
        return animalMapper.aRespuesta(animal);
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        buscarActivo(id).setActivo(false);
    }

    @Override
    @Transactional
    public AnimalRespuesta restaurar(Long id) {
        Animal animal = animalRepository.findById(id).orElseThrow(() -> noEncontrado(id));
        if (animal.isActivo()) {
            throw new ReglaNegocioException(Mensajes.ANIMAL_NO_ELIMINADO.formatted(id));
        }
        animal.setActivo(true);
        return animalMapper.aRespuesta(animal);
    }

    private Animal buscarActivo(Long id) {
        return animalRepository.findByIdAndActivoTrue(id).orElseThrow(() -> noEncontrado(id));
    }

    private RecursoNoEncontradoException noEncontrado(Long id) {
        return new RecursoNoEncontradoException(Mensajes.ANIMAL_NO_EXISTE.formatted(id));
    }
}
