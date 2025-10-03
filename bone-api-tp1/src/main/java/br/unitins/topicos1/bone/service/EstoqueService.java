package br.unitins.topicos1.bone.service;

import java.util.List;

import br.unitins.topicos1.bone.dto.EstoqueDTO;
import br.unitins.topicos1.bone.dto.EstoqueDTOResponse;

public interface EstoqueService {
    
    List<EstoqueDTOResponse> findAll();
    List<EstoqueDTOResponse> findByQuantidade(Integer quantidade);
    EstoqueDTOResponse findByBoneId(Long id);
    EstoqueDTOResponse findById(Long id);
    EstoqueDTOResponse create(EstoqueDTO dto);
    void update(Long id, EstoqueDTO dto);
    void delete(Long id);
    Boolean verificarDisponibilidade(Long id);
    void atualizarQuantidade(Long id, EstoqueDTO dto);
    void adicionarQuantidade(Long id, EstoqueDTO dto);
}