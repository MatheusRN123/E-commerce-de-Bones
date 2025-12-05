package br.unitins.topicos1.bone.resource;

import br.unitins.topicos1.bone.dto.UsuarioDTO;
import br.unitins.topicos1.bone.model.Usuario;
import br.unitins.topicos1.bone.service.HashService;
import br.unitins.topicos1.bone.service.UsuarioService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
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
    public Response buscarTodos(){
        LOG.info("Requisição: buscar todos os usuários");
        var usuarios = service.findAll();
        LOG.infof("Retornando %d usuários", usuarios.size());
        return Response.ok(usuarios).build();
    }

    @GET
    @Path("/find/{login}")
    @RolesAllowed("ADM")
    public Response buscarPorLogin(@PathParam("login") String login){
        LOG.infof("Requisição: buscar usuário pelo login '%s'", login);
        Usuario usuario = service.findByLogin(login);
        if(usuario == null){
            LOG.warnf("Usuário com login '%s' não encontrado", login);
            return Response.status(Status.NOT_FOUND).build();
        }
        LOG.infof("Usuário encontrado: %s", usuario.getLogin());
        return Response.ok(usuario).build();
    }

    @GET
    @Path("/find/{login}/{senha}")
    @RolesAllowed("ADM")
    public Response buscarPorLoginESenha(@PathParam("login") String login, @PathParam("senha") String senha){
        LOG.infof("Requisição: autenticar usuário login='%s'", login);
        Usuario usuario = service.findByLoginAndSenha(login, senha);
        if(usuario == null){
            LOG.warnf("Falha na autenticação para login: %s", login);
            return Response.status(Status.NOT_FOUND).build();
        }
        LOG.infof("Usuário autenticado com sucesso: %s", login);
        return Response.ok(usuario).build();
    }

    @GET
    @Path("/{id}")
    @RolesAllowed("ADM")
    public Response buscarPorId(@PathParam("id") Long id) {
        LOG.infof("Requisição: buscar usuário pelo ID %d", id);
        Usuario usuario = service.findById(id);
        if(usuario == null){
            LOG.warnf("Usuário com ID %d não encontrado", id);
            return Response.status(Status.NOT_FOUND).build();
        }
        LOG.infof("Usuário encontrado: %s", usuario.getLogin());
        return Response.ok(usuario).build();
    }

    @POST
    @Path("/cadastro")
    @Transactional
    @RolesAllowed({"ADM", "USER"})
    public Response cadastrarUsuario(UsuarioDTO dto) {
        LOG.infof("Requisição: cadastrar usuário '%s'", dto.login());

        if(dto.login() == null || dto.senha() == null || dto.nome() == null) {
            LOG.warn("Falha no cadastro: campos obrigatórios faltando");
            return Response.status(Status.BAD_REQUEST).entity("Campos obrigatórios faltando").build();
        }

        String hash = hashService.getHashSenha(dto.senha());

        Usuario usuario = new Usuario();
        usuario.setNome(dto.nome());
        usuario.setLogin(dto.login());
        usuario.setSenha(hash);
        usuario.setPerfil(dto.perfil());

        service.save(usuario);

        LOG.infof("Usuário '%s' criado com sucesso", dto.login());
        return Response.status(Status.CREATED).entity("Usuário criado com sucesso").build();
    }
}
