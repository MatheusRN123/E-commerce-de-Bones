package br.unitins.topicos1.bone.resources;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import org.junit.jupiter.api.Test;

import br.unitins.topicos1.bone.dto.UsuarioDTO;

import io.restassured.http.ContentType;
import io.quarkus.test.security.TestSecurity;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
@TestSecurity(authorizationEnabled = false)
public class UsuarioResourceTest {

    @Test
    public void testBuscarTodos() {
        given()
        .when().get("/usuarios")
        .then()
            .statusCode(200)
            .body("$.size()", greaterThan(0));
    }

    @Test
    public void testBuscarPorLogin() {
        given()
            .pathParam("login", "admin")
        .when().get("/usuarios/login/{login}")
        .then()
            .statusCode(anyOf(is(200), is(404)));
    }

    @Test
    public void testBuscarPorLoginESenha() {
        given()
            .pathParam("login", "admin")
            .pathParam("senha", "1234")
        .when().get("/usuarios/auth/{login}/{senha}")
        .then()
            .statusCode(anyOf(is(200), is(404)));
    }

    @Test
    public void testBuscarPorId() {
        given()
            .pathParam("id", 1)
        .when().get("/usuarios/{id}")
        .then()
            .statusCode(anyOf(is(200), is(404)));
    }

    @Test
    public void testCadastrarUsuario() {
        // Note: perfil será definido automaticamente como USER no endpoint
        UsuarioDTO dto = new UsuarioDTO(
            "Usuario Teste",
            "usuarioTeste",
            "senha123"
        );

        given()
            .contentType(ContentType.JSON)
            .body(dto)
        .when().post("/usuarios/cadastro")
        .then()
            .statusCode(anyOf(is(201), is(400)));
    }

    @Test
    public void testPromoverUsuario() {
        UsuarioDTO dto = new UsuarioDTO(
            "Usuario Para Promocao",
            "usuarioPromocao",
            "senha123"
        );
    
        // Cadastro do usuário
        int usuarioId = 
        given()
            .contentType(ContentType.JSON)
            .body(dto)
        .when().post("/usuarios/cadastro")
        .then()
            .statusCode(201)
            .extract().path("id"); // agora funciona
    
        // Promover o usuário para ADM
        given()
            .pathParam("id", usuarioId)
        .when().put("/usuarios/{id}/promover") // sugiro simplificar o path no resource
        .then()
            .statusCode(200)
            .body("perfil", equalTo("ADM"));
    }
}
