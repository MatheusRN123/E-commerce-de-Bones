package br.unitins.topicos1.bone.service;

import java.util.List;

import br.unitins.topicos1.bone.dto.EstoqueDTO;
import br.unitins.topicos1.bone.dto.EstoqueDTOResponse;
import br.unitins.topicos1.bone.model.Estoque;
import br.unitins.topicos1.bone.repository.BoneRepository;
import br.unitins.topicos1.bone.repository.EstoqueRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class EstoqueServiceImpl implements EstoqueService {
    
    @Inject
    EstoqueRepository repository;

    @Inject
    BoneRepository boneRepository;

    @Override
    public List<EstoqueDTOResponse> findAll(){
        return repository
                    .listAll()
                    .stream()
                    .map(m -> EstoqueDTOResponse.valueOf(m))
                    .toList();
    }

    @Override
    public List<EstoqueDTOResponse> findByQuantidade(Integer quantidade){
        return repository
                    .findByQuantidade(quantidade)
                    .stream().
                    map(m -> EstoqueDTOResponse.valueOf(m))
                    .toList();
    }

    @Override
    public EstoqueDTOResponse findById(Long id){
        return EstoqueDTOResponse.valueOf(repository.findById(id));
    }

    @Override
    @Transactional
    public EstoqueDTOResponse create(EstoqueDTO dto){

        Estoque estoque = new Estoque();

        estoque.setQuantidade(dto.quantidade());
        estoque.setDataAtualizacao(dto.dataAtualizacao());

        repository.persist(estoque);

        return EstoqueDTOResponse.valueOf(estoque);
    }

    @Override
    @Transactional
    public void update(Long id, EstoqueDTO dto){
        
        Estoque estoque = repository.findById(id);

        estoque.setQuantidade(dto.quantidade());
        estoque.setDataAtualizacao(dto.dataAtualizacao());
    }

    @Override
    @Transactional
    public void delete(Long id){

        repository.deleteById(id);

    }

    @Override
    @Transactional
    public Boolean verificarDisponibilidade(Long id){
        
        Estoque estoque = repository.findById(id);

        return estoque.verificarDisponibilidade();
    }

    @Override
    @Transactional
    public void atualizarQuantidade(Long id, EstoqueDTO dto){
        
        Estoque estoque = repository.findById(id);

        if(estoque != null){
            estoque.atualizarQuantidade(dto.quantidade());
        }
    }

    @Override
    @Transactional
    public void adicionarQuantidade(Long id, EstoqueDTO dto){
        
        Estoque estoque = repository.findById(id);

        if(estoque != null){
            estoque.adicionarQuantidade(dto.quantidade());
        }
    }
}
