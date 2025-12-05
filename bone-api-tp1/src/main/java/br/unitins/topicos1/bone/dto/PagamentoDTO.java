package br.unitins.topicos1.bone.dto;

public record PagamentoDTO(
    String tipoPagamento,
    PixDTO pix,
    CartaoDTO cartao,
    BoletoDTO boleto
) {}
