package com.eduardo.estudomc.estudomc.produto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutoController {

    @Autowired
    ProdutoService produtoService;

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public ResponseEntity<Produto> find(@PathVariable Integer id){
        Produto produto = produtoService.find(id);
        return ResponseEntity.ok().body(produto);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Page<ProdutoDTO>> findPage(
            @RequestParam(value = "nome", defaultValue = "") String nome,
            @RequestParam(value = "categorias", defaultValue = "") String categorias,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
            @RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction){
        Page<ProdutoDTO> pageDTO = produtoService.search(nome, categorias, page, linesPerPage, orderBy, direction);
        return ResponseEntity.ok().body(pageDTO);
    }

}
