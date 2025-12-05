package br.unitins.topicos1.bone.resource;

import br.unitins.topicos1.bone.dto.EstoqueDTO;
import br.unitins.topicos1.bone.service.EstoqueService;
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

@Path("/estoques")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EstoqueResource {

    private static final Logger LOG = Logger.getLogger(EstoqueResource.class);

    @Inject
    EstoqueService service;

    @GET
    @RolesAllowed({"ADM", "USER"})
    public Response buscarTodos() {
        LOG.info("Requisição para buscar todos os estoques");
        try {
            var response = service.findAll();
            LOG.infof("Retornando %d estoques", response.size());
            return Response.ok(response).build();
        } catch (Exception e) {
            LOG.error("Erro ao buscar todos os estoques", e);
            return Response.status(Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Path("/{id}")
    @RolesAllowed({"ADM", "USER"})
    public Response buscarPorId(Long id) {
        LOG.infof("Requisição para buscar estoque pelo ID: %d", id);
        try {
            var response = service.findById(id);
            LOG.infof("Estoque ID %d encontrado", id);
            return Response.ok(response).build();
        } catch (Exception e) {
            LOG.errorf(e, "Erro ao buscar estoque ID: %d", id);
            return Response.status(Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Path("/find/{quantidade}")
    @RolesAllowed("ADM")
    public Response buscarPorQuantidade(Integer quantidade) {
        LOG.infof("Requisição para buscar estoques com quantidade: %d", quantidade);
        try {
            var response = service.findByQuantidade(quantidade);
            LOG.infof("Encontrados %d estoques com quantidade %d", response.size(), quantidade);
            return Response.ok(response).build();
        } catch (Exception e) {
            LOG.errorf(e, "Erro ao buscar estoques com quantidade: %d", quantidade);
            return Response.status(Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @POST
    @RolesAllowed("ADM")
    public Response incluirEstoque(EstoqueDTO dto) {
        LOG.infof("Requisição para criar estoque com quantidade: %d", dto.quantidade());
        try {
            var response = service.create(dto);
            LOG.info("Estoque criado com sucesso");
            return Response.status(Status.CREATED).entity(response).build();
        } catch (Exception e) {
            LOG.error("Erro ao criar estoque", e);
            return Response.status(Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PUT
    @Path("/{id}")
    @RolesAllowed("ADM")
    public Response alterarEstoque(Long id, EstoqueDTO dto) {
        LOG.infof("Requisição para atualizar estoque ID: %d", id);
        try {
            service.update(id, dto);
            LOG.infof("Estoque ID %d atualizado com sucesso", id);
            return Response.noContent().build();
        } catch (Exception e) {
            LOG.errorf(e, "Erro ao atualizar estoque ID: %d", id);
            return Response.status(Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DELETE
    @Path("/{id}")
    @RolesAllowed("ADM")
    public Response apagarEstoque(Long id) {
        LOG.infof("Requisição para deletar estoque ID: %d", id);
        try {
            service.delete(id);
            LOG.infof("Estoque ID %d deletado com sucesso", id);
            return Response.noContent().build();
        } catch (Exception e) {
            LOG.errorf(e, "Erro ao deletar estoque ID: %d", id);
            return Response.status(Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Path("/{id}/disponibilidade")
    @RolesAllowed("ADM")
    public Response verificarDisponibilidade(Long id) {
        LOG.infof("Requisição para verificar disponibilidade do estoque ID: %d", id);
        try {
            var disponivel = service.verificarDisponibilidade(id);
            LOG.infof("Estoque ID %d disponível: %b", id, disponivel);
            return Response.ok(disponivel).build();
        } catch (Exception e) {
            LOG.errorf(e, "Erro ao verificar disponibilidade do estoque ID: %d", id);
            return Response.status(Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PUT
    @Path("/{id}/quantidade")
    @RolesAllowed("ADM")
    public Response atualizarQuantidade(Long id, EstoqueDTO dto) {
        LOG.infof("Requisição para atualizar quantidade do estoque ID: %d", id);
        try {
            service.atualizarQuantidade(id, dto);
            LOG.infof("Quantidade do estoque ID %d atualizada", id);
            return Response.noContent().build();
        } catch (Exception e) {
            LOG.errorf(e, "Erro ao atualizar quantidade do estoque ID: %d", id);
            return Response.status(Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PUT
    @Path("/{id}/adicionar/adicionar")
    @RolesAllowed("ADM")
    public Response adicionarQuantidade(Long id, EstoqueDTO dto) {
        LOG.infof("Requisição para adicionar quantidade ao estoque ID: %d", id);
        try {
            service.adicionarQuantidade(id, dto);
            LOG.infof("Quantidade adicionada ao estoque ID %d com sucesso", id);
            return Response.noContent().build();
        } catch (Exception e) {
            LOG.errorf(e, "Erro ao adicionar quantidade ao estoque ID: %d", id);
            return Response.status(Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}
