package com.gcc.ev4.repository;

import com.gcc.ev4.model.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface AnimalRepository extends JpaRepository<Animal, Long> {

    List<Animal> findByActivoOrderByIdAsc(boolean activo);

    Optional<Animal> findByIdAndActivoTrue(Long id);
}
