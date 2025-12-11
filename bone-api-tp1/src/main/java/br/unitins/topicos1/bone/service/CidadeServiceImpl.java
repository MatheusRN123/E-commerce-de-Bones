package br.unitins.topicos1.bone.service;

import java.util.List;

import br.unitins.topicos1.bone.dto.CidadeDTO;
import br.unitins.topicos1.bone.dto.CidadeDTOResponse;
import br.unitins.topicos1.bone.model.Cidade;
import br.unitins.topicos1.bone.model.Estado;
import br.unitins.topicos1.bone.repository.CidadeRepository;
import br.unitins.topicos1.bone.repository.EstadoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.jboss.logging.Logger;

@ApplicationScoped
public class CidadeServiceImpl implements CidadeService {

    private static final Logger LOG = Logger.getLogger(CidadeServiceImpl.class);

    @Inject
    CidadeRepository repository;

    @Inject
    EstadoRepository estadoRepository;

    @Override
    public List<CidadeDTOResponse> findAll() {
        LOG.info("Buscando todas as cidades");
        List<CidadeDTOResponse> response = repository
                .listAll()
                .stream()
                .map(CidadeDTOResponse::valueOf)
                .toList();
        LOG.debugf("Total de cidades encontradas: %d", response.size());
        return response;
    }

    @Override
    public List<CidadeDTOResponse> findByNome(String nome) {
        LOG.infof("Buscando cidades pelo nome: %s", nome);
        List<CidadeDTOResponse> response = repository
                .findByNome(nome)
                .stream()
                .map(CidadeDTOResponse::valueOf)
                .toList();
        LOG.debugf("Total de cidades encontradas com nome '%s': %d", nome, response.size());
        return response;
    }

    @Override
    public CidadeDTOResponse findById(Long id) {
        LOG.infof("Buscando cidade pelo ID: %d", id);
        Cidade cidade = repository.findById(id);
        if (cidade == null) {
            LOG.warnf("Cidade com ID %d não encontrada", id);
            return null;
        }
        return CidadeDTOResponse.valueOf(cidade);
    }

    @Override
    @Transactional
    public CidadeDTOResponse create(CidadeDTO dto) {
        LOG.infof("Criando nova cidade: %s", dto.nome());
        try {
            Cidade cidade = new Cidade();
            cidade.setNome(dto.nome());

            Estado estado = estadoRepository.findById(dto.idEstado());
            cidade.setEstado(estado);

            repository.persist(cidade);

            LOG.infof("Cidade '%s' criada com sucesso", dto.nome());
            return CidadeDTOResponse.valueOf(cidade);
        } catch (Exception e) {
            LOG.errorf(e, "Erro ao criar cidade: %s", dto.nome());
            throw e;
        }
    }

    @Override
    @Transactional
    public void update(Long id, CidadeDTO dto) {
        LOG.infof("Atualizando cidade ID: %d", id);
        try {
            Cidade cidade = repository.findById(id);

            cidade.setNome(dto.nome());
            Estado estado = estadoRepository.findById(dto.idEstado());
            cidade.setEstado(estado);

            cidade.setEnderecos(null);

            LOG.infof("Cidade ID %d atualizada com sucesso", id);
        } catch (Exception e) {
            LOG.errorf(e, "Erro ao atualizar cidade ID: %d", id);
            throw e;
        }
    }

    @Override
    @Transactional
    public void delete(Long id) {
        LOG.infof("Excluindo cidade ID: %d", id);
        try {
            boolean deleted = repository.deleteById(id);
            if (deleted) {
                LOG.infof("Cidade ID %d excluída com sucesso", id);
            } else {
                LOG.warnf("Cidade ID %d não encontrada para exclusão", id);
            }
        } catch (Exception e) {
            LOG.errorf(e, "Erro ao excluir cidade ID: %d", id);
            throw e;
        }
    }
}
