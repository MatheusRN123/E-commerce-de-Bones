package br.unitins.topicos1.bone.service;


import br.unitins.topicos1.bone.dto.EstampaBordadaDTO;
import br.unitins.topicos1.bone.dto.EstampaBordadaDTOResponse;
import br.unitins.topicos1.bone.model.EstampaBordada;
import br.unitins.topicos1.bone.repository.EstampaBordadaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class EstampaBordadaServiceImpl implements EstampaBordadaService {

    @Inject
    EstampaBordadaRepository repository;

    @Override
    @Transactional
    public EstampaBordadaDTOResponse create(EstampaBordadaDTO dto) {
        EstampaBordada estampa = new EstampaBordada();

        estampa.setNome(dto.nome());
        estampa.setPosicao(dto.posicao());
        estampa.setDescricao(dto.descricao());
        estampa.setCorLinha(dto.corLinha());
        estampa.setQuantCores(dto.quantCores());

        repository.persist(estampa);
        return EstampaBordadaDTOResponse.valueOf(estampa);
    }

    @Override
    @Transactional
    public void update(Long id, EstampaBordadaDTO dto) {
        EstampaBordada estampa = repository.findById(id);
        if (estampa == null)
            throw new NotFoundException("Estampa bordada não encontrada");

        estampa.setNome(dto.nome());
        estampa.setPosicao(dto.posicao());
        estampa.setDescricao(dto.descricao());
        estampa.setCorLinha(dto.corLinha());
        estampa.setQuantCores(dto.quantCores());
    }

    @Override
    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
