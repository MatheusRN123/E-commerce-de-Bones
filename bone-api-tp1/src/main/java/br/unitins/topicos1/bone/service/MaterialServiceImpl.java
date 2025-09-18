package br.unitins.topicos1.bone.service;

import java.util.List;

import br.unitins.topicos1.bone.dto.MaterialDTO;
import br.unitins.topicos1.bone.model.Material;
import br.unitins.topicos1.bone.repository.MaterialRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class MaterialServiceImpl implements MaterialService {
    
    @Inject
    MaterialRepository repository;

    @Override
    public List<Material> findAll(){
        return repository.listAll();
    }

    @Override
    public List<Material> findByNome(String nome){
        return repository.findByNome(nome);
    }

    @Override
    public Material findById(Long id){
        return repository.findById(id);
    }

    @Override
    @Transactional
    public Material create(MaterialDTO dto){

        Material material = new Material();

        material.setNome(dto.nome());

        repository.persist(material);

        return material;
    }

    @Override
    @Transactional
    public void update(Long id, MaterialDTO dto){
        
        Material material = repository.findById(id);

        material.setNome(dto.nome());
    }

    @Override
    @Transactional
    public void delete(Long id){

        repository.deleteById(id);

    }
}
