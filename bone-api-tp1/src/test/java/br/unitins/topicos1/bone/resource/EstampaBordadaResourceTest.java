package br.unitins.topicos1.bone.resource;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;

import org.junit.jupiter.api.Test;

import br.unitins.topicos1.bone.dto.EstampaBordadaDTO;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;

@QuarkusTest
public class EstampaBordadaResourceTest {

    @Test
    public void testCreate() {
        EstampaBordadaDTO dto = new EstampaBordadaDTO(
            "EstampaBordadaTeste",
            "BORDADA",
            "frente",
            "Estampa bordada de teste",
            "branca",
            1
        );

        given()
            .contentType(ContentType.JSON)
            .body(dto)
            .when().post("/estampas/bordada")
            .then()
            .statusCode(anyOf(is(201), is(200)))
            .body("nome", is("EstampaBordadaTeste"));
    }

    @Test
    public void testUpdate() {
        EstampaBordadaDTO dto = new EstampaBordadaDTO(
            "EstampaBordadaAtualizada",
            "BORDADA",
            "costas",
            "Estampa bordada atualizada",
            "preta e branco",
            2
        );

        given()
            .contentType(ContentType.JSON)
            .body(dto)
            .pathParam("id", 1)
            .when().put("/estampas/bordada/{id}")
            .then()
            .statusCode(anyOf(is(200), is(204), is(404)));
    }

    @Test
    public void testDelete() {
        given()
            .pathParam("id", 3)
            .when().delete("/estampas/bordada/{id}")
            .then()
            .statusCode(anyOf(is(204), is(404)));
    }
}
