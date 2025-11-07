package br.unitins.topicos1.bone.resource;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;

import org.junit.jupiter.api.Test;

import br.unitins.topicos1.bone.dto.EstampaDigitalDTO;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;

@QuarkusTest
public class EstampaDigitalResourceTest {

    @Test
    public void testCreate() {
        EstampaDigitalDTO dto = new EstampaDigitalDTO(
            "EstampaDigitalTeste",
            "DIGITAL",
            "frente",
            "Estampa digital de teste",
            "Alta resolução"
        );

        given()
            .contentType(ContentType.JSON)
            .body(dto)
            .when().post("/estampas/digital")
            .then()
            .statusCode(anyOf(is(201), is(200)))
            .body("nome", is("EstampaDigitalTeste"));
    }

    @Test
    public void testUpdate() {
        EstampaDigitalDTO dto = new EstampaDigitalDTO(
            "EstampaDigitalAtualizada",
            "DIGITAL",
            "costas",
            "Estampa digital atualizada",
            "4K"
        );

        given()
            .contentType(ContentType.JSON)
            .body(dto)
            .pathParam("id", 1)
            .when().put("/estampas/digital/{id}")
            .then()
            .statusCode(anyOf(is(200), is(204), is(404)));
    }

    @Test
    public void testDelete() {
        given()
            .pathParam("id", 2)
            .when().delete("/estampas/digital/{id}")
            .then()
            .statusCode(anyOf(is(204), is(404)));
    }
}
