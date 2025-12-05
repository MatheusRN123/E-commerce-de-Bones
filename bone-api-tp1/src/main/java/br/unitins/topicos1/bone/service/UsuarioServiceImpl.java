package br.unitins.topicos1.bone.service;

import java.util.List;

import br.unitins.topicos1.bone.model.Usuario;
import br.unitins.topicos1.bone.repository.UsuarioRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.jboss.logging.Logger;

@ApplicationScoped
public class UsuarioServiceImpl implements UsuarioService {

    private static final Logger LOG = Logger.getLogger(UsuarioServiceImpl.class);

    @Inject
    UsuarioRepository repository;

    @Override
    public List<Usuario> findAll() {
        LOG.info("Buscando todos os usuários");
        List<Usuario> usuarios = repository.listAll();
        LOG.infof("Encontrados %d usuários", usuarios.size());
        return usuarios;
    }

    @Override
    public Usuario findByLogin(String login) {
        LOG.infof("Buscando usuário pelo login: %s", login);
        Usuario usuario = repository.findByLogin(login);
        if (usuario != null) {
            LOG.infof("Usuário encontrado: %s", usuario.getLogin());
        } else {
            LOG.warnf("Usuário com login '%s' não encontrado", login);
        }
        return usuario;
    }

    @Override
    public Usuario findByLoginAndSenha(String login, String senha) {
        LOG.infof("Buscando usuário pelo login e senha: %s", login);
        Usuario usuario = repository.findByLoginSenha(login, senha);
        if (usuario != null) {
            LOG.infof("Usuário autenticado com sucesso: %s", login);
        } else {
            LOG.warnf("Falha na autenticação para login: %s", login);
        }
        return usuario;
    }

    @Override
    public Usuario findById(Long id) {
        LOG.infof("Buscando usuário pelo ID: %d", id);
        Usuario usuario = repository.findById(id);
        if (usuario != null) {
            LOG.infof("Usuário encontrado: %s", usuario.getLogin());
        } else {
            LOG.warnf("Usuário com ID %d não encontrado", id);
        }
        return usuario;
    }

    @Override
    public void save(Usuario usuario) {
        LOG.infof("Salvando usuário: %s", usuario.getLogin());
        repository.persist(usuario);
        LOG.infof("Usuário '%s' salvo com sucesso", usuario.getLogin());
    }
}
