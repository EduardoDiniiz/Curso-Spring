package com.eduardo.estudomc.estudomc;

import com.eduardo.estudomc.estudomc.categoria.Categoria;
import com.eduardo.estudomc.estudomc.categoria.CategoriaRepository;
import com.eduardo.estudomc.estudomc.produto.Produto;
import com.eduardo.estudomc.estudomc.produto.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Arrays;

@SpringBootApplication
public class EstudomcApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private ProdutoRepository produtoRepository;

	public static void main(String[] args) {
		SpringApplication.run(EstudomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Categoria cat1 = Categoria.builder().id(null).nome("Informática").produtos(new ArrayList<>()).build();
		Categoria cat2 = Categoria.builder().id(null).nome("Escritorio").produtos(new ArrayList<>()).build();

		Produto p1 = Produto.builder().id(null).nome("Computador").preco(2000.00).categorias(new ArrayList<>()).build();
		Produto p2 = Produto.builder().id(null).nome("Impressora").preco(800.00).categorias(new ArrayList<>()).build();
		Produto p3 = Produto.builder().id(null).nome("Mouse").preco(80.00).categorias(new ArrayList<>()).build();

		System.out.println("Esse é P1" + p1.getNome());

		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));

		p1.getCategorias().add(cat1);
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().add(cat1);

		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));

	}
}

