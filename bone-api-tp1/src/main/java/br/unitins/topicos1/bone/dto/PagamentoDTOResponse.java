package br.unitins.topicos1.bone.dto;

import java.time.LocalDateTime;

import br.unitins.topicos1.bone.model.Pagamento;

public record PagamentoDTOResponse(
    Long id,
    Double valor,
    LocalDateTime data,
    String status,
    String tipo
) {

    public static PagamentoDTOResponse valueOf(Pagamento pagamento) {
        return new PagamentoDTOResponse(
            pagamento.getId(),
            pagamento.getValor(),
            pagamento.getData(),
            pagamento.getStatus(),
            pagamento.getClass().getSimpleName()
        );
    }
}