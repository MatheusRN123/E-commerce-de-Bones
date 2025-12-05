package br.unitins.topicos1.bone.service;

import java.time.Duration;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import br.unitins.topicos1.bone.model.Perfil;
import io.smallrye.jwt.build.Jwt;
import jakarta.enterprise.context.ApplicationScoped;
import org.jboss.logging.Logger;

@ApplicationScoped
public class JwtServiceImpl implements JwtService {

    private static final Logger LOG = Logger.getLogger(JwtServiceImpl.class);

    private static final Duration EXPIRATION_TIME = Duration.ofHours(24);

    @Override
    public String generateJwt(String login, Perfil perfil) {
        LOG.infof("Gerando JWT para usuário: %s com perfil: %s", login, perfil.name());

        // data
        Instant expiryDate = Instant.now().plus(EXPIRATION_TIME);

        // papeis (perfil)
        Set<String> roles = new HashSet<String>();
        roles.add(perfil.name());

        // gerando o token
        String token = Jwt.issuer("unitins-jwt")
                .subject(login)
                .groups(roles)
                .expiresAt(expiryDate)
                .sign();

        LOG.infof("JWT gerado para usuário: %s, expira em: %s", login, expiryDate);
        return token;
    }
    
}
