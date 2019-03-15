package com.eduardo.estudomc.estudomc.pedido;

import com.eduardo.estudomc.estudomc.ItemPedido.ItemPedido;
import com.eduardo.estudomc.estudomc.cliente.Cliente;
import com.eduardo.estudomc.estudomc.endereco.Endereco;
import com.eduardo.estudomc.estudomc.pagamento.Pagamento;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

@Entity
@Getter
@Setter
public class Pedido implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date instate;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "pedido")
    private Pagamento pagamento;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "endereco_entrega")
    private Endereco enderecoEntrega;

    @OneToMany(mappedBy = "id.pedido")
    private Set<ItemPedido> itens = new HashSet<>();

    public Double getValorTotal(){
        Double soma = 0.0;
        for (ItemPedido itempPedido : itens ) {
            soma += itempPedido.getSubTotal();
        }
        return soma;
    }
    public Pedido(){

    }

    public Pedido(Integer id, Date instate, Cliente cliente, Endereco enderecoEntrega) {
        this.id = id;
        this.instate = instate;
        this.cliente = cliente;
        this.enderecoEntrega = enderecoEntrega;
    }

    @Override
    public String toString() {
        NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        StringBuilder builder = new StringBuilder();
        builder.append("Pedido número: ");
        builder.append(getId());
        builder.append(", Instante: ");
        builder.append(sdf.format(getInstate()));
        builder.append(", Cliente: ");
        builder.append(getCliente().getNome());
        builder.append(", Situação do pagamento: ");
        builder.append(getPagamento().getEstadoPagamento().getDescricao());
        builder.append("\nDetalhes\n");
        for (ItemPedido itemPedido:getItens()) {
            builder.append(itemPedido.toString());
        }
        builder.append("Valor total: ");
        builder.append(nf.format(getValorTotal()));
        return builder.toString();
    }
}
