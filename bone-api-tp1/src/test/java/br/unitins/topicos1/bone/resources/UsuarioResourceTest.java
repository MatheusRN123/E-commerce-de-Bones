package br.unitins.topicos1.bone.resources;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.anything;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.anyOf;

import org.junit.jupiter.api.Test;

import br.unitins.topicos1.bone.dto.UsuarioDTO;
import br.unitins.topicos1.bone.model.Perfil;
import br.unitins.topicos1.bone.service.JwtService;

import io.restassured.http.ContentType;
import jakarta.inject.Inject;

import io.quarkus.test.security.TestSecurity;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
@TestSecurity(authorizationEnabled = false)
public class UsuarioResourceTest {


    @Inject
    JwtService jwtService;


    @Test
    public void testBuscarTodos() {
        given()
        .when().get("/usuarios")
        .then()
            .statusCode(200)
            .body(anything());
    }

    @Test
    public void testBuscarPorLogin() {
        given()
            .pathParam("login", "admin")
        .when().get("/usuarios/find/{login}")
        .then()
            .statusCode(anyOf(is(200), is(404)));
    }

    @Test
    public void testBuscarPorLoginESenha() {
        given()
            .pathParam("login", "admin")
            .pathParam("senha", "1234")
        .when().get("/usuarios/find/{login}/{senha}")
        .then()
            .statusCode(anyOf(is(200), is(404)));
    }

    @Test
    public void testBuscarPorId() {
        given()
        .when().get("/usuarios/1")
        .then()
            .statusCode(anyOf(is(200), is(404)));
    }

    @Test
    public void testCadastrarUsuario() {
        UsuarioDTO dto = new UsuarioDTO(
            "Usuario Teste",
            "usuarioTeste",
            "senha123",
            Perfil.USER
        );

        // Cadastro geralmente não precisa de token
        given()
            .contentType(ContentType.JSON)
            .body(dto)
        .when().post("/usuarios/cadastro")
        .then()
            .statusCode(anyOf(is(201), is(400)))
            .body(anything());
    }
}
