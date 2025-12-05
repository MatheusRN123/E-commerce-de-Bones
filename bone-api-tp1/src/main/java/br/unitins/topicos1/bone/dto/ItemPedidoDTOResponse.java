package br.unitins.topicos1.bone.dto;

import br.unitins.topicos1.bone.model.ItemPedido;

public record ItemPedidoDTOResponse(
    Long id,
    Long idPedido,
    BoneDTOResponse bone,
    Integer quantidade,
    Double preco,
    Double subtotal
) {
    public static ItemPedidoDTOResponse valueOf(ItemPedido itemPedido){
        return new ItemPedidoDTOResponse(
            itemPedido.getId(),
            itemPedido.getPedido().getId(),
            BoneDTOResponse.valueOf(itemPedido.getBone()),
            itemPedido.getQuantidade(),
            itemPedido.getPreco(),
            itemPedido.getSubTotal()
        );
    }
}
