package br.unitins.topicos1.bone.dto;

public record ItemPedidoDTO(
    Long idPedido,
    Long idBone,
    Integer quantidade,
    Double preco
) {}
