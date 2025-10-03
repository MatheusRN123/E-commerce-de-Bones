package br.unitins.topicos1.bone.repository;

import java.util.List;

import br.unitins.topicos1.bone.model.Estoque;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class EstoqueRepository implements PanacheRepository<Estoque> {
    
    public List<Estoque> findByQuantidade(Integer quantidade){
        return find("SELECT e FROM Estoque e WHERE e.quantidade = ?1 ", quantidade).list();
    }

    public Estoque findByBoneId(Long idBone){
        return find("bone.id", idBone).firstResult();
    }
}
