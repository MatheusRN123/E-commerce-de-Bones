package br.unitins.topicos1.bone.resources;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.anyOf;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import br.unitins.topicos1.bone.dto.EstoqueDTO;
import br.unitins.topicos1.bone.service.JwtService;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;

import jakarta.inject.Inject;

@QuarkusTest
@TestInstance(Lifecycle.PER_CLASS)
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
        .when().get("/estoques/1")
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
        .when().put("/estoques/1")
        .then()
            .statusCode(anyOf(is(200), is(204), is(404)));
    }

    @Test
    public void testDeleteEstoque() {
        given()
        .when().delete("/estoques/4")
        .then()
            .statusCode(anyOf(is(204), is(404)));
    }

    @Test
    public void testVerificarDisponibilidade() {
        given()
        .when().get("/estoques/1/disponivel")
        .then()
            .statusCode(anyOf(is(200), is(404)));
    }

    @Test
    public void testAtualizarQuantidade() {
        EstoqueDTO dto = new EstoqueDTO(30, LocalDate.now());

        given()
            .contentType(ContentType.JSON)
            .body(dto)
        .when().patch("/estoques/1/atualizar")
        .then()
            .statusCode(anyOf(is(200), is(204), is(404)));
    }

    @Test
    public void testAdicionarQuantidade() {
        EstoqueDTO dto = new EstoqueDTO(10, LocalDate.now());

        given()
            .contentType(ContentType.JSON)
            .body(dto)
        .when().patch("/estoques/1/adicionar")
        .then()
            .statusCode(anyOf(is(200), is(204), is(404)));
    }
}
