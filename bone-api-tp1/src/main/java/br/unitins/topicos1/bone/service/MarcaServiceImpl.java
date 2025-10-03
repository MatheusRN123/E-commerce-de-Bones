package br.unitins.topicos1.bone.service;

import java.util.List;

import br.unitins.topicos1.bone.dto.MarcaDTO;
import br.unitins.topicos1.bone.dto.MarcaDTOResponse;
import br.unitins.topicos1.bone.model.Marca;
import br.unitins.topicos1.bone.repository.MarcaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class MarcaServiceImpl implements MarcaService {
    
    @Inject
    MarcaRepository repository;

    @Override
    public List<MarcaDTOResponse> findAll(){
        return repository
                    .listAll()
                    .stream()
                    .map(m -> MarcaDTOResponse.valueOf(m))
                    .toList();
    }

    @Override
    public List<MarcaDTOResponse> findByNome(String nome){
        return repository
                    .findByNome(nome)
                    .stream().
                    map(m -> MarcaDTOResponse.valueOf(m))
                    .toList();
    }

    @Override
    public MarcaDTOResponse findById(Long id){
        return MarcaDTOResponse.valueOf(repository.findById(id));
    }

    @Override
    @Transactional
    public MarcaDTOResponse create(MarcaDTO dto){

        Marca marca = new Marca();

        marca.setNome(dto.nome());

        repository.persist(marca);

        return MarcaDTOResponse.valueOf(marca);
    }

    @Override
    @Transactional
    public void update(Long id, MarcaDTO dto){
        
        Marca marca = repository.findById(id);

        marca.setNome(dto.nome());
    }

    @Override
    @Transactional
    public void delete(Long id){

        repository.deleteById(id);

    }
}
