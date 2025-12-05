package br.unitins.topicos1.bone.service;

import java.util.List;

import br.unitins.topicos1.bone.dto.BoletoDTO;
import br.unitins.topicos1.bone.dto.CartaoDTO;
import br.unitins.topicos1.bone.dto.PagamentoDTOResponse;
import br.unitins.topicos1.bone.dto.PixDTO;

public interface PagamentoService {

    List<PagamentoDTOResponse> findAll();
    PagamentoDTOResponse findById(Long id);

    PagamentoDTOResponse createPix(Long idPedido, PixDTO dto);
    PagamentoDTOResponse createBoleto(Long idPedido, BoletoDTO dto);
    PagamentoDTOResponse createCartao(Long idPedido, CartaoDTO dto);

    void updatePix(Long id, PixDTO dto);
    void updateBoleto(Long id, BoletoDTO dto);
    void updateCartao(Long id, CartaoDTO dto);

    void delete(Long id);
}
