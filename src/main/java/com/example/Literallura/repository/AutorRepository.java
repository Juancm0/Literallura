package com.example.Literallura.repository;

import com.example.Literallura.entity.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Long> {

    @Query("SELECT a FROM Autor a WHERE :anio BETWEEN a.anioNacimiento AND COALESCE(a.anioFallecimiento, :anio)")
    List<Autor> findAutoresVivosEnAnio(int anio);

    Optional<Autor> findByNombre(String nombre);
}
