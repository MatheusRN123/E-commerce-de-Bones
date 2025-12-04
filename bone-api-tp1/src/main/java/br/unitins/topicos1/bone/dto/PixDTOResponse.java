package br.unitins.topicos1.bone.dto;

import br.unitins.topicos1.bone.model.Pix;

public record PixDTOResponse(
    Long id,
    Double valor,
    String status,
    String chave,
    String tipoChave
) {
    public static PixDTOResponse valueOf(Pix pix) {
        return new PixDTOResponse(
            pix.getId(),
            pix.getValor(),
            pix.getStatus(),
            pix.getChave(),
            pix.getTipoChave()
        );
    }
}
