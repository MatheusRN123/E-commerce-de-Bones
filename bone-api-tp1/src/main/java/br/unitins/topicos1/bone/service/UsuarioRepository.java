package br.unitins.topicos1.bone.service;

import java.util.List;

import br.unitins.topicos1.bone.dto.UsuarioDTO;
import br.unitins.topicos1.bone.dto.UsuarioDTOResponse;

public interface UsuarioRepository {
    List<UsuarioDTOResponse> findAll();
    List<UsuarioDTOResponse> findByNome(String nome);
    UsuarioDTOResponse findById(Long id);
    UsuarioDTOResponse create(UsuarioDTO dto);
    void update(Long id, UsuarioDTO dto);
    void delete(Long id);
}
