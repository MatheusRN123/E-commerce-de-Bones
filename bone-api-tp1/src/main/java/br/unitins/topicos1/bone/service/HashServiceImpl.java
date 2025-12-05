package br.unitins.topicos1.bone.service;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import jakarta.enterprise.context.ApplicationScoped;
import org.jboss.logging.Logger;

@ApplicationScoped
public class HashServiceImpl implements HashService {

    private static final Logger LOG = Logger.getLogger(HashServiceImpl.class);

    private String salt = "#$127732&";
    private Integer iterationCount = 403;
    private Integer keyLength = 512;

    @Override
    public String getHashSenha(String senha) {
        LOG.info("Gerando hash para a senha fornecida");
        try {
            byte[] result = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512")
                    .generateSecret(new PBEKeySpec(senha.toCharArray(),
                            salt.getBytes(),
                            iterationCount,
                            keyLength))
                    .getEncoded();

            String hash = Base64.getEncoder().encodeToString(result);
            LOG.info("Hash gerado com sucesso");
            return hash;

        } catch (InvalidKeySpecException e) {
            LOG.error("Erro ao gerar o hash: InvalidKeySpecException", e);
            throw new RuntimeException("Erro ao gerar o hash", e);
        } catch (NoSuchAlgorithmException e) {
            LOG.error("Erro ao gerar o hash: NoSuchAlgorithmException", e);
            throw new RuntimeException("Erro ao gerar o hash", e);
        }
    }

    public static void main(String[] args) {
        HashService hash = new HashServiceImpl();
        System.out.println(hash.getHashSenha("123456"));
    }
}
