package br.unitins.topicos1.bone.dto;

import br.unitins.topicos1.bone.model.Material;

public record MaterialDTOResponse(
    Long id,
    String nome
) {
    public static MaterialDTOResponse valueOf(Material material){
        return new MaterialDTOResponse(
            material.getId(),
            material.getNome());
    }
}
