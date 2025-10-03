package br.unitins.topicos1.bone.dto;

import java.time.LocalDate;

public record EstoqueDTO(
    Integer quantidade,
    LocalDate dataAtualizacao
) {}
