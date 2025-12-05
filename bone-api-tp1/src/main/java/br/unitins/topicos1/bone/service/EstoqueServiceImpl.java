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
import org.jboss.logging.Logger;

@ApplicationScoped
public class EstoqueServiceImpl implements EstoqueService {

    private static final Logger LOG = Logger.getLogger(EstoqueServiceImpl.class);

    @Inject
    EstoqueRepository repository;

    @Inject
    BoneRepository boneRepository;

    @Override
    public List<EstoqueDTOResponse> findAll() {
        LOG.info("Buscando todos os estoques");
        try {
            var list = repository.listAll()
                    .stream()
                    .map(EstoqueDTOResponse::valueOf)
                    .toList();
            LOG.infof("Encontrados %d estoques", list.size());
            return list;
        } catch (Exception e) {
            LOG.error("Erro ao buscar todos os estoques", e);
            throw e;
        }
    }

    @Override
    public List<EstoqueDTOResponse> findByQuantidade(Integer quantidade) {
        LOG.infof("Buscando estoques com quantidade: %d", quantidade);
        try {
            var list = repository.findByQuantidade(quantidade)
                    .stream()
                    .map(EstoqueDTOResponse::valueOf)
                    .toList();
            LOG.infof("Encontrados %d estoques com quantidade %d", list.size(), quantidade);
            return list;
        } catch (Exception e) {
            LOG.errorf(e, "Erro ao buscar estoques com quantidade: %d", quantidade);
            throw e;
        }
    }

    @Override
    public EstoqueDTOResponse findById(Long id) {
        LOG.infof("Buscando estoque pelo ID: %d", id);
        try {
            var response = EstoqueDTOResponse.valueOf(repository.findById(id));
            LOG.infof("Estoque ID %d encontrado", id);
            return response;
        } catch (Exception e) {
            LOG.errorf(e, "Erro ao buscar estoque ID: %d", id);
            throw e;
        }
    }

    @Override
    @Transactional
    public EstoqueDTOResponse create(EstoqueDTO dto) {
        LOG.infof("Criando estoque com quantidade: %d", dto.quantidade());
        try {
            Estoque estoque = new Estoque();
            estoque.setQuantidade(dto.quantidade());
            estoque.setDataAtualizacao(dto.dataAtualizacao());
            repository.persist(estoque);
            LOG.info("Estoque criado com sucesso");
            return EstoqueDTOResponse.valueOf(estoque);
        } catch (Exception e) {
            LOG.error("Erro ao criar estoque", e);
            throw e;
        }
    }

    @Override
    @Transactional
    public void update(Long id, EstoqueDTO dto) {
        LOG.infof("Atualizando estoque ID: %d", id);
        try {
            Estoque estoque = repository.findById(id);
            estoque.setQuantidade(dto.quantidade());
            estoque.setDataAtualizacao(dto.dataAtualizacao());
            LOG.infof("Estoque ID %d atualizado com sucesso", id);
        } catch (Exception e) {
            LOG.errorf(e, "Erro ao atualizar estoque ID: %d", id);
            throw e;
        }
    }

    @Override
    @Transactional
    public void delete(Long id) {
        LOG.infof("Deletando estoque ID: %d", id);
        try {
            repository.deleteById(id);
            LOG.infof("Estoque ID %d deletado com sucesso", id);
        } catch (Exception e) {
            LOG.errorf(e, "Erro ao deletar estoque ID: %d", id);
            throw e;
        }
    }

    @Override
    @Transactional
    public Boolean verificarDisponibilidade(Long id) {
        LOG.infof("Verificando disponibilidade do estoque ID: %d", id);
        try {
            Estoque estoque = repository.findById(id);
            boolean disponivel = estoque.verificarDisponibilidade();
            LOG.infof("Estoque ID %d disponível: %b", id, disponivel);
            return disponivel;
        } catch (Exception e) {
            LOG.errorf(e, "Erro ao verificar disponibilidade do estoque ID: %d", id);
            throw e;
        }
    }

    @Override
    @Transactional
    public void atualizarQuantidade(Long id, EstoqueDTO dto) {
        LOG.infof("Atualizando quantidade do estoque ID: %d", id);
        try {
            Estoque estoque = repository.findById(id);
            if (estoque != null) {
                estoque.atualizarQuantidade(dto.quantidade());
                LOG.infof("Quantidade do estoque ID %d atualizada", id);
            } else {
                LOG.warnf("Estoque ID %d não encontrado para atualizar quantidade", id);
            }
        } catch (Exception e) {
            LOG.errorf(e, "Erro ao atualizar quantidade do estoque ID: %d", id);
            throw e;
        }
    }

    @Override
    @Transactional
    public void adicionarQuantidade(Long id, EstoqueDTO dto) {
        LOG.infof("Adicionando quantidade ao estoque ID: %d", id);
        try {
            Estoque estoque = repository.findById(id);
            if (estoque != null) {
                estoque.adicionarQuantidade(dto.quantidade());
                LOG.infof("Quantidade adicionada ao estoque ID %d com sucesso", id);
            } else {
                LOG.warnf("Estoque ID %d não encontrado para adicionar quantidade", id);
            }
        } catch (Exception e) {
            LOG.errorf(e, "Erro ao adicionar quantidade ao estoque ID: %d", id);
            throw e;
        }
    }
}
