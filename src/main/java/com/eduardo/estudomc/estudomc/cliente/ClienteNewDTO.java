package com.eduardo.estudomc.estudomc.cliente;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ClienteNewDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String nome;
    private String email;
    private String CpfOuCnpj;
    private Integer tipoCliente;

    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String cep;

    private String telefone01;
    private String telefone02;
    private String telefone03;

    private Integer cidadeId;

    public ClienteNewDTO(){

    }
}
