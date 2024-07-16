package com.example.Literallura.controller;

import com.example.Literallura.entity.Libro;
import com.example.Literallura.service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MenuController {

    private final LibroService libroService;

    @Autowired
    public MenuController(LibroService libroService) {
        this.libroService = libroService;
    }

    @GetMapping("/libros")
    public List<Libro> listarLibros() {
        return libroService.listarLibros();
    }

    @GetMapping("/libros/idioma")
    public List<Libro> listarLibrosPorIdioma(@RequestParam String idioma) {
        return libroService.listarLibrosPorIdioma(idioma);
    }

    @PostMapping("/libros/nuevo")
    public Libro guardarLibroConAutor(@RequestParam String tituloLibro, @RequestParam String nombreAutor) {
        return libroService.guardarLibroConAutor(tituloLibro, nombreAutor);
    }
}
