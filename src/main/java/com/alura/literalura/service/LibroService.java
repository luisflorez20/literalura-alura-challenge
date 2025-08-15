package com.alura.literalura.service;

import com.alura.literalura.dto.DatosAutor;
import com.alura.literalura.dto.DatosLibro;
import com.alura.literalura.dto.DatosRespuesta;
import com.alura.literalura.model.Autor;
import com.alura.literalura.model.Libro;
import com.alura.literalura.repository.AutorRepository;
import com.alura.literalura.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class LibroService {

    private final String URL_BASE = "https://gutendex.com/books/";

    @Autowired
    private ConsumoAPI consumoAPI;

    @Autowired
    private ConvierteDatos conversor;

    @Autowired
    private LibroRepository libroRepository;

    @Autowired
    private AutorRepository autorRepository;

    public DatosLibro buscarLibroPorTitulo(String titulo) {
        String url = URL_BASE + "?search=" + titulo.replace(" ", "%20");
        String json = consumoAPI.obtenerDatos(url);

        if (json != null) {
            DatosRespuesta respuesta = conversor.obtenerDatos(json, DatosRespuesta.class);

            if (respuesta != null && !respuesta.resultados().isEmpty()) {
                return respuesta.resultados().get(0); // Retorna el primer resultado
            }
        }

        return null;
    }

    public Libro guardarLibro(DatosLibro datosLibro) {
        // Verificar si el libro ya existe
        Optional<Libro> libroExistente = libroRepository.findByTituloContainsIgnoreCase(datosLibro.titulo());

        if (libroExistente.isPresent()) {
            System.out.println("El libro ya está registrado en la base de datos.");
            return libroExistente.get();
        }

        // Crear nuevo libro
        Libro libro = new Libro(datosLibro);

        // Procesar autor
        if (datosLibro.autores() != null && !datosLibro.autores().isEmpty()) {
            DatosAutor datosAutor = datosLibro.autores().get(0); // Tomamos el primer autor
            Autor autor = obtenerOCrearAutor(datosAutor);
            libro.setAutor(autor);
        }

        return libroRepository.save(libro);
    }

    private Autor obtenerOCrearAutor(DatosAutor datosAutor) {
        Optional<Autor> autorExistente = autorRepository.findByNombreIgnoreCase(datosAutor.nombre());

        if (autorExistente.isPresent()) {
            return autorExistente.get();
        }

        // Crear nuevo autor
        Autor nuevoAutor = new Autor(datosAutor.nombre(), datosAutor.fechaDeNacimiento(), datosAutor.fechaDeFallecimiento());
        return autorRepository.save(nuevoAutor);
    }

    public List<Libro> listarTodosLosLibros() {
        return libroRepository.findAll();
    }

    public List<Autor> listarTodosLosAutores() {
        return autorRepository.findAll();
    }

    public List<Autor> listarAutoresVivosEnAno(Integer ano) {
        return autorRepository.buscarAutoresVivosEnAno(ano);
    }

    public List<Libro> listarLibrosPorIdioma(String idioma) {
        return libroRepository.buscarPorIdioma(idioma);
    }
}
