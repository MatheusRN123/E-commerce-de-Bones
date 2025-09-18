package br.unitins.topicos1.bone.service;

import java.util.List;

import br.unitins.topicos1.bone.dto.BoneDTO;
import br.unitins.topicos1.bone.model.Bone;

public interface BoneService {
    List<Bone> findAll();
    List<Bone> findByNome(String nome);
    Bone findById(Long id);
    Bone create(BoneDTO dto);
    void update(Long id, BoneDTO dto);
    void delete(Long id);
}