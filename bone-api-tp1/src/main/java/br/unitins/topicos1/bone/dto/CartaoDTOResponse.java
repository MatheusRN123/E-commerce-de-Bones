package br.unitins.topicos1.bone.dto;

import br.unitins.topicos1.bone.model.Cartao;

public record CartaoDTOResponse(
    Long id,
    Double valor,
    String status,
    String nomeTitular,
    String numero,
    String validade
) {
    public static CartaoDTOResponse valueOf(Cartao cartao) {
        return new CartaoDTOResponse(
            cartao.getId(),
            cartao.getValor(),
            cartao.getStatus(),
            cartao.getNomeTitular(),
            cartao.getNumero(),
            cartao.getValidade()
        );
    }
}
