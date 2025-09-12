package br.unitins.topicos1.bone.repository;

import java.util.List;

import br.unitins.topicos1.bone.model.Administrador;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AdministradorRepository implements PanacheRepository<Administrador> {
    
    public List<Administrador> findByNome(String nome){
        return find("SELECT * FROM Administrador a WHERE a.nome LIKE ?1", "%" + nome + "%").list();
    }

}
