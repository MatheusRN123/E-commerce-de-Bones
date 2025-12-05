package br.unitins.topicos1.bone.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import br.unitins.topicos1.bone.model.Pedido;

public record PedidoDTOResponse(
    Long id,
    LocalDateTime data,
<<<<<<< HEAD
    UsuarioDTOResponse idUsuario,
    EnderecoDTOResponse endereco,
    PagamentoDTOResponse pagamento,
    List<ItemPedidoDTOResponse> itens,
    Double valorTotal
=======
    Double total,
    Long idUsuario,
    EnderecoDTOResponse endereco,
    PagamentoDTOResponse pagamento,
    List<ItemPedidoDTOResponse> itens
>>>>>>> a47f27e82323fbf2d3a412623eb607e53f435613
) {
    public static PedidoDTOResponse valueOf(Pedido pedido) {
        return new PedidoDTOResponse(
            pedido.getId(),
            pedido.getData(),
<<<<<<< HEAD
            UsuarioDTOResponse.valueOf(pedido.getUsuario()),
            EnderecoDTOResponse.valueOf(pedido.getEndereco()),
            pedido.getPagamento() != null ? PagamentoDTOResponse.valueOf(pedido.getPagamento()) : null,
            pedido.getItens() != null
                ? pedido.getItens().stream()
                    .map(ItemPedidoDTOResponse::valueOf)
                    .collect(Collectors.toList())
                : null,
            pedido.getValorTotal()
=======
            pedido.getValorTotal(),
            pedido.getUsuario() != null ? pedido.getUsuario().getId() : null,
            pedido.getEndereco() != null ? EnderecoDTOResponse.valueOf(pedido.getEndereco()) : null,
            pedido.getPagamento() != null ? PagamentoDTOResponse.valueOf(pedido.getPagamento()) : null,
            pedido.getItens() != null
                ? pedido.getItens().stream()
                      .map(ItemPedidoDTOResponse::valueOf)
                      .collect(Collectors.toList())
                : null
>>>>>>> a47f27e82323fbf2d3a412623eb607e53f435613
        );
    }
}
