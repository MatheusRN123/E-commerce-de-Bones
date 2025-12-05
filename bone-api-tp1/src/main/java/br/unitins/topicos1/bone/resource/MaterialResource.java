package br.unitins.topicos1.bone.resource;

import br.unitins.topicos1.bone.dto.MaterialDTO;
import br.unitins.topicos1.bone.service.MaterialService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import org.jboss.logging.Logger;

@Path("/materiais")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MaterialResource {

    private static final Logger LOG = Logger.getLogger(MaterialResource.class);

    @Inject
    MaterialService service;

    @GET
    @RolesAllowed({"ADM", "USER"})
    public Response buscarTodos() {
        LOG.info("Requisição para buscar todos os materiais");
        Response response = Response.ok(service.findAll()).build();
        LOG.info("Resposta enviada para buscar todos os materiais");
        return response;
    }

    @GET
    @Path("/find/{nome}")
    @RolesAllowed({"ADM", "USER"})
    public Response buscarPorNome(String nome) {
        LOG.infof("Requisição para buscar materiais pelo nome: %s", nome);
        Response response = Response.ok(service.findByNome(nome)).build();
        LOG.infof("Resposta enviada para materiais com nome: %s", nome);
        return response;
    }

    @GET
    @Path("/{id}")
    @RolesAllowed({"ADM", "USER"})
    public Response buscarPorId(Long id) {
        LOG.infof("Requisição para buscar material pelo ID: %d", id);
        Response response = Response.ok(service.findById(id)).build();
        LOG.infof("Resposta enviada para material ID: %d", id);
        return response;
    }

    @POST
    @RolesAllowed("ADM")
    public Response incluirMaterial(MaterialDTO dto) {
        LOG.infof("Requisição para criar material: %s", dto.nome());
        Response response = Response.status(Status.CREATED).entity(service.create(dto)).build();
        LOG.infof("Material '%s' criado com sucesso", dto.nome());
        return response;
    }

    @PUT
    @Path("/{id}")
    @RolesAllowed("ADM")
    public Response alterarMaterial(Long id, MaterialDTO dto) {
        LOG.infof("Requisição para atualizar material ID: %d", id);
        service.update(id, dto);
        LOG.infof("Material ID %d atualizado com sucesso", id);
        return Response.noContent().build();
    }

    @DELETE
    @Path("/{id}")
    @RolesAllowed("ADM")
    public Response apagarMaterial(Long id) {
        LOG.infof("Requisição para deletar material ID: %d", id);
        service.delete(id);
        LOG.infof("Material ID %d deletado com sucesso", id);
        return Response.noContent().build();
    }
}
