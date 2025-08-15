package com.alura.literalura.repository;


import com.alura.literalura.model.Autor;
import com.alura.literalura.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Long> {

    // Buscar autor por nombre exacto (para evitar duplicados)
    Optional<Autor> findByNombreIgnoreCase(String nombre);

    // Buscar autores vivos en un año determinado
    @Query("SELECT a FROM Autor a WHERE a.fechaDeNacimiento <= :ano AND (a.fechaDeFallecimiento IS NULL OR a.fechaDeFallecimiento >= :ano)")
    List<Autor> buscarAutoresVivosEnAno(@Param("ano") Integer ano);

    // Buscar autores por nombre que contenga cierta cadena
    List<Autor> findByNombreContainsIgnoreCase(String nombre);

    // Autores ordenados por fecha de nacimiento
    List<Autor> findByOrderByFechaDeNacimientoAsc();
}
