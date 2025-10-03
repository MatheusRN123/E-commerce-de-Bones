package br.unitins.topicos1.bone.dto;

import java.time.LocalDate;

import br.unitins.topicos1.bone.model.Estoque;

public record EstoqueDTOResponse(
    Long id,
    Integer quantidade,
    LocalDate dataAtualizacao
) {

    public static EstoqueDTOResponse valueOf(Estoque estoque){

        if(estoque == null){
            return null;
        }
        
        return new EstoqueDTOResponse(
            estoque.getId(),
            estoque.getQuantidade(),
            estoque.getDataAtualizacao()
        );

    }

}
