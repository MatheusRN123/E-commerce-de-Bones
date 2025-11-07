package br.unitins.topicos1.bone.dto;

public record EstampaBordadaDTO(
    String nome,
    String tipo,
    String posicao,
    String descricao,
    String corLinha,
    Integer quantCores
) {}
