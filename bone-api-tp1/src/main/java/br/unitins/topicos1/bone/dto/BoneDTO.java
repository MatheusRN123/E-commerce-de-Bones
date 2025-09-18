package br.unitins.topicos1.bone.dto;

import br.unitins.topicos1.bone.model.Bordado;

public record BoneDTO(
    String nome,
    String cor,
    Long idMaterial,
    String categoriaAba,
    Float tamanhoAba,
    Float profundidade,
    String circunferência,
    Bordado bordado
) {}