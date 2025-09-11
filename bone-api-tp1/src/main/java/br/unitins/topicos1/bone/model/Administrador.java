package br.unitins.topicos1.bone.model;

import jakarta.persistence.Entity;

@Entity
public class Administrador extends Pessoa {
    

    private String email;
    private String senha;
    private String nivelAcesso;

    
    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }


    public String getSenha(){
        return senha;
    }

    public void setSenha(String senha){
        this.senha = senha;
    }
    

    public String getNivelAcesso(){
        return nivelAcesso;
    }

    public void setNivelAcesso(String nivelAcesso){
        this.nivelAcesso = nivelAcesso;
    }

}
