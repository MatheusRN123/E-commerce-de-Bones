package br.unitins.topicos1.bone.repository;

import br.unitins.topicos1.bone.model.EstampaBordada;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class EstampaBordadaRepository implements PanacheRepository<EstampaBordada> {
}
