package com.eduardo.estudomc.estudomc.pedido;

import com.eduardo.estudomc.estudomc.cliente.Cliente;
import com.eduardo.estudomc.estudomc.endereco.Endereco;
import com.eduardo.estudomc.estudomc.pagamento.Pagamento;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
public class Pedido implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Date instate;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "pedido")
    private Pagamento pagamento;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "endereco_entrega")
    private Endereco enderecoEntrega;

    public Pedido(){

    }

    public Pedido(Integer id, Date instate, Cliente cliente, Endereco enderecoEntrega) {
        this.id = id;
        this.instate = instate;
        this.cliente = cliente;
        this.enderecoEntrega = enderecoEntrega;
    }
}
