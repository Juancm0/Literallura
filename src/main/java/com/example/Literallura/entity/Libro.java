package com.example.Literallura.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;


@Entity
@Table(name = "libros")
public class Libro {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "titulo")
    private String titulo;

    @Column(name = "numero_descargas")
    private Integer numeroDescargas;

    @Column(name = "idioma")
    private String idioma;

    @ManyToOne
    @JoinColumn(name = "autor_id")
    @Cascade(CascadeType.PERSIST) // Configuración de cascada para persistencia
    private Autor autor;

    // Constructor vacío
    public Libro() {
    }

    // Constructor con todos los atributos
    public Libro(String titulo, Integer numeroDescargas, String idioma, Autor autor) {
        this.titulo = titulo;
        this.numeroDescargas = numeroDescargas;
        this.idioma = idioma;
        this.autor = autor;
    }

    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getNumeroDescargas() {
        return numeroDescargas;
    }

    public void setNumeroDescargas(Integer numeroDescargas) {
        this.numeroDescargas = numeroDescargas;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }
}
