package br.unitins.topicos1.bone.dto;

import br.unitins.topicos1.bone.model.Usuario;

public record AuthDTOResponse(
    Long id,
    String login,
    String senha
) {
    
    public static AuthDTOResponse valueOf(Usuario usuario) {
        return new AuthDTOResponse(
            usuario.getId(),
            usuario.getLogin(),
            usuario.getSenha()
        );
    }
}
