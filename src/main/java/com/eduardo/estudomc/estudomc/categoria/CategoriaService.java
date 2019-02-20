package com.eduardo.estudomc.estudomc.categoria;

import com.eduardo.estudomc.estudomc.exceptions.DataIntegrityException;
import com.eduardo.estudomc.estudomc.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository repository;

    public Categoria find(Integer id){

        Optional<Categoria> obj = repository.findById(id);
        return obj.orElseThrow(()-> new ObjectNotFoundException("Objeto não encontrado! Id: " + id
                + ", Tipo: " +Categoria.class.getName()));
    }

    public Categoria insert(Categoria categoria){
        return repository.save(categoria);
    }

    public Categoria update(Categoria categoria) {
        Categoria newCategoria = this.find(categoria.getId());
        this.updateData(newCategoria, categoria);
        return repository.save(newCategoria);
    }

    public void delete(Integer id) {
        this.find(id);

        try {
            repository.deleteById(id);
        }catch (DataIntegrityViolationException e){
            throw new DataIntegrityException("Não é possível excluir uma categoria que possui produtos");
        }

    }

    public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return repository.findAll(pageRequest);
    }

    public Categoria fromDTO(CategoriaDTO categoriaDTO){
        return new Categoria(categoriaDTO.getId(), categoriaDTO.getNome());
    }

    public List<Categoria> findAll() {
        return repository.findAll();
    }

    private void updateData(Categoria newCategoria, Categoria obj){
        newCategoria.setNome(obj.getNome());
    }
}
