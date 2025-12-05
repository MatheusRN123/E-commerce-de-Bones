package br.unitins.topicos1.bone.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.unitins.topicos1.bone.model.Cidade;

public record CidadeDTOResponse(
    Long id,
    String nome,
    String siglaEstado,
<<<<<<< HEAD
    List<EnderecoDTOResponse> enderecos
=======
    List<EnderecoDTOResponse> cidades
>>>>>>> a47f27e82323fbf2d3a412623eb607e53f435613
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
