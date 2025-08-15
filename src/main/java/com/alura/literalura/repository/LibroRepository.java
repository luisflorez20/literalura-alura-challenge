package com.alura.literalura.repository;

import com.alura.literalura.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface LibroRepository extends JpaRepository<Libro, Long> {

    // Buscar libro por título (para evitar duplicados)
    Optional<Libro> findByTituloContainsIgnoreCase(String titulo);

    // Listar libros por idioma
    List<Libro> findByIdiomaContainsIgnoreCase(String idioma);

    // Consulta personalizada para buscar por idiomas específicos
    @Query("SELECT l FROM Libro l WHERE l.idioma ILIKE %:idioma%")
    List<Libro> buscarPorIdioma(@Param("idioma") String idioma);

    // Top 10 libros más descargados
    List<Libro> findTop10ByOrderByNumeroDeDescargasDesc();
}
