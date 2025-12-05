package br.unitins.topicos1.bone.resource;

import br.unitins.topicos1.bone.service.EstampaService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import org.jboss.logging.Logger;

@Path("/estampas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EstampaResource {

    private static final Logger LOG = Logger.getLogger(EstampaResource.class);

    @Inject
    EstampaService service;

    @GET
    @RolesAllowed({"ADM", "USER"})
    public Response buscarTodos() {
        LOG.info("Requisição para buscar todas as estampas");
        try {
            var response = service.findAll();
            LOG.infof("Retornando %d estampas", response.size());
            return Response.ok(response).build();
        } catch (Exception e) {
            LOG.error("Erro ao buscar todas as estampas", e);
            return Response.status(Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Path("/find/{nome}")
    @RolesAllowed({"ADM", "USER"})
    public Response buscarPorNome(String nome) {
        LOG.infof("Requisição para buscar estampas pelo nome: %s", nome);
        try {
            var response = service.findByNome(nome);
            LOG.infof("Encontradas %d estampas com o nome '%s'", response.size(), nome);
            return Response.ok(response).build();
        } catch (Exception e) {
            LOG.errorf(e, "Erro ao buscar estampas pelo nome: %s", nome);
            return Response.status(Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Path("/{id}")
    @RolesAllowed({"ADM", "USER"})
    public Response buscarPorId(Long id) {
        LOG.infof("Requisição para buscar estampa pelo ID: %d", id);
        try {
            var response = service.findById(id);
            LOG.infof("Estampa ID %d encontrada", id);
            return Response.ok(response).build();
        } catch (Exception e) {
            LOG.errorf(e, "Erro ao buscar estampa pelo ID: %d", id);
            return Response.status(Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}
