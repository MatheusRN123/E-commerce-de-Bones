package br.unitins.topicos1.bone.service;

import java.util.List;

import br.unitins.topicos1.bone.dto.ModeloDTO;
import br.unitins.topicos1.bone.dto.ModeloDTOResponse;
import br.unitins.topicos1.bone.model.Modelo;
import br.unitins.topicos1.bone.repository.ModeloRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class ModeloServiceImpl implements ModeloService {
    
    @Inject
    ModeloRepository repository;

    @Override
    public List<ModeloDTOResponse> findAll(){
        return repository
                    .listAll()
                    .stream()
                    .map(m -> ModeloDTOResponse.valueOf(m))
                    .toList();
    }

    @Override
    public List<ModeloDTOResponse> findByNome(String nome){
        return repository
                    .findByNome(nome)
                    .stream()
                    .map(m -> ModeloDTOResponse.valueOf(m))
                    .toList();
    }

    @Override
    public ModeloDTOResponse findById(Long id){
        return ModeloDTOResponse.valueOf(repository.findById(id));
    }

    @Override
    @Transactional
    public ModeloDTOResponse create(ModeloDTO dto){

        Modelo modelo = new Modelo();

        modelo.setNome(dto.nome());
        modelo.setCategoria(dto.categoria());
        modelo.setEstilo(dto.estilo());

        repository.persist(modelo);

        return ModeloDTOResponse.valueOf(modelo);
    }

    @Override
    @Transactional
    public void update(Long id, ModeloDTO dto){
        
        Modelo modelo = repository.findById(id);

        modelo.setNome(dto.nome());
        modelo.setCategoria(dto.categoria());
        modelo.setEstilo(dto.estilo());
    }

    @Override
    @Transactional
    public void delete(Long id){

        repository.deleteById(id);

    }
}
