package br.unitins.topicos1.bone.dto;

import java.util.List;

import br.unitins.topicos1.bone.model.Endereco;
import br.unitins.topicos1.bone.model.Pedido;

public record EnderecoDTOResponse(
    Long id,
    String cep,
    String logradouro,
    String numero,
    String nomeCidade,
    List<Long> idPedidos
) {
    public static EnderecoDTOResponse valueOf(Endereco endereco){

        List<Long> pedidosIds = endereco.getPedidos() != null
        ? endereco.getPedidos().stream()
            .map(Pedido::getId)
            .toList()
        : List.of();

        return new EnderecoDTOResponse(
            endereco.getId(),
            endereco.getCep(),
            endereco.getLogradouro(),
            endereco.getNumero(),
            endereco.getCidade().getNome(),
            pedidosIds
        );
    }
}