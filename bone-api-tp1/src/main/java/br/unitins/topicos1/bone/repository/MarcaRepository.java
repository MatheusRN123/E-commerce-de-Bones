package br.unitins.topicos1.bone.repository;

import java.util.List;

import br.unitins.topicos1.bone.model.Marca;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MarcaRepository implements PanacheRepository<Marca> {
    
    public List<Marca> findByNome(String nome){
        return find("SELECT m FROM Marca m WHERE m.nome LIKE ?1 ", "%"+nome+"%").list();
    }
}
