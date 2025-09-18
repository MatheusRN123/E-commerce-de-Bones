package br.unitins.topicos1.bone.resource;

import java.util.List;

import br.unitins.topicos1.bone.dto.BoneDTO;
import br.unitins.topicos1.bone.model.Bone;
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

@Path("/bones")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BoneResource {
    
    @Inject
    BoneService service;

    @GET
    public List<Bone> buscarTodos(){

        return service.findAll();

    }

    @GET
    @Path("/find/{nome}")
    public List<Bone> buscarPorNome(String nome){
        
        return service.findByNome(nome);

    }

    @POST
    public Bone incluirBone(BoneDTO dto){

        return service.create(dto);

    }

    @PUT
    @Path("/{id}")
    public void alterarBone(Long id, BoneDTO dto){

        service.update(id, dto);

    }

    @DELETE
    @Path("/{id}")
    public void deletarBone(Long id){

        service.delete(id);

    }

}
