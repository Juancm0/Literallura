package com.example.Literallura.service;

import com.example.Literallura.entity.Autor;
import com.example.Literallura.entity.Libro;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GutendexService {

    private final RestTemplate restTemplate;

    public GutendexService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Optional<Libro> buscarLibroPorTitulo(String titulo) {
        String url = String.format("https://gutendex.com/books?search=%s", titulo);
        GutendexResponse response = restTemplate.getForObject(url, GutendexResponse.class);

        if (response != null && response.getResults().length > 0) {
            GutendexBook book = response.getResults()[0];
            Autor autor = new Autor();
            autor.setNombre(formatNombreAutor(book.getAuthors()[0].getName())); // Formatear nombre

            Libro libro = new Libro();
            libro.setTitulo(book.getTitle());
            libro.setAutor(autor);
            libro.setIdioma(book.getLanguages()[0]);
            libro.setNumeroDescargas(book.getDownload_count());

            return Optional.of(libro);
        } else {
            return Optional.empty();
        }
    }

    public List<Libro> buscarLibrosPorIdioma(String idioma) {
        String url = String.format("https://gutendex.com/books?languages=%s", idioma);
        GutendexResponse response = restTemplate.getForObject(url, GutendexResponse.class);

        List<Libro> libros = new ArrayList<>();
        if (response != null && response.getResults().length > 0) {
            for (GutendexBook book : response.getResults()) {
                Autor autor = new Autor();
                autor.setNombre(formatNombreAutor(book.getAuthors()[0].getName())); // Formatear nombre

                Libro libro = new Libro();
                libro.setTitulo(book.getTitle());
                libro.setAutor(autor);
                libro.setIdioma(book.getLanguages()[0]);
                libro.setNumeroDescargas(book.getDownload_count());

                libros.add(libro);
            }
        }
        return libros;
    }

    private String formatNombreAutor(String nombreCompleto) {
        // Aquí implementa la lógica para formatear el nombre del autor según tus requisitos
        // Por ejemplo, dividir el nombre completo en nombre y apellidos y devolverlo en el formato deseado
        return nombreCompleto; // Implementar lógica adecuada
    }
}

class GutendexResponse {
    private GutendexBook[] results;

    public GutendexBook[] getResults() {
        return results;
    }

    public void setResults(GutendexBook[] results) {
        this.results = results;
    }
}

class GutendexBook {
    private String title;
    private GutendexAuthor[] authors;
    private String[] languages;
    private int download_count;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public GutendexAuthor[] getAuthors() {
        return authors;
    }

    public void setAuthors(GutendexAuthor[] authors) {
        this.authors = authors;
    }

    public String[] getLanguages() {
        return languages;
    }

    public void setLanguages(String[] languages) {
        this.languages = languages;
    }

    public int getDownload_count() {
        return download_count;
    }

    public void setDownload_count(int download_count) {
        this.download_count = download_count;
    }
}

class GutendexAuthor {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
