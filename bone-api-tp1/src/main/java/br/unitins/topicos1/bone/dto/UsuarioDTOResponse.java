package br.unitins.topicos1.bone.dto;

import br.unitins.topicos1.bone.model.Perfil;
import br.unitins.topicos1.bone.model.Usuario;

public record UsuarioDTOResponse(
    Long id,
    String nome,
    String login,
    Perfil perfil
) {
    public static UsuarioDTOResponse valueOf(Usuario usuario) {
        return new UsuarioDTOResponse(
            usuario.getId(),
            usuario.getNome(),
            usuario.getLogin(),
            usuario.getPerfil()
        );
    }
}
