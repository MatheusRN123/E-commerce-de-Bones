package br.unitins.topicos1.bone.service;

import java.util.List;

import br.unitins.topicos1.bone.dto.BoletoDTO;
import br.unitins.topicos1.bone.dto.CartaoDTO;
import br.unitins.topicos1.bone.dto.PagamentoDTOResponse;
import br.unitins.topicos1.bone.dto.PixDTO;
import br.unitins.topicos1.bone.model.Boleto;
import br.unitins.topicos1.bone.model.Cartao;
import br.unitins.topicos1.bone.model.Pagamento;
import br.unitins.topicos1.bone.model.Pedido;
import br.unitins.topicos1.bone.model.Pix;
import br.unitins.topicos1.bone.repository.PagamentoRepository;
import br.unitins.topicos1.bone.repository.PedidoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
<<<<<<< HEAD
import org.jboss.logging.Logger;
=======
>>>>>>> a47f27e82323fbf2d3a412623eb607e53f435613

@ApplicationScoped
public class PagamentoServiceImpl implements PagamentoService {

<<<<<<< HEAD
    private static final Logger LOG = Logger.getLogger(PagamentoServiceImpl.class);

=======
>>>>>>> a47f27e82323fbf2d3a412623eb607e53f435613
    @Inject
    PagamentoRepository pagamentoRepository;

    @Inject
    PedidoRepository pedidoRepository;

<<<<<<< HEAD
    @Override
    public List<PagamentoDTOResponse> findAll() {
        LOG.info("Buscando todos os pagamentos");
=======

    @Override
    public List<PagamentoDTOResponse> findAll() {
>>>>>>> a47f27e82323fbf2d3a412623eb607e53f435613
        return pagamentoRepository.listAll()
            .stream()
            .map(PagamentoDTOResponse::valueOf)
            .toList();
    }

    @Override
    public PagamentoDTOResponse findById(Long id) {
<<<<<<< HEAD
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
    public PagamentoDTOResponse createPix(Long idPedido, PixDTO dto) {
        LOG.infof("Criando Pix para pedido ID %d", idPedido);
=======
        Pagamento pagamento = pagamentoRepository.findById(id);
        if (pagamento == null)
            return null;
        return PagamentoDTOResponse.valueOf(pagamento);
    }


    @Override
    @Transactional
    public PagamentoDTOResponse createPix(Long idPedido, PixDTO dto) {

>>>>>>> a47f27e82323fbf2d3a412623eb607e53f435613
        Pedido pedido = pedidoRepository.findById(idPedido);
        if (pedido == null)
            throw new NotFoundException("Pedido não encontrado");

        Pix pix = new Pix();
        pix.setPedido(pedido);
        pix.setValor(pedido.getValorTotal());
        pix.setStatus("PENDENTE");
<<<<<<< HEAD
=======

>>>>>>> a47f27e82323fbf2d3a412623eb607e53f435613
        pix.setChave(dto.chave());
        pix.setTipoChave(dto.tipoChave());

        pagamentoRepository.persist(pix);
<<<<<<< HEAD
        LOG.infof("Pix criado com ID %d para pedido ID %d", pix.getId(), idPedido);
=======

>>>>>>> a47f27e82323fbf2d3a412623eb607e53f435613
        return PagamentoDTOResponse.valueOf(pix);
    }

    @Override
    @Transactional
    public PagamentoDTOResponse createBoleto(Long idPedido, BoletoDTO dto) {
<<<<<<< HEAD
        LOG.infof("Criando Boleto para pedido ID %d", idPedido);
=======

>>>>>>> a47f27e82323fbf2d3a412623eb607e53f435613
        Pedido pedido = pedidoRepository.findById(idPedido);
        if (pedido == null)
            throw new NotFoundException("Pedido não encontrado");

        Boleto boleto = new Boleto();
        boleto.setPedido(pedido);
        boleto.setValor(pedido.getValorTotal());
        boleto.setStatus("PENDENTE");
<<<<<<< HEAD
=======

>>>>>>> a47f27e82323fbf2d3a412623eb607e53f435613
        boleto.setCodigoBarras(dto.codigoBarras());
        boleto.setDataVencimento(dto.dataVencimento());

        pagamentoRepository.persist(boleto);
<<<<<<< HEAD
        LOG.infof("Boleto criado com ID %d para pedido ID %d", boleto.getId(), idPedido);
=======

>>>>>>> a47f27e82323fbf2d3a412623eb607e53f435613
        return PagamentoDTOResponse.valueOf(boleto);
    }

    @Override
    @Transactional
    public PagamentoDTOResponse createCartao(Long idPedido, CartaoDTO dto) {
<<<<<<< HEAD
        LOG.infof("Criando Cartão para pedido ID %d", idPedido);
=======

>>>>>>> a47f27e82323fbf2d3a412623eb607e53f435613
        Pedido pedido = pedidoRepository.findById(idPedido);
        if (pedido == null)
            throw new NotFoundException("Pedido não encontrado");

        Cartao cartao = new Cartao();
        cartao.setPedido(pedido);
        cartao.setValor(pedido.getValorTotal());
        cartao.setStatus("PENDENTE");
<<<<<<< HEAD
=======

>>>>>>> a47f27e82323fbf2d3a412623eb607e53f435613
        cartao.setNomeTitular(dto.nomeTitular());
        cartao.setNumero(dto.numero());
        cartao.setValidade(dto.validade());
        cartao.setCvv(dto.cvv());

        pagamentoRepository.persist(cartao);
<<<<<<< HEAD
        LOG.infof("Cartão criado com ID %d para pedido ID %d", cartao.getId(), idPedido);
        return PagamentoDTOResponse.valueOf(cartao);
    }

    @Override
    @Transactional
    public void updatePix(Long id, PixDTO dto) {
        LOG.infof("Atualizando Pix com ID %d", id);
        Pagamento pagamento = pagamentoRepository.findById(id);

        if (!(pagamento instanceof Pix pix)) {
            LOG.warnf("Pix com ID %d não encontrado", id);
            throw new NotFoundException("Pix não encontrado");
        }

        if (dto.chave() != null) pix.setChave(dto.chave());
        if (dto.tipoChave() != null) pix.setTipoChave(dto.tipoChave());

        LOG.infof("Pix com ID %d atualizado", id);
=======

        return PagamentoDTOResponse.valueOf(cartao);
    }


    @Override
    @Transactional
    public void updatePix(Long id, PixDTO dto) {

        Pagamento pagamento = pagamentoRepository.findById(id);

        if (!(pagamento instanceof Pix pix)) {
        throw new NotFoundException("Pix não encontrado");
        }

        if (dto.chave() != null)
            pix.setChave(dto.chave());

        if (dto.tipoChave() != null)
            pix.setTipoChave(dto.tipoChave());
>>>>>>> a47f27e82323fbf2d3a412623eb607e53f435613
    }

    @Override
    @Transactional
    public void updateBoleto(Long id, BoletoDTO dto) {
<<<<<<< HEAD
        LOG.infof("Atualizando Boleto com ID %d", id);
        Pagamento pagamento = pagamentoRepository.findById(id);

        if (!(pagamento instanceof Boleto boleto)) {
            LOG.warnf("Boleto com ID %d não encontrado", id);
            throw new NotFoundException("Boleto não encontrado");
        }

        if (dto.codigoBarras() != null) boleto.setCodigoBarras(dto.codigoBarras());
        if (dto.dataVencimento() != null) boleto.setDataVencimento(dto.dataVencimento());

        LOG.infof("Boleto com ID %d atualizado", id);
=======

        Pagamento pagamento = pagamentoRepository.  findById(id);

        if (!(pagamento instanceof Boleto   boleto)) {
            throw new NotFoundException ("Boleto não encontrado");
        }

        if (dto.codigoBarras() != null)
            boleto.setCodigoBarras(dto. codigoBarras());

        if (dto.dataVencimento() != null)
            boleto.setDataVencimento(dto.dataVencimento());
>>>>>>> a47f27e82323fbf2d3a412623eb607e53f435613
    }

    @Override
    @Transactional
    public void updateCartao(Long id, CartaoDTO dto) {
<<<<<<< HEAD
        LOG.infof("Atualizando Cartão com ID %d", id);
        Pagamento pagamento = pagamentoRepository.findById(id);

        if (!(pagamento instanceof Cartao cartao)) {
            LOG.warnf("Cartão com ID %d não encontrado", id);
            throw new NotFoundException("Cartão não encontrado");
        }

        if (dto.nomeTitular() != null) cartao.setNomeTitular(dto.nomeTitular());
        if (dto.numero() != null) cartao.setNumero(dto.numero());
        if (dto.validade() != null) cartao.setValidade(dto.validade());
        if (dto.cvv() != null) cartao.setCvv(dto.cvv());

        LOG.infof("Cartão com ID %d atualizado", id);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        LOG.infof("Deletando pagamento com ID %d", id);
        pagamentoRepository.deleteById(id);
        LOG.infof("Pagamento com ID %d deletado", id);
=======

        Pagamento pagamento = pagamentoRepository.  findById(id);

        if (!(pagamento instanceof Cartao   cartao)) {
            throw new NotFoundException ("Cartão não encontrado");
        }

        if (dto.nomeTitular() != null)
            cartao.setNomeTitular(dto.nomeTitular   ());

        if (dto.numero() != null)
            cartao.setNumero(dto.numero());

        if (dto.validade() != null)
            cartao.setValidade(dto.validade());

        if (dto.cvv() != null)
            cartao.setCvv(dto.cvv());
    }


    @Override
    @Transactional
    public void delete(Long id) {
        pagamentoRepository.deleteById(id);
>>>>>>> a47f27e82323fbf2d3a412623eb607e53f435613
    }
}
