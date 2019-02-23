package com.eduardo.estudomc.estudomc.cliente;

import com.eduardo.estudomc.estudomc.cidade.Cidade;
import com.eduardo.estudomc.estudomc.endereco.Endereco;
import com.eduardo.estudomc.estudomc.endereco.EnderecoRepository;
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
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    public Cliente findById(Integer id){
        Optional<Cliente> cliente = repository.findById(id);
        return cliente.orElseThrow(()-> new ObjectNotFoundException("Objeto não encontrado! Id: " + id
                + ", Tipo: " + Cliente.class.getName()));
    }

    public Cliente update(Cliente cliente) {
        Cliente newCliente = this.findById(cliente.getId());
        this.updateData(newCliente, cliente);
        return repository.save(newCliente);
    }

    public void delete(Integer id) {
        this.findById(id);

        try {
            repository.deleteById(id);
        }catch (DataIntegrityViolationException e){
            throw new DataIntegrityException("Não é possível excluir porque há pedidos relacionadas");
        }

    }

    public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return repository.findAll(pageRequest);
    }

    public Cliente fromDTO(ClienteDTO clienteDTO){
        return new Cliente(clienteDTO.getId(), clienteDTO.getNome(), clienteDTO.getEmail());
    }

    public Cliente fromDTO(ClienteNewDTO clienteNewDTO){
        Cliente newCliente = new Cliente(null, clienteNewDTO.getNome(), clienteNewDTO.getEmail(), clienteNewDTO.getCpfOuCnpj(), TipoCliente.toEnum(clienteNewDTO.getTipoCliente()));
        Cidade cidade = new Cidade(clienteNewDTO.getCidadeId(), null, null);
        Endereco endereco = new Endereco(null, clienteNewDTO.getLogradouro(), clienteNewDTO.getNumero(), clienteNewDTO.getComplemento(), clienteNewDTO.getBairro(), clienteNewDTO.getCep(), newCliente, cidade);
        newCliente.getEnderecos().add(endereco);
        newCliente.getTelefones().add(clienteNewDTO.getTelefone01());
        if (clienteNewDTO.getTelefone02() != null){
            newCliente.getTelefones().add(clienteNewDTO.getTelefone02());
        }
        if (clienteNewDTO.getTelefone03() != null){
            newCliente.getTelefones().add(clienteNewDTO.getTelefone03());
        }

        return newCliente;
    }

    public List<Cliente> findAll() {
        return repository.findAll();
    }

    private void updateData(Cliente newCliente, Cliente obj){
        newCliente.setNome(obj.getNome());
        newCliente.setEmail(obj.getEmail());
    }

    public Cliente insert(Cliente cliente){
        cliente = repository.save(cliente);
        enderecoRepository.saveAll(cliente.getEnderecos());
        return cliente;
    }
}
