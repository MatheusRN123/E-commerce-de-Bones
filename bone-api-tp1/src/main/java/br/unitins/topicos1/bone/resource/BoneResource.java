package br.unitins.topicos1.bone.resource;

import br.unitins.topicos1.bone.dto.BoneDTO;
import br.unitins.topicos1.bone.dto.BoneDTOResponse;
import br.unitins.topicos1.bone.service.BoneService;
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

@Path("/bones")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BoneResource {
    
    @Inject
    BoneService service;

    @GET
    public Response buscarTodos(){

        return Response.ok(service.findAll()).build();

    }

    @GET
    @Path("/find/{nome}")
    public Response buscarPorNome(String nome){
        
        return Response.ok(service.findByNome(nome)).build();

    }

    @GET
    @Path("/{id}")
    public Response buscarPorId(Long id) {
        BoneDTOResponse response = service.findById(id);
        if(response == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(response).build();
    }

    @POST
    public Response incluirBone(BoneDTO dto){

        return Response.status(Status.CREATED).entity(service.create(dto)).build();

    }

    @PUT
    @Path("/{id}")
    public Response alterarBone(Long id, BoneDTO dto){

        service.update(id, dto);
        return Response.noContent().build();

    }

    @DELETE
    @Path("/{id}")
    public Response deletarBone(Long id){

        service.delete(id);
        return Response.noContent().build();

    }

}
