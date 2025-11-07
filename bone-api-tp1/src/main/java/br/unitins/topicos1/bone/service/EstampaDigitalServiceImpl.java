package br.unitins.topicos1.bone.service;

import br.unitins.topicos1.bone.dto.EstampaDigitalDTO;
import br.unitins.topicos1.bone.dto.EstampaDigitalDTOResponse;
import br.unitins.topicos1.bone.model.EstampaDigital;
import br.unitins.topicos1.bone.repository.EstampaDigitalRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class EstampaDigitalServiceImpl implements EstampaDigitalService {

    @Inject
    EstampaDigitalRepository repository;

    @Override
    @Transactional
    public EstampaDigitalDTOResponse create(EstampaDigitalDTO dto) {
        EstampaDigital estampa = new EstampaDigital();

        estampa.setNome(dto.nome());
        estampa.setPosicao(dto.posicao());
        estampa.setDescricao(dto.descricao());
        estampa.setResolucao(dto.resolucao());

        repository.persist(estampa);
        return EstampaDigitalDTOResponse.valueOf(estampa);
    }

    @Override
    @Transactional
    public void update(Long id, EstampaDigitalDTO dto) {
        EstampaDigital estampa = repository.findById(id);
        if (estampa == null){
            throw new NotFoundException("Estampa digital não encontrada");
        }
        estampa.setNome(dto.nome());
        estampa.setPosicao(dto.posicao());
        estampa.setDescricao(dto.descricao());
        estampa.setResolucao(dto.resolucao());
    }

    @Override
    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
