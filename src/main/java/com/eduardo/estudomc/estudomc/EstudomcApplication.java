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

		Categoria cat1 = new Categoria(null,"Informática");
		Categoria cat2 = new Categoria(null,"Escritorio");

		Produto p1 = new Produto(null,"Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);

		System.out.println("Esse é P1" + p1.getNome());

		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));

		p1.getCategorias().add(cat1);
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().add(cat1);

		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));

		Estado estado1 = new Estado(null, "Minas Gerais");
		Estado estado2 = new Estado(null,"São Paulo");

		Cidade cidade1 = new Cidade(null,"Uberlândia", estado1);
		Cidade cidade2 = new Cidade(null, "São Paulo", estado2);
		Cidade cidade3 = new Cidade(null, "Campinas", estado2);

		estado1.getCidades().addAll(Arrays.asList(cidade1));
		estado2.getCidades().addAll(Arrays.asList(cidade2, cidade3));

		estadoRepository.saveAll(Arrays.asList(estado1, estado2));
		cidadeRepository.saveAll(Arrays.asList(cidade1, cidade2, cidade3));

		Cliente cliente1 = new Cliente(null, "Marria Silva", "maria@gmail.com", "32341566", TipoCliente.PESSOAFISICA);
		cliente1.getTelefones().addAll(Arrays.asList("32452233", "322618654"));

		Endereco endereco1 = new Endereco(null, "Rua Flores", "300", "Apto 303", "Jardim", "5523123331", cliente1, cidade1);
		Endereco endereco2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "3877012", cliente1, cidade2);

		cliente1.getEnderecos().addAll(Arrays.asList(endereco1, endereco2));

		clienteRepository.save(cliente1);
		enderecoRepository.saveAll(Arrays.asList(endereco1, endereco2));
	}
}

