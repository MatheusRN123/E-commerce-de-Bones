package br.unitins.topicos1.bone.dto;

import br.unitins.topicos1.bone.model.ItemPedido;

public record ItemPedidoDTOResponse(
    Long id,
    Long idPedido,
<<<<<<< HEAD
    BoneDTOResponse bone,
=======
    Long idBone,
    String nomeBone,
>>>>>>> a47f27e82323fbf2d3a412623eb607e53f435613
    Integer quantidade,
    Double preco,
    Double subtotal
) {
    public static ItemPedidoDTOResponse valueOf(ItemPedido itemPedido){
        return new ItemPedidoDTOResponse(
            itemPedido.getId(),
            itemPedido.getPedido().getId(),
<<<<<<< HEAD
            BoneDTOResponse.valueOf(itemPedido.getBone()),
=======
            itemPedido.getBone().getId(),
            itemPedido.getBone().getNome(),
>>>>>>> a47f27e82323fbf2d3a412623eb607e53f435613
            itemPedido.getQuantidade(),
            itemPedido.getPreco(),
            itemPedido.getSubTotal()
        );
    }
}
