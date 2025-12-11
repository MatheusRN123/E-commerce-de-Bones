package br.unitins.topicos1.bone.service;

import java.time.LocalDateTime;
import java.util.List;

import br.unitins.topicos1.bone.dto.PedidoDTO;
import br.unitins.topicos1.bone.dto.PedidoDTOResponse;
import br.unitins.topicos1.bone.dto.PagamentoDTO;
import br.unitins.topicos1.bone.dto.BoletoDTO;
import br.unitins.topicos1.bone.dto.CartaoDTO;
import br.unitins.topicos1.bone.dto.ItemPedidoDTO;
import br.unitins.topicos1.bone.model.*;
import br.unitins.topicos1.bone.repository.*;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.jboss.logging.Logger;

@ApplicationScoped
public class PedidoServiceImpl implements PedidoService {

    private static final Logger LOG = Logger.getLogger(PedidoServiceImpl.class);

    @Inject
    PedidoRepository repository;

    @Inject
    UsuarioRepository usuarioRepository;

    @Inject
    EnderecoRepository enderecoRepository;

    @Inject
    BoneRepository boneRepository;

    @Override
    public List<PedidoDTOResponse> findAll() {
        LOG.info("Buscando todos os pedidos");
        List<PedidoDTOResponse> pedidos = repository.listAll()
                .stream()
                .map(PedidoDTOResponse::valueOf)
                .toList();
        LOG.infof("Encontrados %d pedidos", pedidos.size());
        return pedidos;
    }

    @Override
    public PedidoDTOResponse findById(Long id) {
        LOG.infof("Buscando pedido pelo ID: %d", id);
        Pedido pedido = repository.findById(id);
        if (pedido == null) {
            LOG.warnf("Pedido com ID %d não encontrado", id);
            return null;
        }
        LOG.infof("Pedido encontrado: ID %d", pedido.getId());
        return PedidoDTOResponse.valueOf(pedido);
    }

    @Override
    @Transactional
    public PedidoDTOResponse create(PedidoDTO dto) {
        LOG.infof("Criando pedido para usuário ID %d", dto.idUsuario());

        Pedido pedido = new Pedido();
        pedido.setData(LocalDateTime.now());

        Usuario usuario = usuarioRepository.findById(dto.idUsuario());
        pedido.setUsuario(usuario);

        Endereco endereco = enderecoRepository.findById(dto.idEndereco());
        pedido.setEndereco(endereco);

        List<ItemPedido> itens = dto.itens().stream()
                .map(i -> criarItem(i, pedido))
                .toList();
        pedido.setItens(itens);

        Pagamento pagamento = criarPagamento(dto.pagamento(), pedido);
        pedido.setPagamento(pagamento);

        repository.persist(pedido);

        LOG.infof("Pedido criado com sucesso. ID: %d", pedido.getId());
        return PedidoDTOResponse.valueOf(pedido);
    }

    @Override
    @Transactional
    public void update(Long id, PedidoDTO dto) {
        LOG.infof("Atualizando pedido ID: %d", id);

        Pedido pedido = repository.findById(id);
        if (pedido == null) {
            LOG.warnf("Pedido com ID %d não encontrado para atualização", id);
            return;
        }

        Usuario usuario = usuarioRepository.findById(dto.idUsuario());
        pedido.setUsuario(usuario);

        Endereco endereco = enderecoRepository.findById(dto.idEndereco());
        pedido.setEndereco(endereco);

        pedido.getItens().clear();

        for (ItemPedidoDTO itemDTO : dto.itens()) {
            ItemPedido item = criarItem(itemDTO, pedido);
            pedido.getItens().add(item);
        }


        Pagamento pagamento = criarPagamento(dto.pagamento(), pedido);
        pedido.setPagamento(pagamento);

        LOG.infof("Pedido ID %d atualizado com sucesso", id);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        LOG.infof("Deletando pedido ID: %d", id);
        repository.deleteById(id);
        LOG.infof("Pedido ID %d deletado com sucesso", id);
    }

    private Pagamento criarPagamento(PagamentoDTO dto,  Pedido pedido) {
        if (dto == null)
            throw new IllegalArgumentException("Pagamento   não informado");
    
        LOG.infof("Criando pagamento do tipo %s para    pedido ID %d", dto.tipoPagamento(), pedido.getId());
    
        Pagamento pagamento;
    
        switch (dto.tipoPagamento().toUpperCase()) {
            case "PIX" -> {
                Pix pix = new Pix();
                pix.setChave(dto.pix().chave());
                pix.setTipoChave(dto.pix().tipoChave());
                pagamento = pix;
            }
            case "CARTAO" -> {
                Cartao cartao = new Cartao();
                CartaoDTO c = dto.cartao();
                cartao.setNomeTitular(c.nomeTitular());
                cartao.setNumero(c.numero());
                cartao.setValidade(c.validade());
                cartao.setCvv(c.cvv());
                pagamento = cartao;
            }
            case "BOLETO" -> {
                Boleto boleto = new Boleto();
                BoletoDTO b = dto.boleto();
                boleto.setCodigoBarras(b.codigoBarras());
                boleto.setDataVencimento(b.dataVencimento());
                pagamento = boleto;
            }
            default -> throw new IllegalArgumentException   ("Tipo de pagamento inválido: " + dto. tipoPagamento());
        }
    
        // Dados comuns do pagamento
        pagamento.setPedido(pedido);
        pagamento.setData(LocalDateTime.now());
        pagamento.setValor(pedido.getValorTotal());
        pagamento.setStatus("PENDENTE");
    
        return pagamento;
    }


    private ItemPedido criarItem(ItemPedidoDTO dto, Pedido pedido) {
        LOG.infof("Criando item de pedido para Bone ID %d, quantidade %d", dto.idBone(), dto.quantidade());
        ItemPedido item = new ItemPedido();
        item.setBone(boneRepository.findById(dto.idBone()));
        item.setQuantidade(dto.quantidade());
        item.setPreco(dto.preco());
        item.setPedido(pedido);
        LOG.infof("Item criado: Bone ID %d, Preço %.2f", dto.idBone(), dto.preco());
        return item;
    }

}
