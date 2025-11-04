package br.unitins.topicos1.bone.service;

import java.util.List;

import br.unitins.topicos1.bone.dto.EstampaDTO;
import br.unitins.topicos1.bone.dto.EstampaDTOResponse;
import br.unitins.topicos1.bone.model.Estampa;
import br.unitins.topicos1.bone.repository.EstampaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class EstampaServiceImpl implements EstampaService {
    
    @Inject
    EstampaRepository repository;

    @Override
    public List<EstampaDTOResponse> findAll(){
        return repository
                    .listAll()
                    .stream()
                    .map(m -> EstampaDTOResponse.valueOf(m))
                    .toList();
    }

    @Override
    public List<EstampaDTOResponse> findByNome(String nome){
        return repository
                    .findByNome(nome)
                    .stream()
                    .map(m -> EstampaDTOResponse.valueOf(m))
                    .toList();
    }

    @Override
    public EstampaDTOResponse findById(Long id){
        return EstampaDTOResponse.valueOf(repository.findById(id));
    }

    @Override
    @Transactional
    public EstampaDTOResponse create(EstampaDTO dto){

        Estampa estampa = new Estampa();

        estampa.setNome(dto.nome());
        estampa.setTipo(dto.tipo());
        estampa.setPosicao(dto.posicao());
        estampa.setDescricao(dto.descricao());

        repository.persist(estampa);

        return EstampaDTOResponse.valueOf(estampa);
    }

    @Override
    @Transactional
    public void update(Long id, EstampaDTO dto){
        
        Estampa estampa = repository.findById(id);

        estampa.setNome(dto.nome());
        estampa.setTipo(dto.tipo());
        estampa.setPosicao(dto.posicao());
        estampa.setDescricao(dto.descricao());
    }

    @Override
    @Transactional
    public void delete(Long id){

        repository.deleteById(id);

    }
}
