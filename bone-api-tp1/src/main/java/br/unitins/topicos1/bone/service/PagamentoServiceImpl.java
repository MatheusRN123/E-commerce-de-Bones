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

@ApplicationScoped
public class PagamentoServiceImpl implements PagamentoService {

    @Inject
    PagamentoRepository pagamentoRepository;

    @Inject
    PedidoRepository pedidoRepository;


    @Override
    public List<PagamentoDTOResponse> findAll() {
        return pagamentoRepository.listAll()
            .stream()
            .map(PagamentoDTOResponse::valueOf)
            .toList();
    }

    @Override
    public PagamentoDTOResponse findById(Long id) {
        Pagamento pagamento = pagamentoRepository.findById(id);
        if (pagamento == null)
            return null;
        return PagamentoDTOResponse.valueOf(pagamento);
    }


    @Override
    @Transactional
    public PagamentoDTOResponse createPix(Long idPedido, PixDTO dto) {

        Pedido pedido = pedidoRepository.findById(idPedido);
        if (pedido == null)
            throw new NotFoundException("Pedido não encontrado");

        Pix pix = new Pix();
        pix.setPedido(pedido);
        pix.setValor(pedido.getValorTotal());
        pix.setStatus("PENDENTE");

        pix.setChave(dto.chave());
        pix.setTipoChave(dto.tipoChave());

        pagamentoRepository.persist(pix);

        return PagamentoDTOResponse.valueOf(pix);
    }

    @Override
    @Transactional
    public PagamentoDTOResponse createBoleto(Long idPedido, BoletoDTO dto) {

        Pedido pedido = pedidoRepository.findById(idPedido);
        if (pedido == null)
            throw new NotFoundException("Pedido não encontrado");

        Boleto boleto = new Boleto();
        boleto.setPedido(pedido);
        boleto.setValor(pedido.getValorTotal());
        boleto.setStatus("PENDENTE");

        boleto.setCodigoBarras(dto.codigoBarras());
        boleto.setDataVencimento(dto.dataVencimento());

        pagamentoRepository.persist(boleto);

        return PagamentoDTOResponse.valueOf(boleto);
    }

    @Override
    @Transactional
    public PagamentoDTOResponse createCartao(Long idPedido, CartaoDTO dto) {

        Pedido pedido = pedidoRepository.findById(idPedido);
        if (pedido == null)
            throw new NotFoundException("Pedido não encontrado");

        Cartao cartao = new Cartao();
        cartao.setPedido(pedido);
        cartao.setValor(pedido.getValorTotal());
        cartao.setStatus("PENDENTE");

        cartao.setNomeTitular(dto.nomeTitular());
        cartao.setNumero(dto.numero());
        cartao.setValidade(dto.validade());
        cartao.setCvv(dto.cvv());

        pagamentoRepository.persist(cartao);

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
    }

    @Override
    @Transactional
    public void updateBoleto(Long id, BoletoDTO dto) {

        Pagamento pagamento = pagamentoRepository.  findById(id);

        if (!(pagamento instanceof Boleto   boleto)) {
            throw new NotFoundException ("Boleto não encontrado");
        }

        if (dto.codigoBarras() != null)
            boleto.setCodigoBarras(dto. codigoBarras());

        if (dto.dataVencimento() != null)
            boleto.setDataVencimento(dto.dataVencimento());
    }

    @Override
    @Transactional
    public void updateCartao(Long id, CartaoDTO dto) {

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
    }
}
