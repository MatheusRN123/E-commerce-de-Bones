package br.unitins.topicos1.bone.dto;

import br.unitins.topicos1.bone.model.Perfil;

public record UsuarioDTO(
    String nome,
    String login,
    String senha,
    Perfil perfil
) {}