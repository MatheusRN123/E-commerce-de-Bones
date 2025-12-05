package br.unitins.topicos1.bone.dto;

import java.util.List;

public record PedidoDTO(
    Long idUsuario,
    Long idEndereco,
<<<<<<< HEAD
    List<ItemPedidoDTO> itens,
    PagamentoDTO pagamento
=======
    List<ItemPedidoDTO> itens
>>>>>>> a47f27e82323fbf2d3a412623eb607e53f435613
) {}
