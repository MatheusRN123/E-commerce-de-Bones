package br.unitins.topicos1.bone.service;

import java.util.List;

import br.unitins.topicos1.bone.dto.PagamentoDTOResponse;
import br.unitins.topicos1.bone.model.Pagamento;
import br.unitins.topicos1.bone.repository.PagamentoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import org.jboss.logging.Logger;

@ApplicationScoped
public class PagamentoServiceImpl implements PagamentoService {

    private static final Logger LOG = Logger.getLogger(PagamentoServiceImpl.class);

    @Inject
    PagamentoRepository pagamentoRepository;

    @Override
    public List<PagamentoDTOResponse> findAll() {
        LOG.info("Buscando todos os pagamentos");
        return pagamentoRepository.listAll()
            .stream()
            .map(PagamentoDTOResponse::valueOf)
            .toList();
    }

    @Override
    public PagamentoDTOResponse findById(Long id) {
        LOG.infof("Buscando pagamento com ID %d", id);
        Pagamento pagamento = pagamentoRepository.findById(id);
        if (pagamento == null) {
            LOG.warnf("Pagamento com ID %d não encontrado", id);
            return null;
        }
        return PagamentoDTOResponse.valueOf(pagamento);
    }

    @Override
    @Transactional
    public void updateStatus(Long id, String novoStatus) {
        LOG.infof("Atualizando status do pagamento com ID %d para '%s'", id, novoStatus);
        
        Pagamento pagamento = pagamentoRepository.findById(id);
        if (pagamento == null) {
            LOG.warnf("Pagamento com ID %d não encontrado", id);
            throw new NotFoundException("Pagamento não encontrado");
        }

        if (!"PAGO".equalsIgnoreCase(novoStatus) && !"PENDENTE".equalsIgnoreCase(novoStatus)) {
            LOG.warnf("Status inválido: %s", novoStatus);
            throw new IllegalArgumentException("Status deve ser 'PAGO' ou 'PENDENTE'");
        }

        pagamento.setStatus(novoStatus.toUpperCase());
        pagamentoRepository.persist(pagamento);

        LOG.infof("Pagamento com ID %d atualizado para status '%s'", id, pagamento.getStatus());
    }
}
