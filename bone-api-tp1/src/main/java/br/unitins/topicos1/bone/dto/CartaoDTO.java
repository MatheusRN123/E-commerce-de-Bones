package br.unitins.topicos1.bone.dto;

public record CartaoDTO(
    String nomeTitular,
    String numero,
    String validade,
    String cvv
) {}