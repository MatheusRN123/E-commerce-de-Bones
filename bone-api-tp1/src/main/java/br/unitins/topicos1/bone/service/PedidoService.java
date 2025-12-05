package br.unitins.topicos1.bone.service;

import java.util.List;

import br.unitins.topicos1.bone.dto.PedidoDTO;
import br.unitins.topicos1.bone.dto.PedidoDTOResponse;

public interface PedidoService {
    
    List<PedidoDTOResponse> findAll();
    PedidoDTOResponse findById(Long id);
    PedidoDTOResponse create(PedidoDTO dto);
    void update(Long id, PedidoDTO dto);
    void delete(Long id);

}
