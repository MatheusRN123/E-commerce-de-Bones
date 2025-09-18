package br.unitins.topicos1.bone.repository;

import java.util.List;

import br.unitins.topicos1.bone.model.Bone;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class BoneRepository implements PanacheRepository<Bone> {
    
    public List<Bone> findByNome(String nome){
        return find("SELECT b FROM Bone b WHERE b.nome LIKE ?1", "%" + nome + "%").list();
    }

}
