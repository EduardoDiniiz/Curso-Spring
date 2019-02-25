package com.eduardo.estudomc.estudomc.pagamento;

import com.eduardo.estudomc.estudomc.pedido.Pedido;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
@JsonTypeName("pagamentoComCartao")
public class PagamentoComCartao extends Pagamento{

    private Integer numeroDeParcelas;

    public PagamentoComCartao(){

    }

    public PagamentoComCartao(Integer id, EstadoPagamento estadoPagamento, Pedido pedido, Integer numeroDeParcelas) {
        super(id, estadoPagamento, pedido);
        this.numeroDeParcelas = numeroDeParcelas;
    }
}
