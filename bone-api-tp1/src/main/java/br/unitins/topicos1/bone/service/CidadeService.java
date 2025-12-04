package br.unitins.topicos1.bone.service;

import java.util.List;

import br.unitins.topicos1.bone.dto.CidadeDTO;
import br.unitins.topicos1.bone.dto.CidadeDTOResponse;

public interface CidadeService {
    List<CidadeDTOResponse> findAll();
    List<CidadeDTOResponse> findByNome(String nome);
    CidadeDTOResponse findById(Long id);
    List<CidadeDTOResponse> findByIds(List<Long> ids);
    CidadeDTOResponse create(CidadeDTO dto);
    void update(Long id, CidadeDTO dto);
    void delete(Long id);
}
