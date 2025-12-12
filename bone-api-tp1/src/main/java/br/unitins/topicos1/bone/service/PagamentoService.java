package br.unitins.topicos1.bone.service;

import java.util.List;

import br.unitins.topicos1.bone.dto.PagamentoDTOResponse;

public interface PagamentoService {

    List<PagamentoDTOResponse> findAll();
    PagamentoDTOResponse findById(Long id);
    void updateStatus(Long id, String novoStatus);
}
