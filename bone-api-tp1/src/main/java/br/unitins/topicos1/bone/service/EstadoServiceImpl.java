package br.unitins.topicos1.bone.service;

import java.util.List;

import br.unitins.topicos1.bone.dto.EstadoDTO;
import br.unitins.topicos1.bone.dto.EstadoDTOResponse;
import br.unitins.topicos1.bone.model.Cidade;
import br.unitins.topicos1.bone.model.Estado;
import br.unitins.topicos1.bone.repository.CidadeRepository;
import br.unitins.topicos1.bone.repository.EstadoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class EstadoServiceImpl implements EstadoService {

    @Inject
    EstadoRepository estadoRepository;

    @Inject
    CidadeRepository cidadeRepository;

    @Override
    public List<EstadoDTOResponse> findAll() {
        return estadoRepository
                .listAll()
                .stream()
                .map(EstadoDTOResponse::valueOf)
                .toList();
    }

    @Override
    public EstadoDTOResponse findById(Long id) {
        Estado estado = estadoRepository.findById(id);

        if (estado == null)
            throw new NotFoundException("Estado não encontrado");

        return EstadoDTOResponse.valueOf(estado);
    }

    @Override
    public List<EstadoDTOResponse> findByNome(String nome) {
        return estadoRepository
                .find("LOWER(nome) LIKE LOWER(?1)", "%" + nome + "%")
                .list()
                .stream()
                .map(EstadoDTOResponse::valueOf)
                .toList();
    }

    @Override
    public List<EstadoDTOResponse> findBySigla(String sigla){
        return estadoRepository.findBySigla(sigla)
        .stream()
        .map(EstadoDTOResponse::valueOf).
        toList();
    }

    @Override
    @Transactional
    public EstadoDTOResponse create(EstadoDTO dto) {

        Estado estado = new Estado();
        estado.setNome(dto.nome());
        estado.setSigla(dto.sigla());

        // associa cidades
        if (dto.idsCidades() != null && !dto.idsCidades().isEmpty()) {

            List<Cidade> cidades = cidadeRepository.findByIds(dto.idsCidades());

            if (cidades.size() != dto.idsCidades().size()) {
                throw new IllegalArgumentException("Uma ou mais cidades informadas não existem.");
            }

            estado.setCidades(cidades);
        }

        estadoRepository.persist(estado);

        return EstadoDTOResponse.valueOf(estado);
    }

    @Override
    @Transactional
    public void update(Long id, EstadoDTO dto) {

        Estado estado = estadoRepository.findById(id);

        if (estado == null)
            throw new NotFoundException("Estado não encontrado");

        estado.setNome(dto.nome());
        estado.setSigla(dto.sigla());

        if (dto.idsCidades() == null || dto.idsCidades().isEmpty()) {
            estado.getCidades().clear();
            return;
        }

        List<Cidade> cidades = cidadeRepository.findByIds(dto.idsCidades());

        if (cidades.size() != dto.idsCidades().size()) {
            throw new IllegalArgumentException("Uma ou mais cidades informadas não existem.");
        }

        estado.setCidades(cidades);
    }

    @Override
    @Transactional
    public void delete(Long id) {

        boolean removed = estadoRepository.deleteById(id);

        if (!removed)
            throw new NotFoundException("Estado não encontrado");
    }
}
