package br.unitins.topicos1.bone.repository;

<<<<<<< HEAD
import br.unitins.topicos1.bone.model.Usuario;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UsuarioRepository implements PanacheRepository<Usuario> {

    public Usuario findByLoginSenha(String login, String senha) {
        return find("SELECT u FROM Usuario u WHERE u.login = ?1 AND u.senha = ?2 ", login, senha).firstResult();
    }

    public Usuario findByLogin(String login) {
        return find("SELECT u FROM Usuario u WHERE u.login = ?1 ", login).firstResult();
=======
import java.util.List;

import br.unitins.topicos1.bone.model.Usuario;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

public class UsuarioRepository implements PanacheRepository<Usuario> {

        public List<Usuario> findByNome(String nome){
        return find("SELECT u FROM Usuario u WHERE u.nome LIKE ?1", "%" + nome + "%").list();
>>>>>>> a47f27e82323fbf2d3a412623eb607e53f435613
    }

}