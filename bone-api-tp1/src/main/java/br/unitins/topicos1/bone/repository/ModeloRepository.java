package br.unitins.topicos1.bone.repository;

import java.util.List;

import br.unitins.topicos1.bone.model.Modelo;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ModeloRepository implements PanacheRepository<Modelo> {
    
    public List<Modelo> findByNome(String nome){
        return find("SELECT m FROM Modelo m WHERE m.nome LIKE ?1", "%" + nome + "%").list();
    }

}
