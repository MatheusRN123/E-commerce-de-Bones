package br.unitins.topicos1.bone.dto;

import br.unitins.topicos1.bone.model.EstampaBordada;

public record EstampaBordadaDTOResponse(
    Long id,
    String nome,
    String tipo,
    String posicao,
    String descricao,
    String corLinha,
    Integer quantCores
) {

    public static EstampaBordadaDTOResponse valueOf(EstampaBordada estampa){
        return new EstampaBordadaDTOResponse(
            estampa.getId(),
            estampa.getNome(),
            estampa.getTipo(),
            estampa.getPosicao(),
            estampa.getDescricao(),
            estampa.getCorLinha(),
            estampa.getQuantCores()
        );
    }
}
