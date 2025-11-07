package br.unitins.topicos1.bone.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("DIGITAL")
public class EstampaDigital extends Estampa {

    private String resolucao;
    
    public String getResolucao() {
        return resolucao;
    }
    public void setResolucao(String resolucao) {
        this.resolucao = resolucao;
    }
}
