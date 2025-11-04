package br.unitins.topicos1.bone.dto;

import br.unitins.topicos1.bone.model.Estampa;

public record EstampaDTOResponse(
    Long id,
    String nome,
    String tipo,
    String posicao,
    String descricao
) {
    public static EstampaDTOResponse valueOf(Estampa estampa){
        return new EstampaDTOResponse(
            estampa.getId(),
            estampa.getNome(),
            estampa.getTipo(),
            estampa.getPosicao(),
            estampa.getDescricao()
            );
    }
}
