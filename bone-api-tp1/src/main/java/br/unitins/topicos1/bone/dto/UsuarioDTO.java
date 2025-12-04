package br.unitins.topicos1.bone.dto;

import java.util.List;

public record UsuarioDTO(
    String nome,
    String login,
    String senha,
    List<Long> idsPedidos
) {}