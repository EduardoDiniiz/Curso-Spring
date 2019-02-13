package com.eduardo.estudomc.estudomc;

import com.eduardo.estudomc.estudomc.Endereco.Endereco;
import com.eduardo.estudomc.estudomc.Endereco.EnderecoRepository;
import com.eduardo.estudomc.estudomc.categoria.Categoria;
import com.eduardo.estudomc.estudomc.categoria.CategoriaRepository;
import com.eduardo.estudomc.estudomc.cidade.Cidade;
import com.eduardo.estudomc.estudomc.cidade.CidadeRepository;
import com.eduardo.estudomc.estudomc.cliente.Cliente;
import com.eduardo.estudomc.estudomc.cliente.ClienteRepository;
import com.eduardo.estudomc.estudomc.cliente.TipoCliente;
import com.eduardo.estudomc.estudomc.estado.Estado;
import com.eduardo.estudomc.estudomc.estado.EstadoRepository;
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

	@Autowired
	private EstadoRepository estadoRepository;

	@Autowired
	private CidadeRepository cidadeRepository;

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private EnderecoRepository enderecoRepository;

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

		Estado estado1 = Estado.builder().id(null).nome("Minas Gerais").cidades(new ArrayList<Cidade>()).build();
		Estado estado2 = Estado.builder().id(null).nome("São Paulo").cidades(new ArrayList<Cidade>()).build();

		Cidade cidade1 = Cidade.builder().id(null).nome("Uberlândia").estado(estado1).build();
		Cidade cidade2 = Cidade.builder().id(null).nome("São Paulo").estado(estado2).build();
		Cidade cidade3 = Cidade.builder().id(null).nome("Campinas").estado(estado2).build();

		estado1.getCidades().addAll(Arrays.asList(cidade1));
		estado2.getCidades().addAll(Arrays.asList(cidade2, cidade3));

		estadoRepository.saveAll(Arrays.asList(estado1, estado2));
		cidadeRepository.saveAll(Arrays.asList(cidade1, cidade2, cidade3));

		Cliente cliente1 = new Cliente(null, "Marria Silva", "maria@gmail.com", "32341566", TipoCliente.PESSOAFISICA);
		cliente1.getTelefones().addAll(Arrays.asList("32452233", "322618654"));

		Endereco endereco1 = Endereco.builder().id(null).logradouro("Rua Flores").numero("300").complemento("Apto 303").bairro("Jardim").cep("5523123331").cidade(cidade1).cliente(cliente1).build();
		Endereco endereco2 = Endereco.builder().id(null).logradouro("Avenida Matos").numero("105").complemento("Sala 800").logradouro("Centro").cep("3877012").cliente(cliente1).cidade(cidade2).build();

		cliente1.getEnderecos().addAll(Arrays.asList(endereco1, endereco2));

		clienteRepository.save(cliente1);
		enderecoRepository.saveAll(Arrays.asList(endereco1, endereco2));
	}
}

