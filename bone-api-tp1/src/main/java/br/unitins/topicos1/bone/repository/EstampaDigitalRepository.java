package br.unitins.topicos1.bone.repository;

import br.unitins.topicos1.bone.model.EstampaDigital;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class EstampaDigitalRepository implements PanacheRepository<EstampaDigital> {

}
