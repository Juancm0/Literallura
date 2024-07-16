package com.example.Literallura.service;

import com.example.Literallura.entity.Libro;
import com.example.Literallura.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibroService {

    private final LibroRepository libroRepository;

    @Autowired
    public LibroService(LibroRepository libroRepository) {
        this.libroRepository = libroRepository;
    }

    public List<Libro> listarLibros() {
        return libroRepository.findAll();
    }

    public List<Libro> listarLibrosPorIdioma(String idioma) {
        return libroRepository.findByIdioma(idioma);
    }

    public Libro guardarLibroConAutor(String tituloLibro, String nombreAutor) {
        // Lógica para guardar libro con autor, como ya lo habíamos configurado
        return null;
    }
}
