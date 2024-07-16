package com.example.Literallura.service;

import com.example.Literallura.entity.Autor;
import com.example.Literallura.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AutorService {

    private final AutorRepository autorRepository;

    @Autowired
    public AutorService(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }

    public Autor guardarAutor(String nombre) {
        Optional<Autor> autorExistente = autorRepository.findByNombre(nombre);

        if (autorExistente.isPresent()) {
            return autorExistente.get(); // Devuelve el autor existente si se encuentra
        } else {
            Autor nuevoAutor = new Autor(nombre);
            // Aquí podrías realizar la lógica para obtener y guardar las fechas del autor desde la API
            return autorRepository.save(nuevoAutor); // Guarda y devuelve el nuevo autor creado
        }
    }

    // Método para buscar autor por nombre
    public Optional<Autor> buscarAutorPorNombre(String nombre) {
        return autorRepository.findByNombre(nombre);
    }
}
