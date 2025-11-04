package br.unitins.topicos1.bone.dto;

import java.util.List;

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
    Integer quantidadeEstoque,
    String nomeModelo,
    List<String> nomesEstampas
) {
    
    public static BoneDTOResponse valueOf(Bone bone){
        Integer quantidadeEstoque = (bone.getEstoque() != null) ? bone.getEstoque().getQuantidade() : null;

        List<String> nomesEstampas = (bone.getEstampas() != null) ? bone.getEstampas().stream().map(e -> e.getNome()).toList() : null;

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
            quantidadeEstoque,
            bone.getModelo().getNome(),
            nomesEstampas
        );
    }
}
