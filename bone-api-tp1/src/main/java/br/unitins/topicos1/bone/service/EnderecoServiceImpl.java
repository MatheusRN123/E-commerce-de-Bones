package br.unitins.topicos1.bone.service;

import java.util.List;

import br.unitins.topicos1.bone.dto.EnderecoDTO;
import br.unitins.topicos1.bone.dto.EnderecoDTOResponse;
import br.unitins.topicos1.bone.model.Cidade;
import br.unitins.topicos1.bone.model.Endereco;
import br.unitins.topicos1.bone.model.Pedido;
import br.unitins.topicos1.bone.repository.CidadeRepository;
import br.unitins.topicos1.bone.repository.EnderecoRepository;
import br.unitins.topicos1.bone.repository.PedidoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.jboss.logging.Logger;

@ApplicationScoped
public class EnderecoServiceImpl implements EnderecoService {

    private static final Logger LOG = Logger.getLogger(EnderecoServiceImpl.class);

    @Inject
    EnderecoRepository repository;

    @Inject
    CidadeRepository cidadeRepository;

    @Inject
    PedidoRepository pedidoRepository;

    @Override
    public List<EnderecoDTOResponse> findAll() {
        LOG.info("Buscando todos os endereços");
        try {
            var list = repository.listAll()
                    .stream()
                    .map(EnderecoDTOResponse::valueOf)
                    .toList();
            LOG.infof("%d endereços encontrados", list.size());
            return list;
        } catch (Exception e) {
            LOG.error("Erro ao buscar todos os endereços", e);
            throw e;
        }
    }

    @Override
    public List<EnderecoDTOResponse> findByCep(String cep) {
        LOG.infof("Buscando endereços pelo CEP: %s", cep);
        try {
            var list = repository.findByCep(cep)
                    .stream()
                    .map(EnderecoDTOResponse::valueOf)
                    .toList();
            LOG.infof("%d endereços encontrados para o CEP %s", list.size(), cep);
            return list;
        } catch (Exception e) {
            LOG.errorf(e, "Erro ao buscar endereços pelo CEP: %s", cep);
            throw e;
        }
    }

    @Override
    public EnderecoDTOResponse findById(Long id) {
        LOG.infof("Buscando endereço pelo ID: %d", id);
        try {
            Endereco endereco = repository.findById(id);
            if (endereco == null) {
                LOG.warnf("Endereço com ID %d não encontrado", id);
                return null;
            }
            LOG.infof("Endereço com ID %d encontrado", id);
            return EnderecoDTOResponse.valueOf(endereco);
        } catch (Exception e) {
            LOG.errorf(e, "Erro ao buscar endereço pelo ID: %d", id);
            throw e;
        }
    }

    @Override
    @Transactional
    public EnderecoDTOResponse create(EnderecoDTO dto) {
        LOG.infof("Criando endereço: %s, CEP: %s", dto.logradouro(), dto.cep());
        try {
            Endereco endereco = new Endereco();
            endereco.setCep(dto.cep());
            endereco.setLogradouro(dto.logradouro());
            endereco.setNumero(dto.numero());

            Cidade cidade = cidadeRepository.findById(dto.idCidade());
            endereco.setCidade(cidade);

            Pedido pedido = pedidoRepository.findById(dto.idPedido());
            endereco.setPedido(pedido);

            repository.persist(endereco);

            LOG.infof("Endereço criado com sucesso: %s, ID: %d", dto.logradouro(), endereco.getId());
            return EnderecoDTOResponse.valueOf(endereco);
        } catch (Exception e) {
            LOG.errorf(e, "Erro ao criar endereço: %s", dto.logradouro());
            throw e;
        }
    }

    @Override
    @Transactional
    public void update(Long id, EnderecoDTO dto) {
        LOG.infof("Atualizando endereço ID: %d", id);
        try {
            Endereco endereco = repository.findById(id);
            if (endereco == null) {
                LOG.warnf("Endereço ID %d não encontrado para atualização", id);
                return;
            }

            endereco.setCep(dto.cep());
            endereco.setLogradouro(dto.logradouro());
            endereco.setNumero(dto.numero());

            Cidade cidade = cidadeRepository.findById(dto.idCidade());
            endereco.setCidade(cidade);

            Pedido pedido = pedidoRepository.findById(dto.idPedido());
            endereco.setPedido(pedido);

            LOG.infof("Endereço ID %d atualizado com sucesso", id);
        } catch (Exception e) {
            LOG.errorf(e, "Erro ao atualizar endereço ID: %d", id);
            throw e;
        }
    }

    @Override
    @Transactional
    public void delete(Long id) {
        LOG.infof("Deletando endereço ID: %d", id);
        try {
            repository.deleteById(id);
            LOG.infof("Endereço ID %d deletado com sucesso", id);
        } catch (Exception e) {
            LOG.errorf(e, "Erro ao deletar endereço ID: %d", id);
            throw e;
        }
    }
}
