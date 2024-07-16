package com.example.Literallura.service;

import com.example.Literallura.entity.Autor;
import com.example.Literallura.entity.Libro;
import com.example.Literallura.repository.AutorRepository;
import com.example.Literallura.repository.LibroRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MenuService {

    private final GutendexService gutendexService;
    private final LibroRepository libroRepository;
    private final AutorRepository autorRepository;

    public MenuService(GutendexService gutendexService, LibroRepository libroRepository, AutorRepository autorRepository) {
        this.gutendexService = gutendexService;
        this.libroRepository = libroRepository;
        this.autorRepository = autorRepository;
    }

    public void buscarLibroPorTitulo(String titulo) {
        Optional<Libro> libroOpt = gutendexService.buscarLibroPorTitulo(titulo);
        if (libroOpt.isPresent()) {
            Libro libro = libroOpt.get();
            libroRepository.save(libro);
            System.out.println("Libro registrado exitosamente.");
        } else {
            System.out.println("Búsqueda no encontrada.");
        }
    }

    public void listarLibrosRegistrados() {
        List<Libro> libros = libroRepository.findAll();
        if (libros.isEmpty()) {
            System.out.println("No hay libros registrados.");
        } else {
            for (Libro libro : libros) {
                System.out.printf("Título: %s, Autor: %s, Idioma: %s, Número de Descargas: %d\n",
                        libro.getTitulo(), libro.getAutor().getNombre(), libro.getIdioma(), libro.getNumeroDescargas());
            }
        }
    }

    public void listarAutoresRegistrados() {
        List<Autor> autores = autorRepository.findAll();
        if (autores.isEmpty()) {
            System.out.println("No hay autores registrados.");
        } else {
            for (Autor autor : autores) {
                System.out.printf("Nombre: %s, Año de Nacimiento: %d, Año de Fallecimiento: %d\n",
                        autor.getNombre(), autor.getAnioNacimiento(), autor.getAnioFallecimiento());
            }
        }
    }

    public void listarAutoresVivosEnAnio(int anio) {
        List<Autor> autores = autorRepository.findAll(); // Necesitarás cambiar esto para obtener datos reales
        for (Autor autor : autores) {
            if (autor.getAnioNacimiento() <= anio && (autor.getAnioFallecimiento() == null || autor.getAnioFallecimiento() >= anio)) {
                System.out.printf("Nombre: %s, Año de Nacimiento: %d, Año de Fallecimiento: %d\n",
                        autor.getNombre(), autor.getAnioNacimiento(), autor.getAnioFallecimiento());
            }
        }
    }

    public void listarLibrosPorIdioma(String idioma) {
        List<Libro> libros = gutendexService.buscarLibrosPorIdioma(idioma);
        if (libros.isEmpty()) {
            System.out.println("No hay libros registrados en el idioma especificado.");
        } else {
            for (Libro libro : libros) {
                System.out.printf("Título: %s, Autor: %s, Idioma: %s, Número de Descargas: %d\n",
                        libro.getTitulo(), libro.getAutor().getNombre(), libro.getIdioma(), libro.getNumeroDescargas());
            }
        }
    }
}
