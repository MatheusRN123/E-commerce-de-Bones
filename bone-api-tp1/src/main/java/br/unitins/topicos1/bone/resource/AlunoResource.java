package br.unitins.topicos1.bone.resource;

import java.util.List;

import br.unitins.topicos1.bone.model.Aluno;
import br.unitins.topicos1.bone.repository.AlunoRepository;
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

@Path("/alunos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AlunoResource {
    
    @Inject
    AlunoRepository repository;

    @GET
    public List<Aluno> buscarTodos(){

        return repository.listAll();

    }

    @GET
    @Path("/find/{nome}")
    public List<Aluno> buscarPorNome(String nome){
        
        return repository.findByNome(nome);

    }

    @POST
    @Transactional
    public Aluno incluirAluno(Aluno aluno){

        Aluno novoAluno = new Aluno();
        novoAluno.setNome(aluno.getNome());

        repository.persist(novoAluno);

        return novoAluno;

    }

    @PUT
    @Path("/{id}")
    @Transactional
    public void alterarAluno(Long id, Aluno aluno){

        Aluno edicaoAluno = repository.findById(id);

        edicaoAluno.setNome(aluno.getNome());

    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public void deletarAluno(Long id){

        repository.deleteById(id);

    } 

}
