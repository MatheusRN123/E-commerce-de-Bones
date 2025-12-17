package br.unitins.topicos1.bone.service;

import java.util.List;

import br.unitins.topicos1.bone.model.Perfil;
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

    @Inject
    HashService hashService;

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
        String senhaHash = null;
        try {
            senhaHash = hashService.getHashSenha(senha);
        } catch (Exception e) {
            LOG.error("Erro ao gerar hash da senha", e);
            return null;
        }

        Usuario usuario = repository.findByLoginSenha(login,    senhaHash);

        if (usuario != null) {
            LOG.infof("Usuário autenticado com  sucesso: %s", login);
        } else {
            LOG.warnf("Falha na autenticação para   login: %s", login);
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
    public Usuario create(Usuario usuario) {
        LOG.infof("Criando usuário: %s", usuario.getLogin());

        try {
            String hash = hashService.getHashSenha(usuario.getSenha());
            usuario.setSenha(hash);
        } catch (Exception e) {
            LOG.error("Erro ao gerar hash da senha ao criar usuário", e);
            return null;
        }
    
        usuario.setPerfil(Perfil.USER);

        repository.persist(usuario);
        LOG.infof("Usuário '%s' salvo com sucesso com perfil padrão", usuario.getLogin());

        return usuario;
    }

    @Override
    public void delete(Long id) {
        LOG.infof("Deletando usuário com ID: %d", id);

        Usuario usuario = repository.findById(id);

        if (usuario == null) {
            LOG.warnf("Não foi possível deletar.    Usuário com ID %d não encontrado.", id);
            return;
        }

        repository.delete(usuario);
        LOG.infof("Usuário com ID %d deletado com   sucesso.", id);
    }

    @Override
    public Usuario update(Long id, Usuario usuarioAtualizado) {
        LOG.infof("Atualizando usuário com ID: %d",     id);

        Usuario usuario = repository.findById(id);
        if (usuario == null) {
            LOG.warnf("Usuário com ID %d não    encontrado para atualização.", id);
            return null;
        }

        if (usuarioAtualizado.getLogin() != null)
            usuario.setLogin(usuarioAtualizado.getLogin());

        if (usuarioAtualizado.getSenha() != null) {
            try {
                String hash = hashService.getHashSenha  (usuarioAtualizado.getSenha());
                usuario.setSenha(hash);
            } catch (Exception e) {
                LOG.error("Erro ao gerar hash da nova   senha", e);
            }
        }
    
        repository.persist(usuario);

        LOG.infof("Usuário com ID %d atualizado com     sucesso.", id);

        return usuario;
    }

    public Usuario promoverParaAdmin(Long id) {
        LOG.infof("Promovendo usuário ID %d para ADMIN", id);
        
        Usuario usuario = repository.findById(id);
        if (usuario == null) {
            LOG.warnf("Usuário com ID %d não encontrado", id);
            return null;
        }
    
        usuario.setPerfil(Perfil.ADM);
        repository.persist(usuario);
    
        LOG.infof("Usuário ID %d promovido a ADMIN com sucesso", id);
        return usuario;
    }
}
