package br.unitins.topicos1.bone.repository;

import java.util.List;

import br.unitins.topicos1.bone.model.Usuario;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

public class UsuarioRepository implements PanacheRepository<Usuario> {

        public List<Usuario> findByNome(String nome){
        return find("SELECT u FROM Usuario u WHERE u.nome LIKE ?1", "%" + nome + "%").list();
    }

}