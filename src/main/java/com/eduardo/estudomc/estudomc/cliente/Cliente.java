package com.eduardo.estudomc.estudomc.cliente;

import com.eduardo.estudomc.estudomc.Endereco.Endereco;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@EqualsAndHashCode
public class Cliente implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private String email;
    private String CpfOuCnpj;
    private Integer tipoCliente;

    @JsonManagedReference // Serve para serializar os endereços de cliente
    @OneToMany(mappedBy = "cliente")
    private List<Endereco> enderecos = new ArrayList<Endereco>();

    @ElementCollection
    @CollectionTable(name="TELEFONE")
    private Set<String> telefones = new HashSet<>();

    public Cliente(){

    }

    public Cliente(Integer id, String nome, String email, String cpfOuCnpj, TipoCliente tipoCliente) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        CpfOuCnpj = cpfOuCnpj;
        this.tipoCliente = tipoCliente.getCod();
    }

    public void setTipo(TipoCliente tipo){
        this.tipoCliente = tipo.getCod();
    }

    public TipoCliente getTipo(){
        return TipoCliente.toEnum(this.tipoCliente);
    }
}
