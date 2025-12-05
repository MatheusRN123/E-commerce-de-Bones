package br.unitins.topicos1.bone.service;

import java.util.List;

import br.unitins.topicos1.bone.dto.EnderecoDTO;
import br.unitins.topicos1.bone.dto.EnderecoDTOResponse;

public interface EnderecoService {
    List<EnderecoDTOResponse> findAll();
<<<<<<< HEAD
=======
    List<EnderecoDTOResponse> findByNome(String nome);
>>>>>>> a47f27e82323fbf2d3a412623eb607e53f435613
    List<EnderecoDTOResponse> findByCep(String cep);
    EnderecoDTOResponse findById(Long id);
    EnderecoDTOResponse create(EnderecoDTO dto);
    void update(Long id, EnderecoDTO dto);
    void delete(Long id);
}
