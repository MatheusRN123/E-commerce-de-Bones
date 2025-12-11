package br.unitins.topicos1.bone.repository;

import java.util.List;

import br.unitins.topicos1.bone.model.Estado;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class EstadoRepository implements PanacheRepository<Estado> {

    public List<Estado> findBySigla(String sigla){
        return find("SELECT e FROM Estado e WHERE e.sigla LIKE ?1", "%" + sigla + "%").list();
    }
}
