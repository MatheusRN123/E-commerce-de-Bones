package br.unitins.topicos1.bone.model;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("BORDADA")
public class EstampaBordada extends Estampa {
    
    @Column(name = "cor_linha")
    private String corLinha;

    @Column(name = "qt_cores")
    private Integer quantCores;

    public void setCorLinha(String corLinha){
        this.corLinha = corLinha;
    }

    public String getCorLinha(){
        return corLinha;
    }

    public void setQuantCores(Integer quantCores){
        this.quantCores = quantCores;
    }

    public Integer getQuantCores(){
        return quantCores;
    }
}
