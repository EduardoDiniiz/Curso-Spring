package com.eduardo.estudomc.estudomc.produto;

import com.eduardo.estudomc.estudomc.categoria.Categoria;
import com.eduardo.estudomc.estudomc.categoria.CategoriaRepository;
import com.eduardo.estudomc.estudomc.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    public Produto find(Integer id){

        Optional<Produto> obj = produtoRepository.findById(id);
        return obj.orElseThrow(()-> new ObjectNotFoundException("Objeto não encontrado! Id: " + id
                + ", Tipo: " +Produto.class.getName()));
    }

    public Page<ProdutoDTO> search(String nome, String categoriasIds, Integer page, Integer linesPerPage, String orderBy, String direction){
        try {
            nome = URLDecoder.decode(nome, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            nome = "";
        }
        List<Integer> ids = Arrays.asList(categoriasIds.split(",")).stream().map(x -> Integer.parseInt(x)).collect(Collectors.toList());
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        List<Categoria> categorias = categoriaRepository.findAllById(ids);
        Page<Produto> produtosPage = this.produtoRepository.findDistinctByNomeContainingAndCategoriasIn(nome, categorias, pageRequest);
        return produtosPage.map(obj -> new ProdutoDTO(obj));
    }
}
