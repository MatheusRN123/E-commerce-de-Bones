package br.unitins.topicos1.bone.repository;

import java.util.List;

import br.unitins.topicos1.bone.model.Estado;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
<<<<<<< HEAD
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
=======

>>>>>>> a47f27e82323fbf2d3a412623eb607e53f435613
public class EstadoRepository implements PanacheRepository<Estado> {
    
    public List<Estado> findByNome(String nome){
        return find("SELECT e FROM Estado e WHERE e.nome LIKE ?1", "%" + nome + "%").list();
    }

    public List<Estado> findBySigla(String sigla){
        return find("SELECT e FROM Estado e WHERE e.sigla LIKE ?1", "%" + sigla + "%").list();
    }

}
