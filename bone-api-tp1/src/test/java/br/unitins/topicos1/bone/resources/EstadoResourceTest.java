package br.unitins.topicos1.bone.resources;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import org.junit.jupiter.api.Test;

import br.unitins.topicos1.bone.dto.EstadoDTO;
import br.unitins.topicos1.bone.service.JwtService;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;

import jakarta.inject.Inject;

@QuarkusTest
public class EstadoResourceTest {

    @Inject
    JwtService jwtService;

    @Test
    public void testBuscarTodos() {
        given()
        .when()
            .get("/estados")
        .then()
            .statusCode(200)
            .body("size()", greaterThanOrEqualTo(0));
    }

    @Test
    public void testBuscarPorNome() {
        given()
            .pathParam("nome", "São Paulo")
        .when()
            .get("/estados/find/{nome}")
        .then()
            .statusCode(anyOf(is(200), is(204)));
    }

    @Test
    public void testBuscarPorSigla() {
        given()
            .pathParam("sigla", "SP")
        .when()
            .get("/estados/find/{sigla}")
        .then()
            .statusCode(anyOf(is(200), is(204)));
    }

    @Test
    public void testBuscarPorId() {
        given()
        .when()
            .get("/estados/1")
        .then()
            .statusCode(anyOf(is(200), is(404)));
    }

    @Test
    public void testIncluirEstado() {
        EstadoDTO dto = new EstadoDTO(
            "EstadoTeste",
            "ET",
            null // idsCidades null para não depender de dados existentes
        );

        given()
            .contentType(ContentType.JSON)
            .body(dto)
        .when()
            .post("/estados")
        .then()
            .statusCode(anyOf(is(201), is(200)))
            .body("nome", is("EstadoTeste"));
    }

    @Test
    public void testAlterarEstado() {
        EstadoDTO dto = new EstadoDTO(
            "EstadoAtualizado",
            "EA",
            null // idsCidades null
        );

        given()
            .contentType(ContentType.JSON)
            .body(dto)
        .when()
            .put("/estados/1")
        .then()
            .statusCode(anyOf(is(204), is(404)));
    }

    @Test
    public void testDeletarEstado() {
        given()
        .when()
            .delete("/estados/3")
        .then()
            .statusCode(anyOf(is(204), is(404)));
    }
}
