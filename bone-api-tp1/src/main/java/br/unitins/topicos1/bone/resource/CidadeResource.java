package br.unitins.topicos1.bone.resource;

import br.unitins.topicos1.bone.dto.CidadeDTO;
import br.unitins.topicos1.bone.dto.CidadeDTOResponse;
import br.unitins.topicos1.bone.service.CidadeService;
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

@Path("/cidades")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CidadeResource {

    private static final Logger LOG = Logger.getLogger(CidadeResource.class);
    
    @Inject
    CidadeService service;

    @GET
    @RolesAllowed({"ADM", "USER"})
    public Response buscarTodos() {
        LOG.info("Requisição para buscar todas as cidades recebida");
        try {
            var cidades = service.findAll();
            LOG.infof("Retornando %d cidades", cidades.size());
            return Response.ok(cidades).build();
        } catch (Exception e) {
            LOG.error("Erro ao buscar todas as cidades", e);
            return Response.status(Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Path("/find/{nome}")
    @RolesAllowed({"ADM", "USER"})
    public Response buscarPorNome(@PathParam("nome") String nome) {
        LOG.infof("Requisição para buscar cidades pelo nome: %s", nome);
        try {
            var cidades = service.findByNome(nome);
            LOG.infof("Encontradas %d cidades com o nome '%s'", cidades.size(), nome);
            return Response.ok(cidades).build();
        } catch (Exception e) {
            LOG.errorf(e, "Erro ao buscar cidades pelo nome: %s", nome);
            return Response.status(Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Path("/{id}")
    @RolesAllowed({"ADM", "USER"})
    public Response buscarPorId(@PathParam("id") Long id) {
        LOG.infof("Requisição para buscar cidade pelo ID: %d", id);
        try {
            CidadeDTOResponse response = service.findById(id);
            if (response == null) {
                LOG.warnf("Cidade com ID %d não encontrada", id);
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            LOG.infof("Cidade com ID %d encontrada: %s", id, response.nome());
            return Response.ok(response).build();
        } catch (Exception e) {
            LOG.errorf(e, "Erro ao buscar cidade pelo ID: %d", id);
            return Response.status(Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @POST
    @RolesAllowed("ADM")
    public Response incluirCidade(CidadeDTO dto) {
        LOG.infof("Requisição para criar cidade: %s", dto.nome());
        try {
            CidadeDTOResponse response = service.create(dto);
            LOG.infof("Cidade '%s' criada com sucesso", dto.nome());
            return Response.status(Status.CREATED).entity(response).build();
        } catch (Exception e) {
            LOG.errorf(e, "Erro ao criar cidade: %s", dto.nome());
            return Response.status(Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PUT
    @Path("/{id}")
    @RolesAllowed("ADM")
    public Response alterarCidade(Long id, CidadeDTO dto) {
        LOG.infof("Requisição para atualizar cidade ID: %d", id);
        try {
            service.update(id, dto);
            LOG.infof("Cidade ID %d atualizada com sucesso", id);
            return Response.noContent().build();
        } catch (Exception e) {
            LOG.errorf(e, "Erro ao atualizar cidade ID: %d", id);
            return Response.status(Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DELETE
    @Path("/{id}")
    @RolesAllowed("ADM")
    public Response deletarCidade(Long id) {
        LOG.infof("Requisição para deletar cidade ID: %d", id);
        try {
            service.delete(id);
            LOG.infof("Cidade ID %d deletada com sucesso", id);
            return Response.noContent().build();
        } catch (Exception e) {
            LOG.errorf(e, "Erro ao deletar cidade ID: %d", id);
            return Response.status(Status.INTERNAL_SERVER_ERROR).build();
        }
    }

}
