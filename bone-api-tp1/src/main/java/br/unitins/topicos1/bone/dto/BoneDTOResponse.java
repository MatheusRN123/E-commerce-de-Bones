package br.unitins.topicos1.bone.dto;

import br.unitins.topicos1.bone.model.Bone;
import br.unitins.topicos1.bone.model.Bordado;

public record BoneDTOResponse(
    Long id,
    String nome,
    String cor,
    String nomeMaterial,
    String categoriaAba,
    Float tamanhoAba,
    Float profundidade,
    String circunferência,
    Bordado bordado
) {
    public static BoneDTOResponse valueOf(Bone bone){
        return new BoneDTOResponse(
            bone.getId(),
            bone.getNome(),
            bone.getCor(),
            bone.getMaterial().getNome(),
            bone.getCategoriaAba(),
            bone.getTamanhoAba(),
            bone.getProfundidade(),
            bone.getCircunferência(),
            bone.getBordado());
    }
}
