package br.unitins.topicos1.bone.resource;

import java.util.List;

import br.unitins.topicos1.bone.dto.BoletoDTO;
import br.unitins.topicos1.bone.dto.CartaoDTO;
import br.unitins.topicos1.bone.dto.PagamentoDTOResponse;
import br.unitins.topicos1.bone.dto.PixDTO;
import br.unitins.topicos1.bone.service.PagamentoService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import org.jboss.logging.Logger;

@Path("/pagamentos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PagamentoResource {

    private static final Logger LOG = Logger.getLogger(PagamentoResource.class);

    @Inject
    PagamentoService service;

    @GET
    @RolesAllowed({"ADM", "USER"})
    public Response buscarTodos() {
        LOG.info("Endpoint GET /pagamentos chamado");
        List<PagamentoDTOResponse> pagamentos = service.findAll();
        return Response.ok(pagamentos).build();
    }

    @GET
    @Path("/{id}")
    @RolesAllowed({"ADM", "USER"})
    public Response buscarPorId(@PathParam("id") Long id) {
        LOG.infof("Endpoint GET /pagamentos/%d chamado", id);
        PagamentoDTOResponse pagamento = service.findById(id);
        if (pagamento == null) {
            LOG.warnf("Pagamento com ID %d não encontrado", id);
            return Response.status(Status.NOT_FOUND).build();
        }
        return Response.ok(pagamento).build();
    }

    @POST
    @Path("/pix/{idPedido}")
    @RolesAllowed("ADM")
    public Response incluirPix(@PathParam("idPedido") Long idPedido, PixDTO dto) {
        LOG.infof("Endpoint POST /pagamentos/pix/%d chamado", idPedido);
        PagamentoDTOResponse pagamento = service.createPix(idPedido, dto);
        LOG.infof("Pix criado para pedido ID %d com pagamento ID %d", idPedido, pagamento.id());
        return Response.status(Status.CREATED).entity(pagamento).build();
    }

    @POST
    @Path("/boleto/{idPedido}")
    @RolesAllowed("ADM")
    public Response incluirBoleto(@PathParam("idPedido") Long idPedido, BoletoDTO dto) {
        LOG.infof("Endpoint POST /pagamentos/boleto/%d chamado", idPedido);
        PagamentoDTOResponse pagamento = service.createBoleto(idPedido, dto);
        LOG.infof("Boleto criado para pedido ID %d com pagamento ID %d", idPedido, pagamento.id());
        return Response.status(Status.CREATED).entity(pagamento).build();
    }

    @POST
    @Path("/cartao/{idPedido}")
    @RolesAllowed("ADM")
    public Response incluirCartao(@PathParam("idPedido") Long idPedido, CartaoDTO dto) {
        LOG.infof("Endpoint POST /pagamentos/cartao/%d chamado", idPedido);
        PagamentoDTOResponse pagamento = service.createCartao(idPedido, dto);
        LOG.infof("Cartão criado para pedido ID %d com pagamento ID %d", idPedido, pagamento.id());
        return Response.status(Status.CREATED).entity(pagamento).build();
    }

    @PUT
    @Path("/pix/{id}")
    @RolesAllowed("ADM")
    public Response alterarPix(@PathParam("id") Long id, PixDTO dto) {
        LOG.infof("Endpoint PUT /pagamentos/pix/%d chamado", id);
        service.updatePix(id, dto);
        LOG.infof("Pix com ID %d atualizado", id);
        return Response.noContent().build();
    }

    @PUT
    @Path("/boleto/{id}")
    @RolesAllowed("ADM")
    public Response alterarBoleto(@PathParam("id") Long id, BoletoDTO dto) {
        LOG.infof("Endpoint PUT /pagamentos/boleto/%d chamado", id);
        service.updateBoleto(id, dto);
        LOG.infof("Boleto com ID %d atualizado", id);
        return Response.noContent().build();
    }

    @PUT
    @Path("/cartao/{id}")
    @RolesAllowed("ADM")
    public Response alterarCartao(@PathParam("id") Long id, CartaoDTO dto) {
        LOG.infof("Endpoint PUT /pagamentos/cartao/%d chamado", id);
        service.updateCartao(id, dto);
        LOG.infof("Cartão com ID %d atualizado", id);
        return Response.noContent().build();
    }

    @DELETE
    @Path("/{id}")
    @RolesAllowed("ADM")
    public Response deletarPagamento(@PathParam("id") Long id) {
        LOG.infof("Endpoint DELETE /pagamentos/%d chamado", id);
        service.delete(id);
        LOG.infof("Pagamento com ID %d deletado", id);
        return Response.noContent().build();
    }
}
