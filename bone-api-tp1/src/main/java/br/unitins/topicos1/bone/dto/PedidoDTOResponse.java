package br.unitins.topicos1.bone.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import br.unitins.topicos1.bone.model.Pedido;

public record PedidoDTOResponse(
    Long id,
    LocalDateTime data,
    UsuarioDTOResponse idUsuario,
    EnderecoDTOResponse endereco,
    PagamentoDTOResponse pagamento,
    List<ItemPedidoDTOResponse> itens,
    Double valorTotal
) {
    public static PedidoDTOResponse valueOf(Pedido pedido) {
        return new PedidoDTOResponse(
            pedido.getId(),
            pedido.getData(),
            UsuarioDTOResponse.valueOf(pedido.getUsuario()),
            EnderecoDTOResponse.valueOf(pedido.getEndereco()),
            pedido.getPagamento() != null ? PagamentoDTOResponse.valueOf(pedido.getPagamento()) : null,
            pedido.getItens() != null
                ? pedido.getItens().stream()
                    .map(ItemPedidoDTOResponse::valueOf)
                    .collect(Collectors.toList())
                : null,
            pedido.getValorTotal()
        );
    }
}
