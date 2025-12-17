package br.unitins.topicos1.bone.resources;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import br.unitins.topicos1.bone.dto.EstoqueDTO;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.TestSecurity;
import io.restassured.http.ContentType;

@QuarkusTest
@TestSecurity(user = "admin", roles = {"ADM"})
public class EstoqueResourceTest {

    private static final Long ESTOQUE_EXISTENTE_ID = 1L;

    @Test
    public void testVerificarDisponibilidadeSucesso() {
        given()
            .pathParam("id", ESTOQUE_EXISTENTE_ID)
        .when()
            .get("/estoques/{id}/disponibilidade")
        .then()
            .statusCode(200)
            .body("$", equalTo(true));
    }

    @Test
    public void testAtualizarQuantidade() {
        EstoqueDTO dto = new EstoqueDTO(30, LocalDate.now());

        given()
            .contentType(ContentType.JSON)
            .body(dto)
            .pathParam("id", ESTOQUE_EXISTENTE_ID)
        .when()
            .put("/estoques/{id}/quantidade")
        .then()
            .statusCode(204);

        given()
            .pathParam("id", ESTOQUE_EXISTENTE_ID)
        .when()
            .get("/estoques/{id}/disponibilidade")
        .then()
            .statusCode(200)
            .body("$", equalTo(true));
    }

    @Test
    public void testAdicionarQuantidade() {
        EstoqueDTO dto = new EstoqueDTO(5, LocalDate.now());

        given()
            .contentType(ContentType.JSON)
            .body(dto)
            .pathParam("id", ESTOQUE_EXISTENTE_ID)
        .when()
            .put("/estoques/{id}/adicionar")
        .then()
            .statusCode(204);

        given()
            .pathParam("id", ESTOQUE_EXISTENTE_ID)
        .when()
            .get("/estoques/{id}/disponibilidade")
        .then()
            .statusCode(200)
            .body("$", equalTo(true));
    }
}
