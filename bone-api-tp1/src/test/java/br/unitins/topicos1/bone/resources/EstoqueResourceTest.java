package br.unitins.topicos1.bone.resources;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.anyOf;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import br.unitins.topicos1.bone.dto.EstoqueDTO;
import br.unitins.topicos1.bone.service.JwtService;
import io.restassured.http.ContentType;

import jakarta.inject.Inject;

import io.quarkus.test.security.TestSecurity;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
@TestSecurity(authorizationEnabled = false)
public class EstoqueResourceTest {

    @Inject
    JwtService jwtService;

    @Test
    public void testFindAll() {
        given()
        .when().get("/estoques")
        .then()
            .statusCode(200)
            .body(anything());
    }

    @Test
    public void testFindByQuantidade() {
        given()
            .pathParam("quantidade", 5)
        .when().get("/estoques/find/{quantidade}")
        .then()
            .statusCode(anyOf(is(200), is(204)));
    }

    @Test
    public void testFindById() {
        given()
            .pathParam("id", 1)
        .when().get("/estoques/{id}")
        .then()
            .statusCode(anyOf(is(200), is(404)));
    }

    @Test
    public void testCreateEstoque() {
        EstoqueDTO dto = new EstoqueDTO(15, LocalDate.now());

        given()
            .contentType(ContentType.JSON)
            .body(dto)
        .when().post("/estoques")
        .then()
            .statusCode(anyOf(is(201), is(200)))
            .body("quantidade", is(15));
    }

    @Test
    public void testUpdateEstoque() {
        EstoqueDTO dto = new EstoqueDTO(25, LocalDate.now());

        given()
            .contentType(ContentType.JSON)
            .body(dto)
            .pathParam("id", 1)
        .when().put("/estoques/{id}")
        .then()
            .statusCode(anyOf(is(204), is(404)));
    }

    @Test
    public void testDeleteEstoque() {
        given()
            .pathParam("id", 4)
        .when().delete("/estoques/{id}")
        .then()
            .statusCode(anyOf(is(204), is(404)));
    }

    @Test
    public void testVerificarDisponibilidade() {
        given()
            .pathParam("id", 1)
        .when().get("/estoques/{id}/disponibilidade")
        .then()
            .statusCode(anyOf(is(200), is(404)));
    }

    @Test
    public void testAtualizarQuantidade() {
        EstoqueDTO dto = new EstoqueDTO(30, LocalDate.now());

        given()
            .contentType(ContentType.JSON)
            .body(dto)
            .pathParam("id", 1)
        .when().put("/estoques/{id}/quantidade")
        .then()
            .statusCode(anyOf(is(204), is(404)));
    }

    @Test
    public void testAdicionarQuantidade() {
        EstoqueDTO dto = new EstoqueDTO(10, LocalDate.now());

        given()
            .contentType(ContentType.JSON)
            .body(dto)
            .pathParam("id", 1)
        .when().put("/estoques/{id}/adicionar")
        .then()
            .statusCode(anyOf(is(204), is(404)));
    }
}
