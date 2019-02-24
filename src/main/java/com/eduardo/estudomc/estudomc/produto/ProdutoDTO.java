package com.eduardo.estudomc.estudomc.produto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ProdutoDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String nome;
    private Double preco;

    public ProdutoDTO(){

    }

    public ProdutoDTO(Produto produto){
        this.id = produto.getId();
        this.nome = produto.getNome();
        this.preco = produto.getPreco();
    }

    public ProdutoDTO(Integer id, String nome, Double preco) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
    }
}
