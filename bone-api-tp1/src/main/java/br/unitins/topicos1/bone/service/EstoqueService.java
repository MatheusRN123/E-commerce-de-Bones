package br.unitins.topicos1.bone.service;

import br.unitins.topicos1.bone.dto.EstoqueDTO;

public interface EstoqueService {
    
    Boolean verificarDisponibilidade(Long id);
    void atualizarQuantidade(Long id, EstoqueDTO dto);
    void adicionarQuantidade(Long id, EstoqueDTO dto);
}