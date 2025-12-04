package br.unitins.topicos1.bone.dto;

import br.unitins.topicos1.bone.model.ItemPedido;

public record ItemPedidoDTOResponse(
    Long id,
    Long idPedido,
    Long idBone,
    String nomeBone,
    Integer quantidade,
    Double preco,
    Double subtotal
) {
    public static ItemPedidoDTOResponse valueOf(ItemPedido itemPedido){
        return new ItemPedidoDTOResponse(
            itemPedido.getId(),
            itemPedido.getPedido().getId(),
            itemPedido.getBone().getId(),
            itemPedido.getBone().getNome(),
            itemPedido.getQuantidade(),
            itemPedido.getPreco(),
            itemPedido.getSubTotal()
        );
    }
}
