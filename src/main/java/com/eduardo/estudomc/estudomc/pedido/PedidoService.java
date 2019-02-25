package com.eduardo.estudomc.estudomc.pedido;

import com.eduardo.estudomc.estudomc.ItemPedido.ItemPedido;
import com.eduardo.estudomc.estudomc.ItemPedido.ItemPedidoRepository;
import com.eduardo.estudomc.estudomc.exceptions.ObjectNotFoundException;
import com.eduardo.estudomc.estudomc.pagamento.PagamentoComBoleto;
import com.eduardo.estudomc.estudomc.pagamento.PagamentoRepository;
import com.eduardo.estudomc.estudomc.produto.Produto;
import com.eduardo.estudomc.estudomc.produto.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private PagamentoRepository pagamentoRepository;

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    public Pedido find(Integer id) {

        Optional<Pedido> obj = pedidoRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id
                + ", Tipo: " + Pedido.class.getName()));
    }

    public URI insert(Pedido pedido) {
        pedido.setId(null);
        pedido.setInstate(new Date());
        pedido.getPagamento().setEstadoPagamento(1);
        pedido.getPagamento().setPedido(pedido);
        if (pedido.getPagamento() instanceof PagamentoComBoleto) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(pedido.getInstate());
            calendar.add(Calendar.DAY_OF_MONTH, 7);
            ((PagamentoComBoleto) pedido.getPagamento()).setDataVencimento(calendar.getTime());
        }
        pedido = pedidoRepository.save(pedido);
        pagamentoRepository.save(pedido.getPagamento());

        for (ItemPedido itemPedido : pedido.getItens()) {
            itemPedido.setDesconto(0.0);
            Produto produto = produtoRepository.findById(itemPedido.getProduto().getId()).get();
            itemPedido.setPreco(produto.getPreco());
            itemPedido.setPedido(pedido);
        }

        itemPedidoRepository.saveAll(pedido.getItens());
        return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(pedido.getId()).toUri();
    }
}
