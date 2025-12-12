package br.unitins.topicos1.bone.resource;

import java.util.List;

import br.unitins.topicos1.bone.dto.PagamentoDTOResponse;
import br.unitins.topicos1.bone.service.PagamentoService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
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

    @PUT
    @Path("/{id}/status")
    @RolesAllowed("ADM")
    public Response atualizarStatus(@PathParam("id") Long id, @QueryParam("status") String status) {
        LOG.infof("Endpoint PUT /pagamentos/%d/status chamado com status=%s", id, status);

        try {
            service.updateStatus(id, status);
            LOG.infof("Pagamento com ID %d atualizado para status %s", id, status);
            return Response.noContent().build();
        } catch (NotFoundException e) {
            LOG.warnf("Pagamento com ID %d não encontrado", id);
            return Response.status(Status.NOT_FOUND).entity(e.getMessage()).build();
        } catch (IllegalArgumentException e) {
            LOG.warnf("Status inválido fornecido: %s", status);
            return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
        } catch (Exception e) {
            LOG.error("Erro ao atualizar status do pagamento", e);
            return Response.status(Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}
