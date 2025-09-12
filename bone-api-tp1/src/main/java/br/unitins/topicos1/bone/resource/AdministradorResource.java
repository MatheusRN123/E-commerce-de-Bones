package br.unitins.topicos1.bone.resource;

import java.util.List;

import br.unitins.topicos1.bone.model.Administrador;
import br.unitins.topicos1.bone.repository.AdministradorRepository;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/administradores")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AdministradorResource {
    
    @Inject
    AdministradorRepository repository;

    @GET
    public List<Administrador> buscarTodos(){

        return repository.listAll();

    }

    @GET
    @Path("/find/{nome}")
    public List<Administrador> buscarPorNome(String nome){
        
        return repository.findByNome(nome);

    }

    @POST
    @Transactional
    public Administrador incluirAdministrador(Administrador administrador){

        Administrador novoAdmin = new Administrador();
        novoAdmin.setCpf(administrador.getCpf());
        novoAdmin.setNome(administrador.getNome());
        novoAdmin.setEndereco(administrador.getEndereco());
        novoAdmin.setTelefone(administrador.getTelefone());
        novoAdmin.setEmail(administrador.getEmail());
        novoAdmin.setSenha(administrador.getSenha());
        novoAdmin.setNivelAcesso(administrador.getNivelAcesso());

        repository.persist(novoAdmin);

        return novoAdmin;

    }

    @PUT
    @Path("/{id}")
    @Transactional
    public void alterarAluno(Long id, Administrador administrador){

        Administrador edicaoAdministrador = repository.findById(id);

        edicaoAdministrador.setCpf(administrador.getCpf());
        edicaoAdministrador.setNome(administrador.getNome());
        edicaoAdministrador.setEndereco(administrador.getEndereco());
        edicaoAdministrador.setTelefone(administrador.getTelefone());
        edicaoAdministrador.setEmail(administrador.getEmail());
        edicaoAdministrador.setSenha(administrador.getSenha());
        edicaoAdministrador.setNivelAcesso(administrador.getNivelAcesso());

    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public void deletarAdministrador(Long id){

        repository.deleteById(id);

    }

}
