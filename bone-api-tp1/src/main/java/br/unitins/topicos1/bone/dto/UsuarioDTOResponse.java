package br.unitins.topicos1.bone.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.unitins.topicos1.bone.model.Usuario;

public record UsuarioDTOResponse(
    Long id,
    String nome,
    String login,
    List<PedidoDTOResponse> pedidos
) {
    public static UsuarioDTOResponse valueOf(Usuario usuario) {
        return new UsuarioDTOResponse(
            usuario.getId(),
            usuario.getNome(),
            usuario.getLogin(),
            usuario.getPedidos() != null
                ? usuario.getPedidos()
                    .stream()
                    .map(PedidoDTOResponse::valueOf)
                    .collect(Collectors.toList())
                : null
        );
    }
}
