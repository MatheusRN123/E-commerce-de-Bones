package br.unitins.topicos1.bone.resource;

import java.util.List;

import br.unitins.topicos1.bone.dto.MaterialDTO;
import br.unitins.topicos1.bone.dto.MaterialDTOResponse;
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
    public List<MaterialDTOResponse> buscarTodos() {
        return service.findAll();
    }

    @GET
    @Path("/find/{nome}")
    public List<MaterialDTOResponse> buscarPorNome(String nome) {
        return service.findByNome(nome);
    }

    @POST
    public MaterialDTOResponse incluirMaterial(MaterialDTO dto) {
        return service.create(dto);
    }

    @PUT
    @Path("/{id}")
    public void alterarMaterial(Long id, MaterialDTO dto) {
        service.update(id, dto);
    }

    @DELETE
    @Path("/{id}")
    public void apagarMaterial(Long id) {
        service.delete(id);
    }
}