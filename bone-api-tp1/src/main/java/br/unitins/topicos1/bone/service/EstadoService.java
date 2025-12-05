package br.unitins.topicos1.bone.service;

import java.util.List;

import br.unitins.topicos1.bone.dto.EstadoDTO;
import br.unitins.topicos1.bone.dto.EstadoDTOResponse;

public interface EstadoService {
    List<EstadoDTOResponse> findAll();
    List<EstadoDTOResponse> findByNome(String nome);
    List<EstadoDTOResponse> findBySigla(String sigla);
    EstadoDTOResponse findById(Long id);
    EstadoDTOResponse create(EstadoDTO dto);
    void update(Long id, EstadoDTO dto);
    void delete(Long id);
}
