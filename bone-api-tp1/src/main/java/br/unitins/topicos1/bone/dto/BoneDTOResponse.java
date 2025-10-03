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
    String circunferencia,
    Bordado bordado,
    String nomeMarca,
    Integer quantidadeEstoque
) {
    public static BoneDTOResponse valueOf(Bone bone){
        Integer quantidadeEstoque = null;
        
        if(bone.getEstoque() != null){
            quantidadeEstoque = bone.getEstoque().getQuantidade();
        }
        
        return new BoneDTOResponse(
            bone.getId(),
            bone.getNome(),
            bone.getCor(),
            bone.getMaterial().getNome(),
            bone.getCategoriaAba(),
            bone.getTamanhoAba(),
            bone.getProfundidade(),
            bone.getCircunferencia(),
            bone.getBordado(),
            bone.getMarca().getNome(),
            quantidadeEstoque);
    }
}