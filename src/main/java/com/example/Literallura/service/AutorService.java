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
            return autorExistente.get(); 
        } else {
            Autor nuevoAutor = new Autor(nombre);
            
            return autorRepository.save(nuevoAutor);
        }
    }

    
    public Optional<Autor> buscarAutorPorNombre(String nombre) {
        return autorRepository.findByNombre(nombre);
    }
}
