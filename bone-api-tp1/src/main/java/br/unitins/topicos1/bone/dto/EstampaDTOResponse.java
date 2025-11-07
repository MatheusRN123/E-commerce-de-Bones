package br.unitins.topicos1.bone.dto;

import br.unitins.topicos1.bone.model.Estampa;

public record EstampaDTOResponse(
    Long id,
    String tipo,
    String nome,
    String posicao,
    String descricao
) {
    public static EstampaDTOResponse valueOf(Estampa e) {
        return new EstampaDTOResponse(
            e.getId(),
            e.getTipo(),
            e.getNome(),
            e.getPosicao(),
            e.getDescricao()
        );
    }
}
