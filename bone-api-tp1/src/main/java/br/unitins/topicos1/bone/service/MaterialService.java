package br.unitins.topicos1.bone.service;

import java.util.List;

import br.unitins.topicos1.bone.dto.MaterialDTO;
import br.unitins.topicos1.bone.model.Material;

public interface MaterialService {
    
    List<Material> findAll();
    List<Material> findByNome(String nome);
    Material findById(Long id);
    Material create(MaterialDTO dto);
    void update(Long id, MaterialDTO dto);
    void delete(Long id);
}
