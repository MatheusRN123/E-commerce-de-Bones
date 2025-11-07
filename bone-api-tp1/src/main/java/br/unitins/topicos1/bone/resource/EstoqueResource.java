package br.unitins.topicos1.bone.resource;

import br.unitins.topicos1.bone.dto.EstoqueDTO;
import br.unitins.topicos1.bone.service.EstoqueService;
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

@Path("/estoques")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EstoqueResource {

    @Inject
    EstoqueService service;

    @GET
    public Response buscarTodos() {
        return Response.ok(service.findAll()).build();
    }

    @GET
    @Path("/{id}")
    public Response buscarPorId(Long id) {
        return Response.ok(service.findById(id)).build();
    }

    @GET
    @Path("/find/{quantidade}")
    public Response buscarPorQuantidade(Integer quantidade) {
        return Response.ok(service.findByQuantidade(quantidade)).build();
    }

    @POST
    public Response incluirEstoque(EstoqueDTO dto) {
        return Response.status(Status.CREATED).entity(service.create(dto)).build();
    }

    @PUT
    @Path("/{id}")
    public Response alterarEstoque(Long id, EstoqueDTO dto) {
        service.update(id, dto);
        return Response.noContent().build();
    }

    @DELETE
    @Path("/{id}")
    public Response apagarEstoque(Long id) {
        service.delete(id);
        return Response.noContent().build();
    }

    @GET
    @Path("/{id}/disponibilidade")
    public Response verificarDisponibilidade(Long id) {
        return Response.ok(service.verificarDisponibilidade(id)).build();
    }

    @PUT
    @Path("/{id}/quantidade")
    public Response atualizarQuantidade(Long id, EstoqueDTO dto) {
        service.atualizarQuantidade(id, dto);
        return Response.noContent().build();
    }

    @PUT
    @Path("/{id}/adicionar/adicionar")
    public Response adicionarQuantidade(Long id, EstoqueDTO dto) {
        service.adicionarQuantidade(id, dto);
        return Response.noContent().build();
    }

}