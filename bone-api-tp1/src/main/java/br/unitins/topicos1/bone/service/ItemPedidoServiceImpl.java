package br.unitins.topicos1.bone.service;

import java.util.List;

import br.unitins.topicos1.bone.dto.ItemPedidoDTO;
import br.unitins.topicos1.bone.dto.ItemPedidoDTOResponse;
import br.unitins.topicos1.bone.model.Bone;
import br.unitins.topicos1.bone.model.ItemPedido;
import br.unitins.topicos1.bone.repository.BoneRepository;
import br.unitins.topicos1.bone.repository.ItemPedidoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.jboss.logging.Logger;

@ApplicationScoped
public class ItemPedidoServiceImpl implements ItemPedidoService {

    private static final Logger LOG = Logger.getLogger(ItemPedidoServiceImpl.class);
    
    @Inject
    ItemPedidoRepository repository;

    @Inject
    BoneRepository boneRepository;

    @Override
    public List<ItemPedidoDTOResponse> findAll() {
        LOG.info("Requisição para buscar todos os itens de pedido");
        var response = repository.listAll()
                .stream()
                .map(ItemPedidoDTOResponse::valueOf)
                .toList();
        LOG.infof("Retornando %d itens de pedido", response.size());
        return response;
    }

    @Override
    public ItemPedidoDTOResponse findById(Long id) {
        LOG.infof("Requisição para buscar item de pedido ID: %d", id);
        var item = repository.findById(id);
        if (item == null) {
            LOG.warnf("Item de pedido ID %d não encontrado", id);
        } else {
            LOG.infof("Item de pedido ID %d encontrado", id);
        }
        return ItemPedidoDTOResponse.valueOf(item);
    }

    @Override
    @Transactional
    public void update(Long id, ItemPedidoDTO dto) {
        LOG.infof("Requisição para atualizar item de pedido ID: %d", id);
        ItemPedido item = repository.findById(id);

        Bone bone = boneRepository.findById(dto.idBone());
        item.setBone(bone);

        item.setQuantidade(dto.quantidade());
        item.setPreco(dto.preco());

        LOG.infof("Item de pedido ID %d atualizado com sucesso", id);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        LOG.infof("Requisição para deletar item de pedido ID: %d", id);
        repository.deleteById(id);
        LOG.infof("Item de pedido ID %d deletado com sucesso", id);
    }
}
