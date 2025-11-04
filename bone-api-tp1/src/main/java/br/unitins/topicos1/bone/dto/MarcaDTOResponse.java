package br.unitins.topicos1.bone.dto;

import br.unitins.topicos1.bone.model.Marca;

public record MarcaDTOResponse(
    Long id,
    String nome
) {
    
    public static MarcaDTOResponse valueOf(Marca marca){
        
        return new MarcaDTOResponse(
            marca.getId(),
            marca.getNome()
        );

    }

}
