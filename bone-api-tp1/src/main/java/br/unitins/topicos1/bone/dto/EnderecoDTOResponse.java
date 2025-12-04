package br.unitins.topicos1.bone.dto;

import br.unitins.topicos1.bone.model.Endereco;

public record EnderecoDTOResponse(
    Long id,
    String nome,
    String cep,
    String logradouro,
    String numero,
    String nomeCidade,
    Long idPedido
) {
    public static EnderecoDTOResponse valueOf(Endereco endereco){

        return new EnderecoDTOResponse(
            endereco.getId(),
            endereco.getCep(),
            endereco.getCep(),
            endereco.getLogradouro(),
            endereco.getNumero(),
            endereco.getCidade().getNome(),
            endereco.getPedido().getId()
        );
    }
}