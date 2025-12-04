package br.unitins.topicos1.bone.dto;

public record EnderecoDTO(
    String nome,
    String cep,
    String logradouro,
    String numero,
    Long idCidade,
    Long idPedido
) {}
