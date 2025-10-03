package br.unitins.topicos1.bone.resource;

import java.util.List;

import br.unitins.topicos1.bone.dto.EstoqueDTO;
import br.unitins.topicos1.bone.dto.EstoqueDTOResponse;
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

@Path("/estoques")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EstoqueResource {

    @Inject
    EstoqueService service;

    @GET
    public List<EstoqueDTOResponse> buscarTodos() {
        return service.findAll();
    }

    @GET
    @Path("/find/{quantidade}")
    public List<EstoqueDTOResponse> buscarPorQuantidade(Integer quantidade) {
        return service.findByQuantidade(quantidade);
    }

    @GET
    @Path("/bone/{idBone}")
    public EstoqueDTOResponse buscarPorBoneId(Long id){
        return service.findByBoneId(id);
    }

    @POST
    public EstoqueDTOResponse incluirEstoque(EstoqueDTO dto) {
        return service.create(dto);
    }

    @PUT
    @Path("/{id}")
    public void alterarEstoque(Long id, EstoqueDTO dto) {
        service.update(id, dto);
    }

    @DELETE
    @Path("/{id}")
    public void apagarEstoque(Long id) {
        service.delete(id);
    }

    @GET
    @Path("/{id}/disponibilidade")
    public Boolean verificarDisponibilidade(Long id) {
        return service.verificarDisponibilidade(id);
    }

    @PUT
    @Path("/{id}/quantidade")
    public void atualizarQuantidade(Long id, EstoqueDTO dto) {
        service.atualizarQuantidade(id, dto);
    }

    @PUT
    @Path("/{id}/adicionar/adicionar")
    public void adicionarQuantidade(Long id, EstoqueDTO dto) {
        service.adicionarQuantidade(id, dto);
    }

}