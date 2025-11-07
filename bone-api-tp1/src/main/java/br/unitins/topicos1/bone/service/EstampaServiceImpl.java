package br.unitins.topicos1.bone.service;

import java.util.List;

import br.unitins.topicos1.bone.dto.EstampaDTOResponse;
import br.unitins.topicos1.bone.model.Estampa;
import br.unitins.topicos1.bone.repository.EstampaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class EstampaServiceImpl implements EstampaService {

    @Inject
    EstampaRepository repository;

    @Override
    public List<EstampaDTOResponse> findAll() {
        return repository.listAll()
                .stream()
                .map(EstampaDTOResponse::valueOf)
                .toList();
    }

    @Override
    public List<EstampaDTOResponse> findByNome(String nome) {
        return repository.findByNome(nome)
                .stream()
                .map(EstampaDTOResponse::valueOf)
                .toList();
    }

    @Override
    public EstampaDTOResponse findById(Long id) {
        Estampa entity = repository.findById(id);
        if(entity == null){
            throw new NotFoundException("Estampa não encontrada");
        }
        return EstampaDTOResponse.valueOf(entity);
    }
}