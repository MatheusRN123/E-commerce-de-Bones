package br.unitins.topicos1.bone.service;

import br.unitins.topicos1.bone.model.Perfil;

public interface JwtService {

    public String generateJwt(String usuario, Perfil perfil);
    
}
