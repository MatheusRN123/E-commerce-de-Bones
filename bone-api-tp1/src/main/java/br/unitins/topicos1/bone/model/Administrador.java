package br.unitins.topicos1.bone.model;

import jakarta.persistence.Entity;

@Entity
public class Administrador extends Cliente {
    

    private String nivelAcesso;

    public String getNivelAcesso(){
        return nivelAcesso;
    }

    public void setNivelAcesso(String nivelAcesso){
        this.nivelAcesso = nivelAcesso;
    }

}
