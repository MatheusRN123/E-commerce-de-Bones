package br.unitins.topicos1.bone.resource;

import br.unitins.topicos1.bone.dto.UsuarioDTO;
import br.unitins.topicos1.bone.model.Usuario;
import br.unitins.topicos1.bone.service.HashService;
import br.unitins.topicos1.bone.service.UsuarioService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import org.jboss.logging.Logger;

@Path("/usuarios")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UsuarioResource {
    
    private static final Logger LOG = Logger.getLogger(UsuarioResource.class);

    @Inject
    UsuarioService service;

    @Inject
    HashService hashService;

    @GET
    @RolesAllowed({"ADM", "USER"})
    public Response buscarTodos() {
        LOG.info("Requisição: buscar todos os usuários");
        var usuarios = service.findAll();
        LOG.infof("Retornando %d usuários", usuarios.size());
        return Response.ok(usuarios).build();
    }

    @GET
    @Path("/{id}")
    @RolesAllowed("ADM")
    public Response buscarPorId(@PathParam("id") Long id) {
        LOG.infof("Requisição: buscar usuário pelo ID %d", id);
        Usuario usuario = service.findById(id);
        if (usuario == null) {
            LOG.warnf("Usuário com ID %d não encontrado", id);
            return Response.status(Status.NOT_FOUND).build();
        }
        return Response.ok(usuario).build();
    }

    @GET
    @Path("/login/{login}")
    @RolesAllowed("ADM")
    public Response buscarPorLogin(@PathParam("login") String login) {
        LOG.infof("Requisição: buscar usuário pelo login '%s'", login);
        Usuario usuario = service.findByLogin(login);
        if (usuario == null) {
            LOG.warnf("Usuário com login '%s' não encontrado", login);
            return Response.status(Status.NOT_FOUND).build();
        }
        return Response.ok(usuario).build();
    }

    @GET
    @Path("/auth/{login}/{senha}")
    @RolesAllowed("ADM")
    public Response buscarPorLoginESenha(@PathParam("login") String login, @PathParam("senha") String senha) {
        LOG.infof("Requisição: autenticar usuário login='%s'", login);
        Usuario usuario = service.findByLoginAndSenha(login, senha);
        if (usuario == null) {
            LOG.warnf("Falha na autenticação para login: %s", login);
            return Response.status(Status.NOT_FOUND).build();
        }
        return Response.ok(usuario).build();
    }

    @POST
    @Path("/cadastro")
    @Transactional
    @RolesAllowed({"ADM", "USER"})
    public Response cadastrarUsuario(UsuarioDTO dto) {
        LOG.infof("Requisição: cadastrar usuário '%s'", dto.login());

        if (dto.login() == null || dto.senha() == null || dto.nome() == null) {
            LOG.warn("Falha no cadastro: campos obrigatórios faltando");
            return Response.status(Status.BAD_REQUEST).entity("Campos obrigatórios faltando").build();
        }

        String hash = hashService.getHashSenha(dto.senha());

        Usuario usuario = new Usuario();
        usuario.setNome(dto.nome());
        usuario.setLogin(dto.login());
        usuario.setSenha(hash);
        usuario.setPerfil(dto.perfil());

        service.create(usuario);

        return Response.status(Status.CREATED).build();
    }


    @PUT
    @Path("/{id}")
    @Transactional
    @RolesAllowed("ADM")
    public Response atualizarUsuario(@PathParam("id") Long id, UsuarioDTO dto) {
        LOG.infof("Requisição: atualizar usuário com ID %d", id);

        Usuario usuarioAtualizado = new Usuario();
        usuarioAtualizado.setNome(dto.nome());
        usuarioAtualizado.setLogin(dto.login());
        usuarioAtualizado.setSenha(dto.senha());
        usuarioAtualizado.setPerfil(dto.perfil());

        Usuario atualizado = service.update(id, usuarioAtualizado);

        if (atualizado == null) {
            LOG.warnf("Usuário com ID %d não encontrado para atualização", id);
            return Response.status(Status.NOT_FOUND).build();
        }

        return Response.noContent().build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    @RolesAllowed("ADM")
    public Response deletarUsuario(@PathParam("id") Long id) {
        LOG.infof("Requisição: deletar usuário com ID %d", id);

        Usuario usuario = service.findById(id);
        if (usuario == null) {
            LOG.warnf("Usuário com ID %d não encontrado para deletar", id);
            return Response.status(Status.NOT_FOUND).build();
        }

        service.delete(id);
        return Response.noContent().build();
    }
}