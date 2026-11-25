package com.gcc.ev4.controller;

import com.gcc.ev4.dto.AnimalRespuesta;
import com.gcc.ev4.dto.AnimalSolicitud;
import com.gcc.ev4.response.RespuestaApi;
import com.gcc.ev4.service.IAnimalService;
import com.gcc.ev4.util.Mensajes;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/animales")
public class AnimalController {

    private final IAnimalService animalService;

    public AnimalController(IAnimalService animalService) {
        this.animalService = animalService;
    }

    @GetMapping
    public ResponseEntity<RespuestaApi<List<AnimalRespuesta>>> listar() {
        return ResponseEntity.ok(RespuestaApi.ok(Mensajes.ANIMALES_LISTADOS, animalService.listar()));
    }

    @GetMapping("/{animalId}")
    public ResponseEntity<RespuestaApi<AnimalRespuesta>> verDetalleAnimal(@PathVariable Long animalId) {
        return ResponseEntity.ok(RespuestaApi.ok(Mensajes.ANIMAL_ENCONTRADO, animalService.obtenerDetalle(animalId)));
    }

    @PostMapping
    public ResponseEntity<RespuestaApi<AnimalRespuesta>> registrar(@Valid @RequestBody AnimalSolicitud solicitud) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(RespuestaApi.ok(Mensajes.ANIMAL_REGISTRADO, animalService.registrar(solicitud)));
    }

    @PutMapping("/{animalId}")
    public ResponseEntity<RespuestaApi<AnimalRespuesta>> actualizar(@PathVariable Long animalId,
                                                                   @Valid @RequestBody AnimalSolicitud solicitud) {
        return ResponseEntity.ok(RespuestaApi.ok(Mensajes.ANIMAL_ACTUALIZADO, animalService.actualizar(animalId, solicitud)));
    }

    @DeleteMapping("/{animalId}")
    public ResponseEntity<RespuestaApi<Void>> eliminar(@PathVariable Long animalId) {
        animalService.eliminar(animalId);
        return ResponseEntity.ok(RespuestaApi.ok(Mensajes.ANIMAL_ELIMINADO, null));
    }

    @PatchMapping("/{animalId}/restaurar")
    public ResponseEntity<RespuestaApi<AnimalRespuesta>> restaurar(@PathVariable Long animalId) {
        return ResponseEntity.ok(RespuestaApi.ok(Mensajes.ANIMAL_RESTAURADO, animalService.restaurar(animalId)));
    }
}
