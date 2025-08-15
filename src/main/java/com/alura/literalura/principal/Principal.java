package com.alura.literalura.principal;

import com.alura.literalura.dto.DatosLibro;
import com.alura.literalura.model.Autor;
import com.alura.literalura.model.Libro;
import com.alura.literalura.service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class Principal {

    private final Scanner teclado = new Scanner(System.in);

    @Autowired
    private LibroService libroService;

    public void muestraElMenu() {
        int opcion = -1;
        String menu = """
                
                ==================================================
                    BIENVENIDO AL CATÁLOGO DE LIBROS LITERALURA
                ==================================================
                
                Elija la opción a través de su número:
                1 - Buscar libro por título
                2 - Listar libros registrados
                3 - Listar autores registrados  
                4 - Listar autores vivos en un determinado año
                5 - Listar libros por idioma
                0 - Salir
                
                ===============================================
                """;

        while (opcion != 0) {
            System.out.println(menu);
            System.out.print("Ingrese su opción: ");

            try {
                opcion = Integer.parseInt(teclado.nextLine());

                switch (opcion) {
                    case 1:
                        buscarLibroPorTitulo();
                        break;
                    case 2:
                        listarLibrosRegistrados();
                        break;
                    case 3:
                        listarAutoresRegistrados();
                        break;
                    case 4:
                        listarAutoresVivosEnAno();
                        break;
                    case 5:
                        listarLibrosPorIdioma();
                        break;
                    case 0:
                        System.out.println("\n¡Gracias por usar LiterAlura!");
                        System.out.println("¡Hasta luego!");
                        break;
                    default:
                        System.out.println("\nOpción no válida. Por favor, seleccione una opción del 0 al 5.");
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println("\nError: Por favor ingrese solo números.");
            }
        }
    }

    private void buscarLibroPorTitulo() {
        System.out.println("\n========== BUSCAR LIBRO POR TÍTULO ==========");
        System.out.print("Ingrese el nombre del libro que desea buscar: ");
        String titulo = teclado.nextLine();

        if (titulo.trim().isEmpty()) {
            System.out.println("Error: El título no puede estar vacío.");
            return;
        }

        System.out.println("\nBuscando libro en la API...");

        try {
            DatosLibro datosLibro = libroService.buscarLibroPorTitulo(titulo);

            if (datosLibro != null) {
                System.out.println("\n¡Libro encontrado!");
                Libro libro = libroService.guardarLibro(datosLibro);
                System.out.println(libro);
            } else {
                System.out.println("\nLibro no encontrado.");
                System.out.println("Verifique el título e intente nuevamente.");
            }
        } catch (Exception e) {
            System.out.println("\nError al buscar el libro: " + e.getMessage());
        }

        presionarEnterParaContinuar();
    }

    private void listarLibrosRegistrados() {
        System.out.println("\n========== LIBROS REGISTRADOS ==========");

        List<Libro> libros = libroService.listarTodosLosLibros();

        if (libros.isEmpty()) {
            System.out.println("No hay libros registrados en la base de datos.");
            System.out.println("¡Busque algunos libros para verlos aquí!");
        } else {
            System.out.println("Total de libros registrados: " + libros.size());
            System.out.println();

            for (Libro libro : libros) {
                System.out.println(libro);
                System.out.println();
            }
        }

        presionarEnterParaContinuar();
    }

    private void listarAutoresRegistrados() {
        System.out.println("\n========== AUTORES REGISTRADOS ==========");

        List<Autor> autores = libroService.listarTodosLosAutores();

        if (autores.isEmpty()) {
            System.out.println("No hay autores registrados en la base de datos.");
            System.out.println("¡Busque algunos libros para ver sus autores aquí!");
        } else {
            System.out.println("Total de autores registrados: " + autores.size());
            System.out.println();

            for (Autor autor : autores) {
                System.out.println(autor);
                System.out.println();
            }
        }

        presionarEnterParaContinuar();
    }

    private void listarAutoresVivosEnAno() {
        System.out.println("\n========== AUTORES VIVOS EN UN AÑO ==========");
        System.out.print("Ingrese el año que desea consultar: ");

        try {
            Integer ano = Integer.parseInt(teclado.nextLine());

            List<Autor> autoresVivos = libroService.listarAutoresVivosEnAno(ano);

            if (autoresVivos.isEmpty()) {
                System.out.println("\nNo se encontraron autores vivos en el año " + ano + ".");
            } else {
                System.out.println("\nAutores vivos en el año " + ano + ":");
                System.out.println("Total encontrados: " + autoresVivos.size());
                System.out.println();

                for (Autor autor : autoresVivos) {
                    System.out.println(autor);
                    System.out.println();
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("\nError: Por favor ingrese un año válido (número).");
        } catch (Exception e) {
            System.out.println("\nError al buscar autores: " + e.getMessage());
        }

        presionarEnterParaContinuar();
    }

    private void listarLibrosPorIdioma() {
        System.out.println("\n========== LIBROS POR IDIOMA ==========");

        String menuIdiomas = """
                Seleccione el idioma:
                es - Español
                en - Inglés  
                fr - Francés
                pt - Portugués
                """;

        System.out.println(menuIdiomas);
        System.out.print("Ingrese las siglas del idioma: ");
        String idioma = teclado.nextLine().toLowerCase().trim();

        if (idioma.isEmpty()) {
            System.out.println("Error: Debe ingresar un idioma.");
            return;
        }

        // Validar idiomas aceptados
        if (!idioma.equals("es") && !idioma.equals("en") && !idioma.equals("fr") && !idioma.equals("pt")) {
            System.out.println("Error: Idioma no válido. Use: es, en, fr o pt");
            return;
        }

        try {
            List<Libro> librosPorIdioma = libroService.listarLibrosPorIdioma(idioma);

            String nombreIdioma = switch (idioma) {
                case "es" -> "Español";
                case "en" -> "Inglés";
                case "fr" -> "Francés";
                case "pt" -> "Portugués";
                default -> idioma.toUpperCase();
            };

            if (librosPorIdioma.isEmpty()) {
                System.out.println("\nNo se encontraron libros en " + nombreIdioma + ".");
            } else {
                System.out.println("\nLibros en " + nombreIdioma + ":");
                System.out.println("Total encontrados: " + librosPorIdioma.size());
                System.out.println();

                for (Libro libro : librosPorIdioma) {
                    System.out.println(libro);
                    System.out.println();
                }
            }
        } catch (Exception e) {
            System.out.println("\nError al buscar libros por idioma: " + e.getMessage());
        }

        presionarEnterParaContinuar();
    }

    private void presionarEnterParaContinuar() {
        System.out.println("\nPresione ENTER para continuar...");
        teclado.nextLine();
    }
}
