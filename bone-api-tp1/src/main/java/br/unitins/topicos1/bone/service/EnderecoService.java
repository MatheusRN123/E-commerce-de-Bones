package br.unitins.topicos1.bone.service;

import java.util.List;

import br.unitins.topicos1.bone.dto.EnderecoDTO;
import br.unitins.topicos1.bone.dto.EnderecoDTOResponse;

public interface EnderecoService {
    List<EnderecoDTOResponse> findAll();
    EnderecoDTOResponse findById(Long id);
    EnderecoDTOResponse create(EnderecoDTO dto);
    void update(Long id, EnderecoDTO dto);
    void delete(Long id);
}
