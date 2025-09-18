package br.unitins.topicos1.bone.resource;

import java.util.List;

import br.unitins.topicos1.bone.dto.MaterialDTO;
import br.unitins.topicos1.bone.model.Material;
import br.unitins.topicos1.bone.service.MaterialService;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/materiais")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MaterialResource {

    @Inject
    MaterialService service;

    @GET
    public List<Material> buscarTodos() {
        return service.findAll();
    }

    @GET
    @Path("/find/{nome}")
    public List<Material> buscarPorNome(String nome) {
        return service.findByNome(nome);
    }

    @POST
    public Material incluir(MaterialDTO dto) {
        return service.create(dto);
    }

    @PUT
    @Path("/{id}")
    public void alterar(Long id, MaterialDTO dto) {
        service.update(id, dto);
    }

    @DELETE
    @Path("/{id}")
    public void apagar(Long id) {
        service.delete(id);
    }
}