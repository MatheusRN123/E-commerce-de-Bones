package br.unitins.topicos1.bone.service;

import java.util.List;

import br.unitins.topicos1.bone.model.Usuario;

public interface UsuarioService {
    List<Usuario> findAll();
    Usuario findByLogin(String login);
    Usuario findByLoginAndSenha(String login, String senha);
    Usuario findById(Long id);
    void save(Usuario usuario);
}
