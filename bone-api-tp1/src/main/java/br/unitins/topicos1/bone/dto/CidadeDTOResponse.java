package br.unitins.topicos1.bone.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.unitins.topicos1.bone.model.Cidade;

public record CidadeDTOResponse(
    Long id,
    String nome,
    String siglaEstado,
    List<EnderecoDTOResponse> cidades
) {
    public static CidadeDTOResponse valueOf(Cidade cidade){

        return new CidadeDTOResponse(
            cidade.getId(),
            cidade.getNome(),
            cidade.getEstado().getSigla(),
            cidade.getEnderecos() != null
                ? cidade.getEnderecos()
                    .stream()
                    .map(EnderecoDTOResponse::valueOf)
                    .collect(Collectors.toList())
                : null
        );
    }
}
