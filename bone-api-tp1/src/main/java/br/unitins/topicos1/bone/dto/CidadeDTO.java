package br.unitins.topicos1.bone.dto;

import java.util.List;

public record CidadeDTO(
    String nome,
    Long idEstado,
    List<Long> idEnderecos
) {}
