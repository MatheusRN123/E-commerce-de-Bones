package br.unitins.topicos1.bone.dto;

import br.unitins.topicos1.bone.model.Endereco;

public record EnderecoDTOResponse(
    Long id,
    String cep,
    String logradouro,
    String numero,
    String nomeCidade
) {
    public static EnderecoDTOResponse valueOf(Endereco endereco){

        return new EnderecoDTOResponse(
            endereco.getId(),
            endereco.getCep(),
            endereco.getLogradouro(),
            endereco.getNumero(),
            endereco.getCidade().getNome()
        );
    }
}