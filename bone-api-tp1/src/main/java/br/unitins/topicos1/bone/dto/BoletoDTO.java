package br.unitins.topicos1.bone.dto;

import java.time.LocalDate;

public record BoletoDTO(
    String codigoBarras,
    LocalDate dataVencimento
) {}

