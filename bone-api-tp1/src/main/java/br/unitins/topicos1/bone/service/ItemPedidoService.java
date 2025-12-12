package br.unitins.topicos1.bone.service;

import java.util.List;

import br.unitins.topicos1.bone.dto.ItemPedidoDTO;
import br.unitins.topicos1.bone.dto.ItemPedidoDTOResponse;

public interface ItemPedidoService {
    List<ItemPedidoDTOResponse> findAll();
    ItemPedidoDTOResponse findById(Long id);
    void update(Long id, ItemPedidoDTO dto);
    void delete(Long id);
}