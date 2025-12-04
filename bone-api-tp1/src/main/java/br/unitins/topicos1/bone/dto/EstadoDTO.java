package br.unitins.topicos1.bone.dto;

import java.util.List;

public record EstadoDTO(
    String nome,
    String sigla,
    List<Long> idsCidades
) {}
