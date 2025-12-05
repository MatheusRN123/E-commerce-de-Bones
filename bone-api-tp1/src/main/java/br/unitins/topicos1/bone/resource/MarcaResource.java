package br.unitins.topicos1.bone.resource;

import br.unitins.topicos1.bone.dto.MarcaDTO;
import br.unitins.topicos1.bone.service.MarcaService;
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

@Path("/marcas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MarcaResource {

    private static final Logger LOG = Logger.getLogger(MarcaResource.class);

    @Inject
    MarcaService service;

    @GET
    @RolesAllowed({"ADM", "USER"})
    public Response buscarTodos() {
        LOG.info("Requisição para buscar todas as marcas");
        var marcas = service.findAll();
        LOG.infof("Retornando %d marcas", marcas.size());
        return Response.ok(marcas).build();
    }

    @GET
    @Path("/find/{nome}")
    @RolesAllowed({"ADM", "USER"})
    public Response buscarPorNome(String nome) {
        LOG.infof("Requisição para buscar marcas pelo nome: %s", nome);
        var marcas = service.findByNome(nome);
        LOG.infof("Encontradas %d marcas com o nome '%s'", marcas.size(), nome);
        return Response.ok(marcas).build();
    }

    @GET
    @Path("/{id}")
    @RolesAllowed({"ADM", "USER"})
    public Response buscarPorId(Long id) {
        LOG.infof("Requisição para buscar marca pelo ID: %d", id);
        var marca = service.findById(id);
        LOG.infof("Marca encontrada: %s", marca.nome());
        return Response.ok(marca).build();
    }

    @POST
    @RolesAllowed("ADM")
    public Response incluirMarca(MarcaDTO dto) {
        LOG.infof("Requisição para criar marca: %s", dto.nome());
        var marca = service.create(dto);
        LOG.infof("Marca '%s' criada com sucesso", dto.nome());
        return Response.status(Status.CREATED).entity(marca).build();
    }

    @PUT
    @Path("/{id}")
    @RolesAllowed("ADM")
    public Response alterarMarca(Long id, MarcaDTO dto) {
        LOG.infof("Requisição para atualizar marca ID: %d", id);
        service.update(id, dto);
        LOG.infof("Marca ID %d atualizada com sucesso", id);
        return Response.noContent().build();
    }

    @DELETE
    @Path("/{id}")
    @RolesAllowed("ADM")
    public Response apagarMarca(Long id) {
        LOG.infof("Requisição para deletar marca ID: %d", id);
        service.delete(id);
        LOG.infof("Marca ID %d deletada com sucesso", id);
        return Response.noContent().build();
    }
}
