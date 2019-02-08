package com.eduardo.estudomc.estudomc.categoria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository repository;

    public Optional<Categoria> find(Integer id){

        Optional<Categoria> obj = repository.findById(id);
        return obj;
    }
}
