package br.unitins.topicos1.bone.service;

import java.util.List;

import br.unitins.topicos1.bone.dto.CidadeDTO;
import br.unitins.topicos1.bone.dto.CidadeDTOResponse;
import br.unitins.topicos1.bone.model.Cidade;
import br.unitins.topicos1.bone.repository.CidadeRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class CidadeServiceImpl implements CidadeService {
    
    @Inject
    CidadeRepository repository;

    @Override
    public List<CidadeDTOResponse> findAll(){
        return repository
                    .listAll()
                    .stream()
                    .map(m -> CidadeDTOResponse.valueOf(m))
                    .toList();
    }

    @Override
    public List<CidadeDTOResponse> findByNome(String nome){
        return repository
                    .findByNome(nome)
                    .stream().
                    map(m -> CidadeDTOResponse.valueOf(m))
                    .toList();
    }

    @Override
    public CidadeDTOResponse findById(Long id){
        return CidadeDTOResponse.valueOf(repository.findById(id));
    }

    @Override
    public List<CidadeDTOResponse> findByIds(List<Long> ids){
        return repository.findByIds(ids).stream().map(m -> CidadeDTOResponse.valueOf(m)).toList();
    }

    @Override
    @Transactional
    public CidadeDTOResponse create(CidadeDTO dto){

        Cidade marca = new Cidade();

        marca.setNome(dto.nome());

        repository.persist(marca);

        return CidadeDTOResponse.valueOf(marca);
    }

    @Override
    @Transactional
    public void update(Long id, CidadeDTO dto){
        
        Cidade marca = repository.findById(id);

        marca.setNome(dto.nome());
    }

    @Override
    @Transactional
    public void delete(Long id){

        repository.deleteById(id);

    }
}
