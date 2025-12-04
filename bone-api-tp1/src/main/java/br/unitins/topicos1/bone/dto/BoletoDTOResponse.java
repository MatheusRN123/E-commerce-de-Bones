package br.unitins.topicos1.bone.dto;

import java.time.LocalDate;
import br.unitins.topicos1.bone.model.Boleto;

public record BoletoDTOResponse(
    Long id,
    Double valor,
    String status,
    String codigoBarras,
    LocalDate dataVencimento
) {
    public static BoletoDTOResponse valueOf(Boleto boleto) {
        return new BoletoDTOResponse(
            boleto.getId(),
            boleto.getValor(),
            boleto.getStatus(),
            boleto.getCodigoBarras(),
            boleto.getDataVencimento()
        );
    }
}
