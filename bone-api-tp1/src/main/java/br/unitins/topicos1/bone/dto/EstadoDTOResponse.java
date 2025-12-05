package br.unitins.topicos1.bone.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.unitins.topicos1.bone.model.Estado;

public record EstadoDTOResponse(
    Long id,
    String nome,
    String sigla,
    List<CidadeDTOResponse> cidades
) {
    public static EstadoDTOResponse valueOf(Estado estado){
        return new EstadoDTOResponse(
            estado.getId(),
            estado.getNome(),
            estado.getSigla(),
            estado.getCidades() != null
                ? estado.getCidades()
                    .stream()
                    .map(CidadeDTOResponse::valueOf)
                    .collect(Collectors.toList())
                : null
        );
    }
}
