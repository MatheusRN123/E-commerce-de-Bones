package br.unitins.topicos1.bone.service;

import java.util.List;

import br.unitins.topicos1.bone.dto.EstampaDTO;
import br.unitins.topicos1.bone.dto.EstampaDTOResponse;

public interface EstampaService {
    
    List<EstampaDTOResponse> findAll();
    List<EstampaDTOResponse> findByNome(String nome);
    EstampaDTOResponse findById(Long id);
    EstampaDTOResponse create(EstampaDTO dto);
    void update(Long id, EstampaDTO dto);
    void delete(Long id);
}
