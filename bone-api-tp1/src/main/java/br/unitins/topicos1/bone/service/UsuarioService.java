package br.unitins.topicos1.bone.service;

import java.util.List;

import br.unitins.topicos1.bone.dto.UsuarioDTOResponse;
import br.unitins.topicos1.bone.model.Usuario;

public interface UsuarioService {
    List<Usuario> findAll();
    UsuarioDTOResponse findByLogin(String login);
    UsuarioDTOResponse findByLoginAndSenha(String login, String senha);
    UsuarioDTOResponse findById(Long id);
    UsuarioDTOResponse create(Usuario usuario);
    UsuarioDTOResponse update(Long id, Usuario usuarioAtualizado);
    void delete(Long id);
}
