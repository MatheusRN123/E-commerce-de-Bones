package br.unitins.topicos1.bone.resource;

import br.unitins.topicos1.bone.dto.ItemPedidoDTO;
import br.unitins.topicos1.bone.dto.ItemPedidoDTOResponse;
import br.unitins.topicos1.bone.service.ItemPedidoService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import org.jboss.logging.Logger;

@Path("/itens")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ItemPedidoResource {

    private static final Logger LOG = Logger.getLogger(ItemPedidoResource.class);

    @Inject
    ItemPedidoService service;

    @GET
    @RolesAllowed({"ADM", "USER"})
    public Response buscarTodos() {
        LOG.info("Requisição para buscar todos os itens de pedido");
        var response = service.findAll();
        LOG.infof("Retornando %d itens de pedido", response.size());
        return Response.ok(response).build();
    }

    @GET
    @Path("/{id}")
    @RolesAllowed({"ADM", "USER"})
    public Response buscarPorId(Long id) {
        LOG.infof("Requisição para buscar item de pedido ID: %d", id);
        ItemPedidoDTOResponse response = service.findById(id);
        if (response == null) {
            LOG.warnf("Item de pedido ID %d não encontrado", id);
            return Response.status(Status.NOT_FOUND).build();
        }
        LOG.infof("Item de pedido ID %d encontrado", id);
        return Response.ok(response).build();
    }

    @PUT
    @Path("/{id}")
    @RolesAllowed("ADM")
    public Response alterarItemPedido(Long id, ItemPedidoDTO dto) {
        LOG.infof("Requisição para atualizar item de pedido ID: %d", id);
        service.update(id, dto);
        LOG.infof("Item de pedido ID %d atualizado com sucesso", id);
        return Response.noContent().build();
    }

    @DELETE
    @Path("/{id}")
    @RolesAllowed("ADM")
    public Response deletarItemPedido(Long id) {
        LOG.infof("Requisição para deletar item de pedido ID: %d", id);
        service.delete(id);
        LOG.infof("Item de pedido ID %d deletado com sucesso", id);
        return Response.noContent().build();
    }
}
