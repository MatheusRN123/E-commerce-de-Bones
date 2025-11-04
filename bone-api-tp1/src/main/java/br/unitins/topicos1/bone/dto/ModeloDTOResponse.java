package br.unitins.topicos1.bone.dto;

import br.unitins.topicos1.bone.model.Modelo;

public record ModeloDTOResponse(
    Long id,
    String nome,
    String categoria,
    String estilo
) {
    
    public static ModeloDTOResponse valueOf(Modelo modelo){
        return new ModeloDTOResponse(
            modelo.getId(),
            modelo.getNome(),
            modelo.getCategoria(),
            modelo.getEstilo()
            );
    }
}
