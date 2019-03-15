package com.eduardo.estudomc.estudomc.pagamento;

import com.eduardo.estudomc.estudomc.pedido.Pedido;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import java.util.Date;

@Entity
@Getter
@Setter
@JsonTypeName("pagamentoComBoleto")
public class PagamentoComBoleto extends Pagamento {

    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date dataVencimento;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date dataPagamento;

    public PagamentoComBoleto(){

    }

    public PagamentoComBoleto(Date dataVencimento, Date dataPagamento) {
        this.dataVencimento = dataVencimento;
        this.dataPagamento = dataPagamento;
    }

    public PagamentoComBoleto(Integer id, EstadoPagamento estadoPagamento, Pedido pedido, Date dataVencimento, Date dataPagamento) {
        super(id, estadoPagamento, pedido);
        this.dataVencimento = dataVencimento;
        this.dataPagamento = dataPagamento;
    }
}
