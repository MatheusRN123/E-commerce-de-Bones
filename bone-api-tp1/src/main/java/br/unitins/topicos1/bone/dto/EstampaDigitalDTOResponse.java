package br.unitins.topicos1.bone.dto;

import br.unitins.topicos1.bone.model.EstampaDigital;

public record EstampaDigitalDTOResponse(
    Long id,
    String nome,
    String tipo,
    String posicao,
    String descricao,
    String resolucao
) {

    public static EstampaDigitalDTOResponse valueOf(EstampaDigital estampa){
        return new EstampaDigitalDTOResponse(
            estampa.getId(),
            estampa.getNome(),
            estampa.getTipo(),
            estampa.getPosicao(),
            estampa.getDescricao(),
            estampa.getResolucao()
        );
    }
}
