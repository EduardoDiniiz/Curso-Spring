package com.eduardo.estudomc.estudomc.cliente;

import com.eduardo.estudomc.estudomc.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    public Cliente findClient(Integer id){
        Optional<Cliente> cliente = repository.findById(id);
        return cliente.orElseThrow(()-> new ObjectNotFoundException("Objeto não encontrado! Id: " + id
                + ", Tipo: " + Cliente.class.getName()));
    }
}
