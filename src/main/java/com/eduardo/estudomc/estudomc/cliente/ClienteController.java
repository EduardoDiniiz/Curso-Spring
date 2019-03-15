package com.eduardo.estudomc.estudomc.cliente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insert(@Valid @RequestBody ClienteNewDTO clienteNewDTO){
        Cliente cliente = clienteService.fromDTO(clienteNewDTO);
        cliente = clienteService.insert(cliente);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(cliente.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Cliente> find(@PathVariable Integer id){
        Cliente clientReturn = clienteService.findById(id);
        return ResponseEntity.ok().body(clientReturn);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@Valid @RequestBody ClienteDTO ClienteDTO, @PathVariable Integer id){
        ClienteDTO.setId(id);
        Cliente Cliente = clienteService.fromDTO(ClienteDTO);
        Cliente = clienteService.update(Cliente);
        return ResponseEntity.noContent().build();
    }


    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        clienteService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<ClienteDTO>> findAll(){
        List<Cliente> Clientes = clienteService.findAll();
        List<ClienteDTO> listDTO = Clientes.stream().map(obj -> new ClienteDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }

    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public ResponseEntity<Page<ClienteDTO>> findPage(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
            @RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction){
        Page<Cliente> ClientesPage = clienteService.findPage(page, linesPerPage, orderBy, direction);
        Page<ClienteDTO> pageDTO = ClientesPage.map(obj -> new ClienteDTO(obj));
        return ResponseEntity.ok().body(pageDTO);
    }
}
