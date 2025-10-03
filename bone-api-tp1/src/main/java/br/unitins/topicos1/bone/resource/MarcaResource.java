package br.unitins.topicos1.bone.resource;

import java.util.List;

import br.unitins.topicos1.bone.dto.MarcaDTO;
import br.unitins.topicos1.bone.dto.MarcaDTOResponse;
import br.unitins.topicos1.bone.service.MarcaService;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/marcas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MarcaResource {

    @Inject
    MarcaService service;

    @GET
    public List<MarcaDTOResponse> buscarTodos() {
        return service.findAll();
    }

    @GET
    @Path("/find/{nome}")
    public List<MarcaDTOResponse> buscarPorNome(String nome) {
        return service.findByNome(nome);
    }

    @POST
    public MarcaDTOResponse incluirMarca(MarcaDTO dto) {
        return service.create(dto);
    }

    @PUT
    @Path("/{id}")
    public void alterarMarca(Long id, MarcaDTO dto) {
        service.update(id, dto);
    }

    @DELETE
    @Path("/{id}")
    public void apagarMarca(Long id) {
        service.delete(id);
    }
}