package br.unitins.topicos1.bone.resources;

import br.unitins.topicos1.bone.dto.AuthDTO;
import br.unitins.topicos1.bone.model.Perfil;
import br.unitins.topicos1.bone.model.Usuario;
import br.unitins.topicos1.bone.resource.AuthResource;
import br.unitins.topicos1.bone.service.JwtService;
import br.unitins.topicos1.bone.service.UsuarioService;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@QuarkusTest
class AuthResourceTest {

    @Inject
    AuthResource authResource; // inject real resource

    @InjectMock
    UsuarioService usuarioService; // mock para controlar o comportamento do service

    @InjectMock
    JwtService jwtService; // mock para gerar token

    @Test
    void testLoginValido() {
        // Arrange
        String login = "matheus";
        String senha = "12345";
        String tokenMock = "token123";

        AuthDTO dto = new AuthDTO(login, senha);

        Usuario usuarioMock = new Usuario();
        usuarioMock.setLogin(login);
        usuarioMock.setPerfil(Perfil.USER);

        when(usuarioService.findByLoginAndSenha(login, senha)).thenReturn(usuarioMock);
        when(jwtService.generateJwt(login, Perfil.USER)).thenReturn(tokenMock);

        // Act
        Response response = authResource.login(dto);

        // Assert
        assertEquals(200, response.getStatus());
        assertEquals("Bearer " + tokenMock, response.getHeaderString("Authorization"));

        verify(usuarioService).findByLoginAndSenha(login, senha);
        verify(jwtService).generateJwt(login, Perfil.USER);
    }

    @Test
    void testLoginInvalido() {
        // Arrange
        String login = "matheus";
        String senha = "12345";

        AuthDTO dto = new AuthDTO(login, senha);

        when(usuarioService.findByLoginAndSenha(login, senha)).thenReturn(null);

        // Act
        Response response = authResource.login(dto);

        // Assert
        assertEquals(401, response.getStatus());
        assertEquals("Login ou senha inválidos", response.getEntity());

        verify(usuarioService).findByLoginAndSenha(login, senha);
        verifyNoInteractions(jwtService);
    }
}
